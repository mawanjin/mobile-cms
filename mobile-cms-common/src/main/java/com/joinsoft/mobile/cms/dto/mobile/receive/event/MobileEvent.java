package com.joinsoft.mobile.cms.dto.mobile.receive.event;

import com.joinsoft.mobile.cms.dto.mobile.receive.MobileMessage;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class MobileEvent extends MobileMessage {
    protected final String event;

    public MobileEvent(String event, String from, String to, String msgId, Date createTime) {
        super(from, to, msgId, createTime);
        this.event = event;
    }

    @Override
    public String toString() {
        return "MobileEvent{" +
                "event='" + event + '\'' +
                '}' + super.toString();
    }
}
