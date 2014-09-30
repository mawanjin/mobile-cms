package com.joinsoft.mobile.cms.dto.mobile.receive;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class TextMessage extends MobileMessage {
    private String content;

    public TextMessage(String from, String to, String msgId, Date createTime) {
        super(from, to, msgId, createTime);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
