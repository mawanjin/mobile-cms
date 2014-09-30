package com.joinsoft.mobile.cms.dto;

import com.joinsoft.mobile.cms.entity.TbTrafficDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangxulong on 14-8-29.
 */
public class MyTrafficDto {
    private BigDecimal availableTraffic;
    private BigDecimal currentMonthTraffic;
    private List<TbTrafficDetail> currentMonthRecords;

    public MyTrafficDto(BigDecimal availableTraffic, BigDecimal currentMonthTraffic, List<TbTrafficDetail> currentMonthRecords) {
        this.availableTraffic = availableTraffic;
        this.currentMonthTraffic = currentMonthTraffic;
        this.currentMonthRecords = currentMonthRecords;
    }

    public MyTrafficDto() {
    }

    public BigDecimal getAvailableTraffic() {
        return availableTraffic;
    }

    public void setAvailableTraffic(BigDecimal availableTraffic) {
        this.availableTraffic = availableTraffic;
    }


    public BigDecimal getCurrentMonthTraffic() {
        return currentMonthTraffic;
    }

    public void setCurrentMonthTraffic(BigDecimal currentMonthTraffic) {
        this.currentMonthTraffic = currentMonthTraffic;
    }

    public List<TbTrafficDetail> getCurrentMonthRecords() {
        return currentMonthRecords;
    }

    public void setCurrentMonthRecords(List<TbTrafficDetail> currentMonthRecords) {
        this.currentMonthRecords = currentMonthRecords;
    }
}