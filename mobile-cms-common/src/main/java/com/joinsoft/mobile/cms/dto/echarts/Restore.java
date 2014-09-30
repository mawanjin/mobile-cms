package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class Restore {
    private Boolean show = true;
    private String title;

    /**
     * @return the show
     */
    public Boolean getShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public Restore setShow(Boolean show) {
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
    public Restore setTitle(String title) {
        this.title = title;
        return this;
    }
}
