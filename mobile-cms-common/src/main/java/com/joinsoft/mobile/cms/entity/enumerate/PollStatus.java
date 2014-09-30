package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * User: wujun
 * Date: 2014/8/14
 */
public enum PollStatus {

    New("新发布"),
    Running("进行中"),
    Finished("已结束"),
    Canceled("取消");
    private String text;

    PollStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
