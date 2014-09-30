package com.joinsoft.mobile.cms.web.front.security;

import com.joinsoft.mobile.cms.web.front.FrontController;

/**
 * xingsen@join-cn.com
 */
public abstract class AccessTokenController extends FrontController {
    public static final String PORTAL_PREFIX = FrontController.PORTAL_PREFIX + "/at";

    @Override
    public String getPortalPrefix() {
        return PORTAL_PREFIX;
    }
}
