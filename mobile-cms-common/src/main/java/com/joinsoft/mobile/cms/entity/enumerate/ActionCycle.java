package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * xingsen@join-cn.com
 */
public enum ActionCycle {
    Day("每天"), Week("每周"), Month("每月"), Empty("无周期");
    private String title;

    private ActionCycle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
