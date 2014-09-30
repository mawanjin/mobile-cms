package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class Feature {
    private Mark mark;
    private DataView dataView;
    private Restore restore;
    private SaveAsImage saveAsImage;

    public Feature() {
        this.mark = new Mark();
        this.dataView = new DataView();
        this.restore = new Restore();
        this.saveAsImage = new SaveAsImage();
    }

    /**
     * @return the mark
     */
    public Mark getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public Feature setMark(Mark mark) {
        this.mark = mark;
        return this;
    }

    /**
     * @return the dataView
     */
    public DataView getDataView() {
        return dataView;
    }

    /**
     * @param dataView the dataView to set
     */
    public Feature setDataView(DataView dataView) {
        this.dataView = dataView;
        return this;
    }

    /**
     * @return the restore
     */
    public Restore getRestore() {
        return restore;
    }

    /**
     * @param restore the restore to set
     */
    public Feature setRestore(Restore restore) {
        this.restore = restore;
        return this;
    }

    /**
     * @return the saveAsImage
     */
    public SaveAsImage getSaveAsImage() {
        return saveAsImage;
    }

    /**
     * @param saveAsImage the saveAsImage to set
     */
    public Feature setSaveAsImage(SaveAsImage saveAsImage) {
        this.saveAsImage = saveAsImage;
        return this;
    }

}
