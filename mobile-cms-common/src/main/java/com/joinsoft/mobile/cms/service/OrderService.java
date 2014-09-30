package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.dto.MyOrderDto;
import com.joinsoft.mobile.cms.entity.TbPointDetail;
import com.joinsoft.mobile.cms.entity.TbUserProfile;
import com.joinsoft.mobile.cms.entity.enumerate.WifiCodeStatus;
import com.joinsoft.mobile.cms.entity.mall.TbOrder;
import com.joinsoft.mobile.cms.entity.mall.TbOrderItem;
import com.joinsoft.mobile.cms.entity.mall.TbProduct;
import com.joinsoft.mobile.cms.entity.mall.TbWifiCode;
import com.joinsoft.mobile.cms.repository.PointDetailRepository;
import com.joinsoft.mobile.cms.repository.UserProfileRepository;
import com.joinsoft.mobile.cms.repository.mall.OrderItemRepository;
import com.joinsoft.mobile.cms.repository.mall.OrderRepository;
import com.joinsoft.mobile.cms.repository.mall.ProductRepository;
import com.joinsoft.mobile.cms.repository.mall.WifiCodeRepository;
import com.joinsoft.mobile.cms.util.DateSearchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by wangxulong on 14-8-19.
 */
@Service
@Transactional
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private PointDetailRepository pointDetailRepository;
    @Resource
    private OrderItemRepository orderItemRepository;
    @Resource
    private SecurityService securityService;
    @Resource
    private ProductRepository productRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private UserProfileRepository userProfileRepository;
    @Resource
    private WifiCodeRepository wifiCodeRepository;

    public Page<TbOrder> search(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbOrder> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbOrder> page = orderRepository.findAll(spec, pageable);
        return page;
    }

    public List<TbOrderItem> getOrderItemByOrder(Long orderId) {
        return orderItemRepository.findByOrder(orderId);
    }


    public List<MyOrderDto> getMyOrders(Integer year, Integer month) {
        User user = securityService.getLoginUser();
        Assert.notNull(user, "用户没有登录");

        List<MyOrderDto> myOrderDtos = new ArrayList<MyOrderDto>();
        Date beginDate = DateSearchUtil.getBeginMonthDate(year, month);
        Date endDate = DateSearchUtil.getEndMonthDate(year, month);

        List<TbOrder> myOrders = orderRepository.findMyOrderByYearAndMonth(user.getId(), beginDate, endDate);
        for (TbOrder myOrder : myOrders) {
            MyOrderDto orderDto = new MyOrderDto();
            orderDto.setOrder(myOrder);
            List<TbOrderItem> orderItems = orderItemRepository.findByOrder(myOrder.getId());
            for (TbOrderItem orderItem : orderItems) {
                TbWifiCode wifiCode = wifiCodeRepository.findByOrderItemId(orderItem.getId());
                orderItem.setWifiCode(wifiCode.getCode());
            }
            orderDto.setOrderItems(orderItems);
            myOrderDtos.add(orderDto);
        }
        return myOrderDtos;
    }

    public void submitOrder(Map<Long, Integer> productIds) {
        User user = securityService.getLoginUser();
        Assert.notNull(user, "用户没有登录");

        TbOrder order = new TbOrder();
        order.setCreateTime(new Date());
        order.setOrderNum(UUID.randomUUID().toString() + user.getLoginName() + user.getId());
        order.setUser(user);

        BigDecimal totalPrice = new BigDecimal(0);
        List<TbOrderItem> orderItems = new ArrayList<TbOrderItem>();
        for (Long id : productIds.keySet()) {
            TbProduct product = productRepository.findOne(id);
            Assert.notNull(product, "商品不存在或已经下线");

            totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(productIds.get(id))));
            TbOrderItem orderItem = new TbOrderItem();
            orderItem.setCount(productIds.get(id));
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItems.add(orderItem);
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        orderItemRepository.save(orderItems);
        //自动支付
        payOrder(order.getId(), productIds);
    }

    public void payOrder(Long orderId, Map<Long, Integer> productIds) {
        User user = securityService.getLoginUser();
        Assert.notNull(user, "用户没有登录");

        TbOrder order = orderRepository.findOne(orderId);

        BigDecimal userMemory = pointDetailRepository.findCurrentPoint(user.getId());
        logger.info("userMemory {} TotalPrice{}", userMemory, order);
        if (userMemory == null || userMemory.compareTo(order.getTotalPrice()) < 0) {
            throw new RuntimeException("积分不足");
        }
        long newUserScore = userMemory.subtract(order.getTotalPrice()).longValue();

        StringBuilder message = new StringBuilder("兑换：");
        for (Long id : productIds.keySet()) {
            TbProduct product = productRepository.findOne(id);
            message.append(String.format(" %s*%s ", product.getTitle(), productIds.get(id).intValue()));
        }
        TbPointDetail pointDetail = new TbPointDetail();

        pointDetail.setCreateTime(new Date());
        pointDetail.setLoginName(user.getLoginName());
        pointDetail.setUserId(user.getId());
        pointDetail.setUserName(user.getName());
        pointDetail.setPoint(order.getTotalPrice().multiply(new BigDecimal(-1)));
        pointDetail.setCnName(message.toString());

        pointDetailRepository.save(pointDetail);

        //发送串码
        sendWifiCodeToUser(user.getId(), order.getId());

        if (logger.isInfoEnabled()) {
            logger.info(String.format("用户 %s 支付订单 %s 花费 %s 剩余 %s", user.getLoginName(), order.getId(),
                    order.getTotalPrice(), newUserScore));
        }
    }

    protected void sendWifiCodeToUser(Long userId, Long orderId) {
        TbUserProfile userProfile = userProfileRepository.findByUser(userId);
        String mobilePhone = userProfile.getMobile();
        Assert.hasText(mobilePhone, String.format("用户:%s没有绑定手机", userProfile.getUser().getLoginName()));

        List<TbOrderItem> items = orderItemRepository.findByOrder(orderId);
        if (!items.isEmpty()) {
            //查询可用的串码
            PageRequest pageRequest = new PageRequest(0, items.size());
            List<TbWifiCode> wifiCodes = wifiCodeRepository.findByStatus(pageRequest, WifiCodeStatus.Valid);
            if (wifiCodes.size() != items.size()) {
                throw new RuntimeException("WIFI流量卡不足，请联系客服人员");
            }
            for (int i = 0; i < wifiCodes.size(); i++) {
                TbWifiCode wifiCode = wifiCodes.get(i);
                String code = wifiCode.getCode();
                //TODO 接口发送串码
                wifiCode.setStatus(WifiCodeStatus.Sent);
                wifiCode.setFeedUserId(userId);
                wifiCode.setFeedTime(new Date());
                wifiCode.setFeedUserName(userProfile.getUser().getName());
                wifiCode.setOrderItemId(items.get(i).getId());
            }
            wifiCodeRepository.save(wifiCodes);
        }
    }


}
