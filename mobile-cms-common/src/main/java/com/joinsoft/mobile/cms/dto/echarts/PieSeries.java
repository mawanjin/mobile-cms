package com.joinsoft.mobile.cms.dto.echarts;

/**
 * User: wujun
 * Date: 2014/8/29
 */
public class PieSeries extends ASeries {
    /**
     * 说明：
     * 圆心坐标，支持绝对值（px）和百分比，百分比计算min(width, height) * 50%<br>
     * <p/>
     * 默认值：
     * ['50%', '50%']
     */
    private String[] center;

    /**
     * 说明：
     * 半径，支持绝对值（px）和百分比，百分比计算比，min(width, height) / 2 * 75%， 传数组实现环形图，[内半径，外半径]<br>
     * <p/>
     * 默认值：
     * [0, '75%']
     */
    private Object radius;

    /**
     * 说明：
     * 开始角度, 饼图（90）、仪表盘（225），有效输入范围：[-360,360]<br>
     * <p/>
     * 默认值：
     * 90
     */
    private Integer startAngle;

    /**
     * 说明：
     * 最小角度，可用于防止某item的值过小而影响交互<br>
     * <p/>
     * 默认值：
     * 0
     */
    private Integer minAngle;

    /**
     * 说明：
     * 显示是否顺时针<br>
     * <p/>
     * 默认值：
     * TRUE
     */
    private Boolean clockWise;

    public PieSeries() {
        this.setType(ESeriesType.pie);
    }

    /**
     * @return the center
     */
    public String[] getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public PieSeries setCenter(String[] center) {
        this.center = center;
        return this;
    }

    /**
     * @return the radius
     */
    public Object getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public PieSeries setRadius(Object radius) {
        this.radius = radius;
        return this;
    }

    /**
     * @return the startAngle
     */
    public Integer getStartAngle() {
        return startAngle;
    }

    /**
     * @param startAngle the startAngle to set
     */
    public PieSeries setStartAngle(Integer startAngle) {
        this.startAngle = startAngle;
        return this;
    }

    /**
     * @return the minAngle
     */
    public Integer getMinAngle() {
        return minAngle;
    }

    /**
     * @param minAngle the minAngle to set
     */
    public PieSeries setMinAngle(Integer minAngle) {
        this.minAngle = minAngle;
        return this;
    }

    /**
     * @return the clockWise
     */
    public Boolean getClockWise() {
        return clockWise;
    }

    /**
     * @param clockWise the clockWise to set
     */
    public PieSeries setClockWise(Boolean clockWise) {
        this.clockWise = clockWise;
        return this;
    }
}
