package com.joinsoft.mobile.cms.dto.echarts;

import java.util.List;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class RootOption {
    private Boolean calculable = true;
    private Title title;
    private Toolbox toolbox;
    private Tooltip tooltip;
    private Legend legend;
    private List<PieSeries> series;
    private Boolean animation = false;

    public RootOption() {
        this.tooltip = new Tooltip();
    }

    public Boolean getCalculable() {
        return calculable;
    }

    public void setCalculable(Boolean calculable) {
        this.calculable = calculable;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Toolbox getToolbox() {
        return toolbox;
    }

    public void setToolbox(Toolbox toolbox) {
        this.toolbox = toolbox;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public List<PieSeries> getSeries() {
        return series;
    }

    public void setSeries(List<PieSeries> series) {
        this.series = series;
    }

    public Boolean getAnimation() {
        return animation;
    }

    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }
}
