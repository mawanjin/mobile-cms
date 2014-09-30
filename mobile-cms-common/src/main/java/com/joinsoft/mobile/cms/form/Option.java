package com.joinsoft.mobile.cms.form;

/**
 * xingsen@join-cn.com
 */
public class Option {
    private final Object val;
    private final String title;

    public Option(String title, Object val) {
        this.title = title;
        this.val = val;
    }

    public Object getVal() {
        return val;
    }

    public String getTitle() {
        return title;
    }
}
