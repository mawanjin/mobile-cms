package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * Created by wangxulong on 14-9-17.
 */
public enum WifiCodeStatus {
    Valid("有效"),Invalid("无效"),Sent("已发送"),;
    private final String title;

    WifiCodeStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
