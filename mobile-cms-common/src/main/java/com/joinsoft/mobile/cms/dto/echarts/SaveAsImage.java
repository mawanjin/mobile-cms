package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class SaveAsImage {
    private Boolean show = true;
    private String title;
    private EImageType type;

    /**
     * @return the show
     */
    public Boolean getShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public SaveAsImage setShow(Boolean show) {
        this.show = show;
        return this;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public SaveAsImage setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return the type
     */
    public EImageType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public SaveAsImage setType(EImageType type) {
        this.type = type;
        return this;
    }

    public enum EImageType {
        PNG, JPEG
    }
}
