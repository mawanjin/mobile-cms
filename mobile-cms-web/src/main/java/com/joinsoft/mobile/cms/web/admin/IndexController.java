package com.joinsoft.mobile.cms.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * xingsen@join-cn.com
 */
@Controller
@RequestMapping(AdminController.PORTAL_PREFIX + "/*")
public class IndexController extends AdminController {
    @RequestMapping("index")
    public void index() {

    }
}
