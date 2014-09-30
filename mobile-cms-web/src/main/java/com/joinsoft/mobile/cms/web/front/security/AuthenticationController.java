package com.joinsoft.mobile.cms.web.front.security;

import com.joinsoft.mobile.cms.component.eventbus.reward.RewardEvent;
import com.joinsoft.mobile.cms.dto.RewardEventResult;
import com.joinsoft.mobile.cms.service.mobile.MobileSecurityService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by wangxulong on 14-8-24.
 */
@Controller
@RequestMapping(AccessTokenController.PORTAL_PREFIX + "/auth/*")
public class AuthenticationController extends AccessTokenController {
    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Resource
    private MobileSecurityService securityService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public void login(String code, Model model) {
        model.addAttribute("code", code);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String mobilePhone, String password, String code, HttpServletRequest request, Model model) {
        checkPhone(mobilePhone);
        saveErrorNext(request, "/auth/login.do");
        //绑定openId和用户名
        RewardEventResult result = securityService.loginAndBind(mobilePhone, password, code);
        model.addAttribute("message", "恭喜您！注册成功！");
        model.addAttribute("result", result);
        return messageBox();
    }

    @RequestMapping(value = "reg", method = RequestMethod.GET)
    public void reg(String code, Model model) {
        model.addAttribute("code", code);
    }

    @RequestMapping(value = "reg", method = RequestMethod.POST)
    public String reg(String mobilePhone, String password, String verifyCode, String code, String rePassword,
                      String invite, Model model, HttpServletRequest request) {
        checkPhone(mobilePhone);
        checkPassword(password, rePassword);
        //注册并且绑定
        saveErrorNext(request, "/auth/reg.do");
        RewardEventResult result = securityService.regAndBind(mobilePhone, password, verifyCode, code, invite);
        model.addAttribute("result", result);
        model.addAttribute("message", "恭喜您！注册成功！");
        return messageBox();

    }

    @RequestMapping(value = "restore")
    public void restorePassword(String code, Model model) {
        model.addAttribute("code", code);
    }

    @RequestMapping(value = "restore", method = RequestMethod.POST)
    public String restorePassword(String mobilePhone, String newPassword, String verifyCode, String rePassword,
                                  String code, HttpServletRequest request, Model model) {
        checkPhone(mobilePhone);
        checkPassword(newPassword, rePassword);
        //修改密码并且绑定用户
        saveErrorNext(request, "/auth/restore.do");
        securityService.restoreAndBind(mobilePhone, newPassword, verifyCode, code);
        model.addAttribute("message", "修改成功");
        return messageBox();

    }

    @RequestMapping(value = "success")
    public void success() {
        //成功页面
    }

    protected void checkPassword(String newPassword, String rePassword) {
        if (StringUtils.isEmpty(newPassword)) {
            throw new RuntimeException("密码不能为空");
        }
        if (!newPassword.equals(rePassword)) {
            throw new RuntimeException("两次密码不一样");
        }
    }

    protected void checkPhone(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            throw new RuntimeException("手机号不能空");
        }
    }

}
