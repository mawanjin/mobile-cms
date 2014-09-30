package com.joinsoft.mobile.cms.dto;

import com.joinsoft.mobile.cms.entity.TbPointDetail;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangxulong on 14-8-29.
 */
public class MyPointDto {
    private BigDecimal currentMonthPoint;
    private BigDecimal totalPoint;
    private List<TbPointDetail> currentMonthConsumeRecords;

    public MyPointDto(BigDecimal currentMonthPoint, BigDecimal totalPoint,List<TbPointDetail> currentMonthConsumeRecords) {
        this.currentMonthPoint = currentMonthPoint;
        this.totalPoint = totalPoint;
        this.currentMonthConsumeRecords = currentMonthConsumeRecords;
    }

    public BigDecimal getCurrentMonthPoint() {
        return currentMonthPoint;
    }

    public void setCurrentMonthPoint(BigDecimal currentMonthPoint) {
        this.currentMonthPoint = currentMonthPoint;
    }

    public BigDecimal getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(BigDecimal totalPoint) {
        this.totalPoint = totalPoint;
    }

    public List<TbPointDetail> getCurrentMonthConsumeRecords() {
        return currentMonthConsumeRecords;
    }

    public void setCurrentMonthConsumeRecords(List<TbPointDetail> currentMonthConsumeRecords) {
        this.currentMonthConsumeRecords = currentMonthConsumeRecords;
    }
}
