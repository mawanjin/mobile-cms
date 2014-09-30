package com.joinsoft.mobile.cms.web.admin.mall;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.entity.mall.TbOrder;
import com.joinsoft.mobile.cms.entity.mall.TbOrderItem;
import com.joinsoft.mobile.cms.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-19.
 */
@Controller
@RequestMapping(MallController.PORTAL_PREFIX + "/order/*")
public class OrderController extends MallController {
    @Resource
    private OrderService orderService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbOrder> page = orderService.search(searchParams, buildPageRequest(request));
        model.addAttribute("page", page);
    }

    @RequestMapping("show")
    public void show(Long id, Model model) {
        List<TbOrderItem> orderItems = orderService.getOrderItemByOrder(id);
        model.addAttribute("orderItems", orderItems);
    }
}
