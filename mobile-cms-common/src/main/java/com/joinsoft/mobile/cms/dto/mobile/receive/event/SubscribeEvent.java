package com.joinsoft.mobile.cms.dto.mobile.receive.event;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class SubscribeEvent extends MobileEvent {
    public SubscribeEvent(String event, String from, String to, String msgId, Date createTime) {
        super(event, from, to, msgId, createTime);
    }
}
