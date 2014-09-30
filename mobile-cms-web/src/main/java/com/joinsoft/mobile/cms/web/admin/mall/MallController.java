package com.joinsoft.mobile.cms.web.admin.mall;

import com.joinsoft.mobile.cms.web.admin.AdminController;

/**
 * xingsen@join-cn.com
 */
public class MallController extends AdminController {
    public static final String PORTAL_PREFIX = AdminController.PORTAL_PREFIX + "/mall";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }
}
