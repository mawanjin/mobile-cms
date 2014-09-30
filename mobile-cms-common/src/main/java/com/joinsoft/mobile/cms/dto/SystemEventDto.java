package com.joinsoft.mobile.cms.dto;

import com.joinsoft.mobile.cms.component.eventbus.SystemEvent;

/**
 * xingsen@join-cn.com
 */
public class SystemEventDto implements Comparable<SystemEventDto> {
    private String description;
    private String key;

    public SystemEventDto(SystemEvent event) {
        this.description = event.getDescription();
        this.key = event.getKey();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(SystemEventDto o) {
        return o.getKey().compareTo(this.getKey());
    }
}
