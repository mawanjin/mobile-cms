package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class LegendItem {
    private String name;
    private TextStyle textStyle;
    private String icon;

    public LegendItem(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public LegendItem setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the textStyle
     */
    public TextStyle getTextStyle() {
        return textStyle;
    }

    /**
     * @param textStyle the textStyle to set
     */
    public LegendItem setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public LegendItem setIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
