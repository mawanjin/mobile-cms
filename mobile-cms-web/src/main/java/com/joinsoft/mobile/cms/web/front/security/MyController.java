package com.joinsoft.mobile.cms.web.front.security;

import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.component.eventbus.reward.SystemEventActivitySign;
import com.joinsoft.mobile.cms.component.eventbus.reward.SystemEventSign;
import com.joinsoft.mobile.cms.dto.MyPointDto;
import com.joinsoft.mobile.cms.dto.MyTrafficDto;
import com.joinsoft.mobile.cms.dto.RewardEventResult;
import com.joinsoft.mobile.cms.service.OrderService;
import com.joinsoft.mobile.cms.service.RewardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by wangxulong on 14-8-27.
 */
@Controller
@RequestMapping(AccessTokenController.PORTAL_PREFIX + "/my/*")
public class MyController extends AccessTokenController {
    @Resource
    private RewardService rewardService;
    @Resource
    private EventBusService eventBusService;
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "sign")
    public void sign(Model model) {
    }

    @RequestMapping(value = "do_sign")
    public String doSign(Model model) {
        RewardEventResult result = eventBusService.fireRewardEvent(new SystemEventSign());
        model.addAttribute("result", result);
        String message = "签到成功";
        if (result.getPointVal().intValue() == (0) && result.getTrafficVal().intValue() == 0) {
            message = "对不起，您已经签过到了";
        }
        model.addAttribute("message", message);
        return AccessTokenController.PORTAL_PREFIX + "/my/sign";
    }

    @RequestMapping("point")
    public void myPoint(Integer year, Integer month, Model model) {
        MyPointDto myPoint = rewardService.getMyPoint(year, month);
        addYearAndMonth(year, month, model);
        model.addAttribute("myPoint", myPoint);
    }

    @RequestMapping("traffic")
    public void myTraffic(Integer year, Integer month, Model model) {
        MyTrafficDto myTraffic = rewardService.getMyTraffic(year, month);

        addYearAndMonth(year, month, model);
        model.addAttribute("myTraffic", myTraffic);
    }

    @RequestMapping("order")
    public void myOrder(Model model, Integer year, Integer month) {
        addYearAndMonth(year, month, model);
        model.addAttribute("myOrders", orderService.getMyOrders(year, month));

    }

    @RequestMapping(value = "activity_sign")
    public void activitySign(Model model) {
    }

    @RequestMapping(value = "do_activity_sign")
    public String doActivitySign(Model model) {
        RewardEventResult result = eventBusService.fireRewardEvent(new SystemEventActivitySign());
        model.addAttribute("result", result);
        String message = "签到成功";
        if (result.getPointVal().intValue() == (0) && result.getTrafficVal().intValue() == 0) {
            message = "对不起，您已经签过到了";
        }
        model.addAttribute("message", message);
        return AccessTokenController.PORTAL_PREFIX + "/my/activity_sign";
    }

    @RequestMapping(value = "apply")
    public String apply(BigDecimal applyTraffic) {
        rewardService.applyTraffic(applyTraffic);
        return redirect("/my/traffic.do");
    }

}
