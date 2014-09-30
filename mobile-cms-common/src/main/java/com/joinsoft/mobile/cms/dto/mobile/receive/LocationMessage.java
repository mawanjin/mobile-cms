package com.joinsoft.mobile.cms.dto.mobile.receive;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class LocationMessage extends MobileMessage {
    private String x;
    private String y;
    private String scale;
    private String label;

    public LocationMessage(String from, String to, String msgId, Date createTime) {
        super(from, to, msgId, createTime);
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
