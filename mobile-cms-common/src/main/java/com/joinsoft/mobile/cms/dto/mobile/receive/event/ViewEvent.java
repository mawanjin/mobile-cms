package com.joinsoft.mobile.cms.dto.mobile.receive.event;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class ViewEvent extends MobileEvent {
    private String eventKey;

    public ViewEvent(String event, String from, String to, String msgId, Date createTime) {
        super(event, from, to, msgId, createTime);
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
