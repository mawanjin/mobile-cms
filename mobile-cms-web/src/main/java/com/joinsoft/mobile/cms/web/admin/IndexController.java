package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.service.mobile.MobileSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private MobileSecurityService securityService;

    @RequestMapping("index")
    public void index(HttpServletRequest request) {

    }

    @RequestMapping("dologout")
    public void dologout(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loginUser");
        //todo 插入数据库退出
        logger.debug("method dologout() called.");
    }


}
