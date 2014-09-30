package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class Tooltip {
    /**
     * 说明：
     * 显示策略，可选为：true（显示） | false（隐藏）<br>
     * <p/>
     * 默认值：
     * TRUE
     */
    private Boolean show;

    /**
     * 说明：
     * tooltip主体内容显示策略，只需tooltip触发事件或显示axisPointer而不需要显示内容时可配置该项为falase，
     * 可选为：true（显示） | false（隐藏）<br>
     * <p/>
     * 默认值：
     * TRUE
     */
    private Boolean showContent;

    /**
     * 说明：
     * 触发类型，默认数据触发，见下图，可选为：'item' | 'axis'<br>
     * <p/>
     * 默认值：
     * 'item'
     */
    private ETrigger trigger;

    /**
     * 说明：
     * 内容格式器：{string}（Template） | {Function}，支持异步回调见表格下方<br>
     * <p/>
     * 默认值：
     * null
     */
    private String formatter;

    /**
     * 说明：
     * 拖拽重计算独有，数据孤岛内容格式器：{string}（Template） | {Function}，见表格下方<br>
     * <p/>
     * 默认值：
     * '{a} < br/>
     * {b} : {c}'
     */
    private String islandFormatter;

    /**
     * 说明：
     * 显示延迟，添加显示延迟可以避免频繁切换，特别是在详情内容需要异步获取的场景，单位ms<br>
     * <p/>
     * 默认值：
     * 20
     */
    private Integer showDelay;

    /**
     * 说明：
     * 隐藏延迟，单位ms<br>
     * <p/>
     * 默认值：
     * 100
     */
    private Integer hideDelay;

    /**
     * 说明：
     * 动画变换时长，单位s，如果你希望tooltip的跟随实时响应，showDelay设置为0是关键，
     * 同时transitionDuration设0也会有交互体验上的差别。<br>
     * <p/>
     * 默认值：
     * 0.4
     */
    private Integer transitionDuration;

    /**
     * 说明：
     * 提示边框圆角，单位px，默认为4<br>
     * <p/>
     * 默认值：
     * 4
     */
    private Integer borderRadius;

    /**
     * 说明：
     * 提示边框线宽，单位px，默认为0（无边框）<br>
     * <p/>
     * 默认值：
     * 0
     */
    private Integer borderWidth;

    /**
     * 说明：
     * 提示内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距，同css<br>
     * <p/>
     * 默认值：
     * 5
     */
    private String padding;

    /**
     * 说明：
     * 文本样式，默认为白色字体（详见textStyle）<br>
     * <p/>
     * 默认值：
     * { color:'#fff' }
     */
    private TextStyle textStyle;

    public Tooltip() {
        this.trigger = ETrigger.item;
        this.formatter = "{a} <br/>{b} : {c} ({d}%)";
    }

    /**
     * @return the show
     */
    public Boolean getShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public Tooltip setShow(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * @return the showContent
     */
    public Boolean getShowContent() {
        return showContent;
    }

    /**
     * @param showContent the showContent to set
     */
    public Tooltip setShowContent(Boolean showContent) {
        this.showContent = showContent;
        return this;
    }

    /**
     * @return the trigger
     */
    public ETrigger getTrigger() {
        return trigger;
    }

    /**
     * @param trigger the trigger to set
     */
    public Tooltip setTrigger(ETrigger trigger) {
        this.trigger = trigger;
        return this;
    }

    /**
     * @return the formatter
     */
    public String getFormatter() {
        return formatter;
    }

    /**
     * @param formatter the formatter to set
     */
    public Tooltip setFormatter(String formatter) {
        this.formatter = formatter;
        return this;
    }

    /**
     * @return the islandFormatter
     */
    public String getIslandFormatter() {
        return islandFormatter;
    }

    /**
     * @param islandFormatter the islandFormatter to set
     */
    public Tooltip setIslandFormatter(String islandFormatter) {
        this.islandFormatter = islandFormatter;
        return this;
    }

    /**
     * @return the showDelay
     */
    public Integer getShowDelay() {
        return showDelay;
    }

    /**
     * @param showDelay the showDelay to set
     */
    public Tooltip setShowDelay(Integer showDelay) {
        this.showDelay = showDelay;
        return this;
    }

    /**
     * @return the hideDelay
     */
    public Integer getHideDelay() {
        return hideDelay;
    }

    /**
     * @param hideDelay the hideDelay to set
     */
    public Tooltip setHideDelay(Integer hideDelay) {
        this.hideDelay = hideDelay;
        return this;
    }

    /**
     * @return the transitionDuration
     */
    public Integer getTransitionDuration() {
        return transitionDuration;
    }

    /**
     * @param transitionDuration the transitionDuration to set
     */
    public Tooltip setTransitionDuration(Integer transitionDuration) {
        this.transitionDuration = transitionDuration;
        return this;
    }


    /**
     * @return the borderRadius
     */
    public Integer getBorderRadius() {
        return borderRadius;
    }

    /**
     * @param borderRadius the borderRadius to set
     */
    public Tooltip setBorderRadius(Integer borderRadius) {
        this.borderRadius = borderRadius;
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
    public Tooltip setBorderWidth(Integer borderWidth) {
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
    public Tooltip setPadding(String padding) {
        this.padding = padding;
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
    public Tooltip setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }

}
