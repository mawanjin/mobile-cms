package com.joinsoft.mobile.cms.component.eventbus.replymessage;

/**
 * xingsen@join-cn.com
 */
public class SystemEventSubscribe extends ReplyMessageEvent {
    public SystemEventSubscribe(String openId) {
        super(openId, "sys.subscribe", "用户关注");
    }

    public SystemEventSubscribe() {
        this("");
    }
}
