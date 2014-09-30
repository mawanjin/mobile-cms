package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class TextStyle {

    /**
     * 说明：
     * 修饰，仅对tooltip.textStyle生效<br>
     * <p/>
     * 默认值：
     * 'none'
     */
    private String decoration;

    /**
     * 说明：
     * 水平对齐方式，可选为：'left' | 'right' | 'center'<br>
     * <p/>
     * 默认值：
     * 各异
     */
    private EHorizontalAlign align;

    /**
     * 说明：
     * 垂直对齐方式，可选为：'top' | 'bottom' | 'middle'<br>
     * <p/>
     * 默认值：
     * 各异
     */
    private EVerticalAlign baseline;

    /**
     * 说明：
     * 字体系列<br>
     * <p/>
     * 默认值：
     * 'Arial, Verdana, sans-serif'
     */
    private String fontFamily;

    /**
     * 说明：
     * 字号 ，单位px<br>
     * <p/>
     * 默认值：
     * 12
     */
    private Integer fontSize;

    /**
     * @return the decoration
     */
    public String getDecoration() {
        return decoration;
    }

    /**
     * @param decoration the decoration to set
     */
    public TextStyle setDecoration(String decoration) {
        this.decoration = decoration;
        return this;
    }

    /**
     * @return the align
     */
    public EHorizontalAlign getAlign() {
        return align;
    }

    /**
     * @param align the align to set
     */
    public TextStyle setAlign(EHorizontalAlign align) {
        this.align = align;
        return this;
    }

    /**
     * @return the baseline
     */
    public EVerticalAlign getBaseline() {
        return baseline;
    }

    /**
     * @param baseline the baseline to set
     */
    public TextStyle setBaseline(EVerticalAlign baseline) {
        this.baseline = baseline;
        return this;
    }

    /**
     * @return the fontFamily
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * @param fontFamily the fontFamily to set
     */
    public TextStyle setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    /**
     * @return the fontSize
     */
    public Integer getFontSize() {
        return fontSize;
    }

    /**
     * @param fontSize the fontSize to set
     */
    public TextStyle setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }
}
