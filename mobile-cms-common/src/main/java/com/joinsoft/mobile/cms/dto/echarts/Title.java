package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class Title {
    private String x = "center";
    /**
     * 说明：
     * 主标题文本，'\n'指定换行<br>
     * <p/>
     * 默认值：
     * ''
     */
    private String text;

    /**
     * 说明：
     * 主标题文本超链接<br>
     * <p/>
     * 默认值：
     * ''
     */
    private String link;

    /**
     * 说明：
     * 指定窗口打开主标题超链接，支持'self' | 'blank'，不指定等同为'blank'（新窗口）<br>
     * <p/>
     * 默认值：
     * null
     */
    private String target;

    /**
     * 说明：
     * 副标题文本，'\n'指定换行<br>
     * <p/>
     * 默认值：
     * ''
     */
    private String subtext;

    /**
     * 说明：
     * 副标题文本超链接<br>
     * <p/>
     * 默认值：
     * ''
     */
    private String subLink;

    /**
     * 说明：
     * 指定窗口打开副标题超链接，支持'self' | 'blank'，不指定等同为'blank'（新窗口）<br>
     * <p/>
     * 默认值：
     * null
     */
    private ETarget subTarget;

    /**
     * 说明：
     * 水平对齐方式，默认根据x设置自动调整，可选为： left' | 'right' | 'center<br>
     * <p/>
     * 默认值：
     * null
     */
    private EHorizontalAlign textAlign;

    /**
     * 说明：
     * 标题边框线宽，单位px，默认为0（无边框）<br>
     * <p/>
     * 默认值：
     * 0
     */
    private Integer borderWidth;

    /**
     * 说明：
     * 标题内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距，同css，见下图<br>
     * <p/>
     * 默认值：
     * 5
     */
    private String padding;

    /**
     * 说明：
     * 主副标题纵向间隔，单位px，默认为10<br>
     * <p/>
     * 默认值：
     * 5
     */
    private Integer itemGap;

    /**
     * 说明：
     * 主标题文本样式（详见textStyle）<br>
     * <p/>
     * 默认值：
     * {
     */
    private TextStyle textStyle;

    /**
     * 说明：
     * 副标题文本样式（详见textStyle）<br>
     * <p/>
     * 默认值：
     * {color: '#aaa'}
     */
    private TextStyle subtextStyle;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public Title setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public Title setLink(String link) {
        this.link = link;
        return this;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public Title setTarget(String target) {
        this.target = target;
        return this;
    }

    /**
     * @return the subText
     */

    public String getSubtext() {
        return subtext;
    }

    /**
     * @param subText the subText to set
     */
    public Title setSubtext(String subText) {
        this.subtext = subText;
        return this;
    }

    /**
     * @return the subLink
     */
    public String getSubLink() {
        return subLink;
    }

    /**
     * @param subLink the subLink to set
     */
    public Title setSubLink(String subLink) {
        this.subLink = subLink;
        return this;
    }

    /**
     * @return the subTarget
     */
    public ETarget getSubTarget() {
        return subTarget;
    }

    /**
     * @param subTarget the subTarget to set
     */
    public Title setSubTarget(ETarget subTarget) {
        this.subTarget = subTarget;
        return this;
    }

    /**
     * @return the textAlign
     */
    public EHorizontalAlign getTextAlign() {
        return textAlign;
    }

    /**
     * @param textAlign the textAlign to set
     */
    public Title setTextAlign(EHorizontalAlign textAlign) {
        this.textAlign = textAlign;
        return this;
    }

    /**
     * @return the borderWidth
     */
    public Integer getBorderWidth() {
        return borderWidth;
    }

    /**
     * @param borderWidth the borderWidth to set
     */
    public Title setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    /**
     * @return the padding
     */
    public String getPadding() {
        return padding;
    }

    /**
     * @param padding the padding to set
     */
    public Title setPadding(String padding) {
        this.padding = padding;
        return this;
    }

    /**
     * @return the itemGap
     */
    public Integer getItemGap() {
        return itemGap;
    }

    /**
     * @param itemGap the itemGap to set
     */
    public Title setItemGap(Integer itemGap) {
        this.itemGap = itemGap;
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
    public Title setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    /**
     * @return the subtextStyle
     */
    public TextStyle getSubtextStyle() {
        return subtextStyle;
    }

    /**
     * @param subtextStyle the subtextStyle to set
     */
    public Title setSubtextStyle(TextStyle subtextStyle) {
        this.subtextStyle = subtextStyle;
        return this;
    }

    public enum ETarget {
        SELF, BLANK
    }
}
