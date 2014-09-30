package com.joinsoft.mobile.cms.component.eventbus;

/**
 * xingsen@join-cn.com
 */
public class SystemEvent {
    protected String key;
    protected String description;

    public SystemEvent(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SystemEvent{");
        sb.append("key='").append(key).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
