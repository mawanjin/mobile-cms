package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class DataView {
    private Boolean show = true;
    private String title;
    private Boolean readOnly;

    /**
     * @return the show
     */
    public Boolean getShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public DataView setShow(Boolean show) {
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
    public DataView setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return the readOnly
     */
    public Boolean getReadOnly() {
        return readOnly;
    }

    /**
     * @param readOnly the readOnly to set
     */
    public DataView setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }
}
