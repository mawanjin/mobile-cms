package com.joinsoft.mobile.cms.dto.template;

import java.math.BigDecimal;

/**
 * Created by wangxulong on 14-9-6.
 */
public class UserMessageTemplate {
    private String name;
    private String mobilePhone;
    private BigDecimal currentPoint;
    private BigDecimal currentTraffic;//当月获取流量

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public BigDecimal getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(BigDecimal currentPoint) {
        this.currentPoint = currentPoint;
    }

    public BigDecimal getCurrentTraffic() {
        return currentTraffic;
    }

    public void setCurrentTraffic(BigDecimal currentTraffic) {
        this.currentTraffic = currentTraffic;
    }


}
