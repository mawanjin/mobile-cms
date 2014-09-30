package com.joinsoft.mobile.cms.dto.mobile.receive.event;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class LocationEvent extends MobileEvent {
    private String latitude;
    private String longitude;
    private String precision;

    public LocationEvent(String event, String from, String to, String msgId, Date createTime) {
        super(event, from, to, msgId, createTime);
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
}
