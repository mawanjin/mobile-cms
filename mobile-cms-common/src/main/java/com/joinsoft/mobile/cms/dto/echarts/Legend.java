package com.joinsoft.mobile.cms.dto.echarts;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Map;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class Legend {
    /**
     * 说明：
     * 布局方式，默认为水平布局，可选为：'horizontal' | 'vertical'<br>
     * <p/>
     * 默认值：
     * 'horizontal'
     */
    private EOrient orient;

    /**
     * 说明：
     * 水平安放位置，默认为全图居中，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）<br>
     * <p/>
     * 默认值：
     * 'center'
     */
    private Object x;

    /**
     * 说明：
     * 垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）<br>
     * <p/>
     * 默认值：
     * 'top'
     */
    private Object y;

    /**
     * 说明：
     * 图例边框线宽，单位px，默认为0（无边框）<br>
     * <p/>
     * 默认值：
     * 0
     */
    private Integer borderWidth;

    /**
     * 说明：
     * 图例内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距，同css，见下图<br>
     * <p/>
     * 默认值：
     * 5
     */
    private String padding;

    /**
     * 说明：
     * 各个item之间的间隔，单位px，默认为10，横向布局时为水平间隔，纵向布局时为纵向间隔，见下图<br>
     * <p/>
     * 默认值：
     * 10
     */
    private Integer itemGap;

    /**
     * 说明：
     * 默认只设定了图例文字颜色（详见textStyle） ，更个性化的是，要指定文字颜色跟随图例，可设color为'auto'<br>
     * <p/>
     * 默认值：
     * {color: '#333'}
     */
    private TextStyle textStyle;


    /**
     * 说明：
     * 配置默认选中状态，可配合LEGEND.SELECTED事件做动态数据载入，try this »<br>
     * <p/>
     * 默认值：
     * null
     */
    private Map<String, Boolean> selected;

    /**
     * 说明：
     * 图例内容数组，数组项通常为{string}，每一项代表一个系列的name。
     * 使用根据该值索引series中同名系列所用的图表类型和itemStyle，如果索引不到，该item将默认为没启用状态。
     * 如需个性化图例文字样式，可把数组项改为{Object}，知道文本样式和个性化图例icon，格式为
     * {
     * name : {string},
     * textStyle : {Object},
     * icon : {string}
     * }<br>
     * <p/>
     * 默认值：
     * [ ]
     */
    private ArrayList<Object> data;

    public Legend() {
        this.orient = EOrient.vertical;
        this.x = "left";
        this.y = "35";
    }

    /**
     * @return the orient
     */
    public EOrient getOrient() {
        return orient;
    }

    /**
     * @param orient the orient to set
     */
    public Legend setOrient(EOrient orient) {
        this.orient = orient;
        return this;
    }

    /**
     * @return the x
     */
    public Object getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public Legend setX(Object x) {
        if (x instanceof EHorizontalAlign)
            this.x = ((EHorizontalAlign) x).name();
        else if (x instanceof Number)
            this.x = ((Number) x).intValue();
        return this;
    }

    /**
     * @return the y
     */
    public Object getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public Legend setY(Object y) {
        if (y instanceof EHorizontalAlign)
            this.y = ((EHorizontalAlign) y).name();
        else if (y instanceof Number)
            this.y = ((Number) y).intValue();
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
    public Legend setBorderWidth(Integer borderWidth) {
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
    public Legend setPadding(String padding) {
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
    public Legend setItemGap(Integer itemGap) {
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
    public Legend setTextStyle(TextStyle textStyle) {
        this.textStyle = textStyle;
        return this;
    }


    /**
     * @return the selected
     */
    public Map<String, Boolean> getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public Legend setSelected(Map<String, Boolean> selected) {
        this.selected = selected;
        return this;
    }

    /**
     * @return the data
     */
    public ArrayList<Object> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public Legend setData(ArrayList<Object> data) {
        this.data = data;
        return this;
    }

    public Legend addData(Object data) {
        if (this.data == null) {
            this.data = Lists.newArrayList();
        }
        this.data.add(data);
        return this;
    }
}
