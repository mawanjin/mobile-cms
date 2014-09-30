package com.joinsoft.mobile.cms.web.admin;

import com.joinsoft.framework.web.controller.UIController;

/**
 * xingsen@join-cn.com
 */
public class AdminController extends UIController {
    public static final String PORTAL_PREFIX = "/admin";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }
}
