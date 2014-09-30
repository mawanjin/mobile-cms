package com.joinsoft.mobile.cms.web.front.security;

import com.joinsoft.framework.web.HttpRequests;
import com.joinsoft.mobile.cms.entity.mall.TbProduct;
import com.joinsoft.mobile.cms.service.OrderService;
import com.joinsoft.mobile.cms.service.ProductService;
import com.joinsoft.mobile.cms.web.front.FrontController;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-19.
 */
@Controller
@RequestMapping(AccessTokenController.PORTAL_PREFIX + "/mall/*")
public class MallController extends AccessTokenController {
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;

    @RequestMapping("index")
    public void index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        Page<TbProduct> page = productService.getOnlineProduct(searchParams, buildPageRequest(request, 1));
        model.addAttribute("page", page);
    }

    @RequestMapping("product")
    public void productInfo(Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));

    }

    @RequestMapping("pay")
    public String pay(Long productId, Model model) {
        Map<Long, Integer> productIds = new HashMap<Long, Integer>();
        productIds.put(productId, 1);//购买一个
        orderService.submitOrder(productIds);
        model.addAttribute("result", "true");
        model.addAttribute("product", productService.getProductById(productId));
        return FrontController.PORTAL_PREFIX + "/at/mall/product";
    }

}
