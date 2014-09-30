package com.joinsoft.mobile.cms.dto.mobile.receive;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class UrlMessage extends MobileMessage {
    private String url;
    private String description;
    private String title;

    public UrlMessage(String from, String to, String msgId, Date createTime) {
        super(from, to, msgId, createTime);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
