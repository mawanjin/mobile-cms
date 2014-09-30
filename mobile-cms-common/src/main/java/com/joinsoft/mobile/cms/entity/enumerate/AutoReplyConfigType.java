package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * xingsen@join-cn.com
 */
public enum AutoReplyConfigType {
    Event("事件触发"), Keyword("关键字触发");
    private final String title;

    AutoReplyConfigType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
