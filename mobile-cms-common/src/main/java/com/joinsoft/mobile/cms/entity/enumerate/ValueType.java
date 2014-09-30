package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * xingsen@join-cn.com
 */
public enum ValueType {
    Fixed("固定值"), Rand("随机值");
    private final String title;

    ValueType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
