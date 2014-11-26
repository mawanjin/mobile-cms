package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.mobile.cms.service.mobile.MobileSecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/*")
public class IndexController extends AdminController {
    @Resource
    private MobileSecurityService securityService;

    @RequestMapping("index")
    public void index(HttpServletRequest request) {

    }
}
