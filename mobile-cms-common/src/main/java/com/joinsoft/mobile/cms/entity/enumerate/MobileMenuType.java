package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * User: wujun
 * Date: 2014/8/20
 */
public enum MobileMenuType {
    View("View", ""),
    Click("Click", ""),
    Top("一级菜单", "top_button_"),
    CMS("关联文本消息", "m_cms_");
    private String text;
    private String prefix;

    MobileMenuType(String text, String prefix) {
        this.text = text;
        this.prefix = prefix;
    }

    public String getText() {
        return text;
    }

    public String getPrefix() {
        return prefix;
    }
}
