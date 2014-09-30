package com.joinsoft.mobile.cms.component.eventbus.replymessage;

import com.joinsoft.mobile.cms.component.eventbus.SystemEvent;

/**
 * xingsen@join-cn.com
 */
public abstract class ReplyMessageEvent extends SystemEvent {
    protected String from;

    public ReplyMessageEvent(String from, String key, String description) {
        super(key, description);
        this.from = from;
    }

    public String getFrom() {
        return from;
    }
}
