package com.joinsoft.mobile.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * xingsen@join-cn.com
 */
public class ContentNodeDto {
    private String title;
    @JsonProperty("description")
    private String summary;
    @JsonProperty("picurl")
    private String picUrl;
    @JsonProperty("url")
    private String contentUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
