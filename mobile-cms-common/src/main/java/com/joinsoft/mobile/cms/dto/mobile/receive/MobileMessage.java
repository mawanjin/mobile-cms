package com.joinsoft.mobile.cms.dto.mobile.receive;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class MobileMessage {
    protected final String msgId;
    protected final String to;
    protected final String from;
    protected final Date createTime;

    public MobileMessage(String from, String to, String msgId, Date createTime) {
        this.from = from;
        this.to = to;
        this.msgId = msgId;
        this.createTime = createTime;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "MobileMessage{" +
                "msgId='" + msgId + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
