package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.framework.security.repository.UserRepository;
import com.joinsoft.mobile.cms.dto.MyPointDto;
import com.joinsoft.mobile.cms.dto.MyTrafficDto;
import com.joinsoft.mobile.cms.dto.RewardEventResult;
import com.joinsoft.mobile.cms.entity.*;
import com.joinsoft.mobile.cms.entity.enumerate.TrafficDetailStatus;
import com.joinsoft.mobile.cms.repository.ActionRepository;
import com.joinsoft.mobile.cms.repository.PointDetailRepository;
import com.joinsoft.mobile.cms.repository.TrafficDetailRepository;
import com.joinsoft.mobile.cms.repository.UserProfileRepository;
import com.joinsoft.online.signin.ws.csb.TrafficService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangxulong on 14-8-26.
 */
@Service
@Transactional
public class RewardService {
    public static final String teleCom = "133|153|180|181|189";
    private Logger logger = LoggerFactory.getLogger(RewardService.class);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Resource
    private SecurityService securityService;
    @Resource
    private ActionService actionService;
    @Resource
    private ConfigService configService;
    @Resource
    private TrafficDetailRepository trafficDetailRepository;
    @Resource
    private PointDetailRepository pointDetailRepository;
    @Resource
    private ActionRepository actionRepository;
    @Resource
    private UserProfileRepository userProfileRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private TrafficService trafficService;


    public RewardEventResult fireEvent(String event, String fromOpenId) {
        User user = userRepository.findByLoginName(fromOpenId);
        Assert.notNull(user, "用户不存在");
        TbUserProfile userProfile = userProfileRepository.findByUser(user.getId());

        TbAction action = actionRepository.findByActionName(event);
        Assert.notNull(action, "此操作不存在");

        int count = actionService.calcAction(event, null);
        if (count == 0) {
            return new RewardEventResult();
        }
        String keyword = getUserKeyword(userProfile.getMobile());
        TbConfig pointConfig = configService.getConfigPointByUser(keyword, action.getId());
        Assert.notNull(pointConfig, "配置比例不存在");
        TbConfig trafficConfig = configService.getConfigTrafficByUser(keyword, action.getId());
        Assert.notNull(trafficConfig, "配置比例不存在");

        BigDecimal trafficVal = saveTrafficLog(userProfile, action, trafficConfig, count);
        BigDecimal pointVal = savePointLog(userProfile, action, pointConfig, count);
        return new RewardEventResult(trafficVal, pointVal);
    }

    public Page<TbPointDetail> searchScoreDetailLog(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbPointDetail> spec = DynamicSpecifications.bySearchFilter(filters.values());
        return pointDetailRepository.findAll(spec, pageable);
    }

    public Page<TbTrafficDetail> searchTrafficDetailLog(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbTrafficDetail> spec = DynamicSpecifications.bySearchFilter(filters.values());
        return trafficDetailRepository.findAll(spec, pageable);
    }

    public MyPointDto getMyPoint(Integer year, Integer month) {
        User user = securityService.getLoginUser();
        Assert.notNull(user, "用户不存在");

        //当前积分
        BigDecimal totalPoint = pointDetailRepository.findCurrentPoint(user.getId());
        //当月总共获取的积分
        BigDecimal currentMonthPoint = pointDetailRepository.findAllPointByMonth(user.getId(),
                getBeginDate(year, month), getEndDate(year, month));
        //当月消耗
        List<TbPointDetail> currentMonthConsumeRecords = pointDetailRepository.findCurrentMonthConsume(user.getId(),
                getBeginDate(year, month), getEndDate(year, month));
        logger.info(String.format("userId=%s; currentScore:%s; allScore=%s", user.getId(), currentMonthPoint,
                totalPoint));
        return new MyPointDto(currentMonthPoint, totalPoint,currentMonthConsumeRecords);
    }

    public MyTrafficDto getMyTraffic(Integer year, Integer month) {
        User user = securityService.getLoginUser();
        Assert.notNull(user, "用户不存在");

        Date beginTime = getBeginDate(year, month);
        Date endTime = getEndDate(year, month);
        //当月总领取
        BigDecimal currentMonthTraffic = trafficDetailRepository.findAllTrafficByMonth(user.getId(), beginTime, endTime);
        //本月流量记录
        List<TbTrafficDetail> currentMonthRecords = trafficDetailRepository.findCurrentMonthTrafficRecord(user.getId(), beginTime, endTime);
        //total available traffic
        BigDecimal availableTraffic = trafficDetailRepository.findAvailableTraffic(user.getId());

        return new MyTrafficDto(availableTraffic, currentMonthTraffic, currentMonthRecords);
    }

    public void applyTraffic(BigDecimal applyTraffic) {
        User user = securityService.getLoginUser();
        Assert.notNull(user, "用户没有登录");
        TbUserProfile userProfile = userProfileRepository.findByUser(user.getId());
        BigDecimal availableTraffic = trafficDetailRepository.findAvailableTraffic(user.getId());
        if (applyTraffic.doubleValue() > availableTraffic.doubleValue()) {
            throw new RuntimeException("可申请流量不足");
        }
        TbTrafficDetail trafficDetail = new TbTrafficDetail();
        trafficDetail.setStatus(TrafficDetailStatus.Applied);
        trafficDetail.setTraffic(applyTraffic.multiply(new BigDecimal("-1")));
        trafficDetail.setUserId(user.getId());
        trafficDetail.setUserName(user.getName());
        trafficDetail.setLoginName(user.getLoginName());
        trafficDetail.setCnName("申请流量");
        trafficDetail.setCreateTime(new Date());
        trafficDetailRepository.save(trafficDetail);
        trafficService.order(userProfile.getMobile(),applyTraffic.intValue(),trafficDetail.getId());
        trafficDetail.setStatus(TrafficDetailStatus.Processing);
        trafficDetailRepository.save(trafficDetail);
    }

    protected String getUserKeyword(String mobilePhone) {
        String str = mobilePhone.substring(0, 3);
        List<String> phones = Arrays.asList(teleCom.split("\\|"));
        if (phones.contains(str)) {
            return "tele";
        }
        return "other";
    }

    protected BigDecimal saveTrafficLog(TbUserProfile userProfile, TbAction action, TbConfig config, int count) {
        TbTrafficDetail trafficLog = new TbTrafficDetail();
        User user = userProfile.getUser();
        trafficLog.setUserId(user.getId());
        trafficLog.setUserName(user.getName());
        trafficLog.setLoginName(user.getLoginName());
        trafficLog.setCreateTime(new Date());
        trafficLog.setAction(action.getActionName());
        trafficLog.setCnName(action.getCnName());
        trafficLog.setStatus(TrafficDetailStatus.Acquired);
        BigDecimal trafficVal = new BigDecimal(NumberUtils.toDouble(config.getVal(), 0) * count);
        trafficLog.setTraffic(trafficVal);
        trafficDetailRepository.save(trafficLog);
        return trafficVal;
    }

    protected BigDecimal savePointLog(TbUserProfile userProfile, TbAction action, TbConfig config, int count) {
        TbPointDetail pointLog = new TbPointDetail();
        User user = userProfile.getUser();
        pointLog.setUserId(user.getId());
        pointLog.setUserName(user.getName());
        pointLog.setLoginName(user.getLoginName());
        pointLog.setAction(action.getActionName());
        pointLog.setCnName(action.getCnName());
        pointLog.setCreateTime(new Date());
        BigDecimal pointVal = new BigDecimal(NumberUtils.toDouble(config.getVal(), 0) * count);
        pointLog.setPoint(pointVal);
        pointDetailRepository.save(pointLog);
        return pointVal;
    }

    protected Date getBeginDate(Integer year, Integer month) {
        Calendar calendar = getCalendar(year, month);
        logger.info("开始时间：{}", simpleDateFormat.format(calendar.getTime()));
        return calendar.getTime();
    }

    protected Date getEndDate(Integer year, Integer month) {
        Calendar calendar = getCalendar(year, month);
        calendar.add(Calendar.MONTH, 1);
        logger.info("结束时间：{}", simpleDateFormat.format(calendar.getTime()));
        return calendar.getTime();
    }

    protected Calendar getCalendar(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        if (null != year) {
            calendar.set(Calendar.YEAR, year.intValue());
        }
        if (null != month) {
            calendar.set(Calendar.MONTH, month - 1);
        }
        return calendar;
    }


}
