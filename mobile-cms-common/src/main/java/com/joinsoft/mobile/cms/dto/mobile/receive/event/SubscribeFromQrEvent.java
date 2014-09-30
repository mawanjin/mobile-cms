package com.joinsoft.mobile.cms.dto.mobile.receive.event;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class SubscribeFromQrEvent extends MobileEvent {
    private String ticket;
    private String eventKey;

    public SubscribeFromQrEvent(String event, String from, String to, String msgId, Date createTime) {
        super(event, from, to, msgId, createTime);
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
