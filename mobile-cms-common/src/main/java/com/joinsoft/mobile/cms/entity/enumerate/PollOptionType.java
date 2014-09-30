package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * User: wujun
 * Date: 2014/8/14
 */
public enum PollOptionType {
    Radio("单选"),
    CheckBox("多选"),
    Text("文本框");

    private String text;

    PollOptionType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
