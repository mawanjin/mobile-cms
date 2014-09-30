package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * User: wujun
 * Date: 2014/9/5
 */
public enum PollOptionValueType {

    String("字符串"),
    Pic("图片"),
    Video("视频");
    private String text;

    PollOptionValueType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
