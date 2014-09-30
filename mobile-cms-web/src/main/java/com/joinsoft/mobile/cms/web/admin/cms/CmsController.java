package com.joinsoft.mobile.cms.web.admin.cms;

import com.joinsoft.mobile.cms.web.admin.AdminController;

/**
 * Created by wangxulong on 14-9-4.
 */
public abstract class CmsController extends AdminController {
    public static final String PORTAL_PREFIX = AdminController.PORTAL_PREFIX + "/cms";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }
}
