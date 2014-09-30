package com.joinsoft.mobile.cms.dto.mobile.receive.event;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class ScanEvent extends MobileEvent {
    private String eventKey;
    private String ticket;

    public ScanEvent(String event, String from, String to, String msgId, Date createTime) {
        super(event, from, to, msgId, createTime);
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
