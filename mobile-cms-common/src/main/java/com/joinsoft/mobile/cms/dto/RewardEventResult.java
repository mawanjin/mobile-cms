package com.joinsoft.mobile.cms.dto;

import java.math.BigDecimal;

/**
 * xingsen@join-cn.com
 */
public class RewardEventResult {
    private final BigDecimal pointVal;
    private final BigDecimal trafficVal;

    public RewardEventResult(BigDecimal trafficVal, BigDecimal pointVal) {
        this.trafficVal = trafficVal;
        this.pointVal = pointVal;
    }

    public RewardEventResult() {
        this.trafficVal = new BigDecimal(0);
        this.pointVal = new BigDecimal(0);
    }

    public BigDecimal getPointVal() {
        return pointVal;
    }

    public BigDecimal getTrafficVal() {
        return trafficVal;
    }
}
