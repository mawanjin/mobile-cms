package com.joinsoft.mobile.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * xingsen@join-cn.com
 */
public class MobileMenuButton {
    private String type;
    private String name;
    private String key;
    private String url;
    @JsonProperty("sub_button")
    private List<MobileMenuButton> subButton;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MobileMenuButton> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<MobileMenuButton> subButton) {
        this.subButton = subButton;
    }
}
