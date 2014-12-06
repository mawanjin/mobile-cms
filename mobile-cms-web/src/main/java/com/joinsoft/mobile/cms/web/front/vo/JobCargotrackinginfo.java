package com.joinsoft.mobile.cms.web.front.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by Administrator on 14-11-12.
 */
public class JobCargotrackinginfo {
    private String 运单号;
    private String 客户名;
    private String 箱号;
    private String 货名;
    private String 箱型;
    private Double 重量;
    private String 发货单位;
    private String 发货单位地址电话;
    private String 收货单位;
    private String 收货单位地址电话;
    private String 运输条款;
    private Double 运费合计;
    private String 收款单位;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 装箱时间;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 始发日期;
    private String 始发港;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 离港时间;
    private String 一程船名航次;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 抵港时间一;
    private String 中转港一;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 离港时间一;
    private String 二程船名航次;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 抵港时间二;
    private String 中转港二;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 离港时间二;
    private String 三程船名航次;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 抵港时间三;
    private String 中转港三;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 离港时间三;
    private String 四程船名航次;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 抵港时间四;
    private String 中转港四;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 离港时间四;
    private String 五程船名航次;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 抵港时间五;
    private String 中转港五;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 离港时间五;
    private String 六程船名航次;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 抵港时间;
    private String 目的港;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 到达时间;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 送达时间;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 拆箱时间;
    private String 指令情况;
    private String 目前状态;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 目前状态起始时间;
    private String 预期状态;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 预期状态起始时间;
    private String 最新航次;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 航次始发时间;
    private String 航次始发港;
    private String 航次目的港;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 航次到达时间;
    private String 预配船名航次;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 预计开航时间;
    private String 航线类型;
    private String 备注;
    private String 客户单号;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date 码头作业时间;

    public String get运单号() {
        return 运单号;
    }

    public void set运单号(String 运单号) {
        this.运单号 = 运单号;
    }

    public String get箱号() {
        return 箱号;
    }

    public void set箱号(String 箱号) {
        this.箱号 = 箱号;
    }

    public String get始发港() {
        return 始发港;
    }

    public void set始发港(String 始发港) {
        this.始发港 = 始发港;
    }

    public String get目的港() {
        return 目的港;
    }

    public void set目的港(String 目的港) {
        this.目的港 = 目的港;
    }

    public String get目前状态() {
        return 目前状态;
    }

    public void set目前状态(String 目前状态) {
        this.目前状态 = 目前状态;
    }

    public Date get目前状态起始时间() {
        return 目前状态起始时间;
    }

    public void set目前状态起始时间(Date 目前状态起始时间) {
        this.目前状态起始时间 = 目前状态起始时间;
    }

    public String get预期状态() {
        return 预期状态;
    }

    public void set预期状态(String 预期状态) {
        this.预期状态 = 预期状态;
    }

    public Date get预期状态起始时间() {
        return 预期状态起始时间;
    }

    public void set预期状态起始时间(Date 预期状态起始时间) {
        this.预期状态起始时间 = 预期状态起始时间;
    }

    public String get一程船名航次() {
        return 一程船名航次;
    }

    public void set一程船名航次(String 一程船名航次) {
        this.一程船名航次 = 一程船名航次;
    }

    public String get二程船名航次() {
        return 二程船名航次;
    }

    public void set二程船名航次(String 二程船名航次) {
        this.二程船名航次 = 二程船名航次;
    }

    public String get三程船名航次() {
        return 三程船名航次;
    }

    public void set三程船名航次(String 三程船名航次) {
        this.三程船名航次 = 三程船名航次;
    }

    public String get四程船名航次() {
        return 四程船名航次;
    }

    public void set四程船名航次(String 四程船名航次) {
        this.四程船名航次 = 四程船名航次;
    }

    public String get五程船名航次() {
        return 五程船名航次;
    }

    public void set五程船名航次(String 五程船名航次) {
        this.五程船名航次 = 五程船名航次;
    }

    public String get六程船名航次() {
        return 六程船名航次;
    }

    public void set六程船名航次(String 六程船名航次) {
        this.六程船名航次 = 六程船名航次;
    }

    public String get预配船名航次() {
        return 预配船名航次;
    }

    public void set预配船名航次(String 预配船名航次) {
        this.预配船名航次 = 预配船名航次;
    }

    public Date get预计开航时间() {
        return 预计开航时间;
    }

    public void set预计开航时间(Date 预计开航时间) {
        this.预计开航时间 = 预计开航时间;
    }

    public String get客户名() {
        return 客户名;
    }

    public void set客户名(String 客户名) {
        this.客户名 = 客户名;
    }

    public String get货名() {
        return 货名;
    }

    public void set货名(String 货名) {
        this.货名 = 货名;
    }

    public String get箱型() {
        return 箱型;
    }

    public void set箱型(String 箱型) {
        this.箱型 = 箱型;
    }

    public Double get重量() {
        return 重量;
    }

    public void set重量(Double 重量) {
        this.重量 = 重量;
    }

    public String get发货单位() {
        return 发货单位;
    }

    public void set发货单位(String 发货单位) {
        this.发货单位 = 发货单位;
    }

    public String get发货单位地址电话() {
        return 发货单位地址电话;
    }

    public void set发货单位地址电话(String 发货单位地址电话) {
        this.发货单位地址电话 = 发货单位地址电话;
    }

    public String get收货单位() {
        return 收货单位;
    }

    public void set收货单位(String 收货单位) {
        this.收货单位 = 收货单位;
    }

    public String get收货单位地址电话() {
        return 收货单位地址电话;
    }

    public void set收货单位地址电话(String 收货单位地址电话) {
        this.收货单位地址电话 = 收货单位地址电话;
    }

    public String get运输条款() {
        return 运输条款;
    }

    public void set运输条款(String 运输条款) {
        this.运输条款 = 运输条款;
    }

    public Double get运费合计() {
        return 运费合计;
    }

    public void set运费合计(Double 运费合计) {
        this.运费合计 = 运费合计;
    }

    public String get收款单位() {
        return 收款单位;
    }

    public void set收款单位(String 收款单位) {
        this.收款单位 = 收款单位;
    }

    public Date get装箱时间() {
        return 装箱时间;
    }

    public void set装箱时间(Date 装箱时间) {
        this.装箱时间 = 装箱时间;
    }

    public Date get始发日期() {
        return 始发日期;
    }

    public void set始发日期(Date 始发日期) {
        this.始发日期 = 始发日期;
    }

    public Date get离港时间() {
        return 离港时间;
    }

    public void set离港时间(Date 离港时间) {
        this.离港时间 = 离港时间;
    }

    public Date get抵港时间一() {
        return 抵港时间一;
    }

    public void set抵港时间一(Date 抵港时间一) {
        this.抵港时间一 = 抵港时间一;
    }

    public String get中转港一() {
        return 中转港一;
    }

    public void set中转港一(String 中转港一) {
        this.中转港一 = 中转港一;
    }

    public Date get离港时间一() {
        return 离港时间一;
    }

    public void set离港时间一(Date 离港时间一) {
        this.离港时间一 = 离港时间一;
    }

    public Date get抵港时间二() {
        return 抵港时间二;
    }

    public void set抵港时间二(Date 抵港时间二) {
        this.抵港时间二 = 抵港时间二;
    }

    public String get中转港二() {
        return 中转港二;
    }

    public void set中转港二(String 中转港二) {
        this.中转港二 = 中转港二;
    }

    public Date get离港时间二() {
        return 离港时间二;
    }

    public void set离港时间二(Date 离港时间二) {
        this.离港时间二 = 离港时间二;
    }

    public Date get抵港时间三() {
        return 抵港时间三;
    }

    public void set抵港时间三(Date 抵港时间三) {
        this.抵港时间三 = 抵港时间三;
    }

    public String get中转港三() {
        return 中转港三;
    }

    public void set中转港三(String 中转港三) {
        this.中转港三 = 中转港三;
    }

    public Date get离港时间三() {
        return 离港时间三;
    }

    public void set离港时间三(Date 离港时间三) {
        this.离港时间三 = 离港时间三;
    }

    public Date get抵港时间四() {
        return 抵港时间四;
    }

    public void set抵港时间四(Date 抵港时间四) {
        this.抵港时间四 = 抵港时间四;
    }

    public String get中转港四() {
        return 中转港四;
    }

    public void set中转港四(String 中转港四) {
        this.中转港四 = 中转港四;
    }

    public Date get离港时间四() {
        return 离港时间四;
    }

    public void set离港时间四(Date 离港时间四) {
        this.离港时间四 = 离港时间四;
    }

    public Date get抵港时间五() {
        return 抵港时间五;
    }

    public void set抵港时间五(Date 抵港时间五) {
        this.抵港时间五 = 抵港时间五;
    }

    public String get中转港五() {
        return 中转港五;
    }

    public void set中转港五(String 中转港五) {
        this.中转港五 = 中转港五;
    }

    public Date get离港时间五() {
        return 离港时间五;
    }

    public void set离港时间五(Date 离港时间五) {
        this.离港时间五 = 离港时间五;
    }

    public Date get抵港时间() {
        return 抵港时间;
    }

    public void set抵港时间(Date 抵港时间) {
        this.抵港时间 = 抵港时间;
    }

    public Date get到达时间() {
        return 到达时间;
    }

    public void set到达时间(Date 到达时间) {
        this.到达时间 = 到达时间;
    }

    public Date get送达时间() {
        return 送达时间;
    }

    public void set送达时间(Date 送达时间) {
        this.送达时间 = 送达时间;
    }

    public Date get拆箱时间() {
        return 拆箱时间;
    }

    public void set拆箱时间(Date 拆箱时间) {
        this.拆箱时间 = 拆箱时间;
    }

    public String get指令情况() {
        return 指令情况;
    }

    public void set指令情况(String 指令情况) {
        this.指令情况 = 指令情况;
    }

    public String get最新航次() {
        return 最新航次;
    }

    public void set最新航次(String 最新航次) {
        this.最新航次 = 最新航次;
    }

    public Date get航次始发时间() {
        return 航次始发时间;
    }

    public void set航次始发时间(Date 航次始发时间) {
        this.航次始发时间 = 航次始发时间;
    }

    public String get航次始发港() {
        return 航次始发港;
    }

    public void set航次始发港(String 航次始发港) {
        this.航次始发港 = 航次始发港;
    }

    public String get航次目的港() {
        return 航次目的港;
    }

    public void set航次目的港(String 航次目的港) {
        this.航次目的港 = 航次目的港;
    }

    public Date get航次到达时间() {
        return 航次到达时间;
    }

    public void set航次到达时间(Date 航次到达时间) {
        this.航次到达时间 = 航次到达时间;
    }

    public String get航线类型() {
        return 航线类型;
    }

    public void set航线类型(String 航线类型) {
        this.航线类型 = 航线类型;
    }

    public String get备注() {
        return 备注;
    }

    public void set备注(String 备注) {
        this.备注 = 备注;
    }

    public String get客户单号() {
        return 客户单号;
    }

    public void set客户单号(String 客户单号) {
        this.客户单号 = 客户单号;
    }

    public Date get码头作业时间() {
        return 码头作业时间;
    }

    public void set码头作业时间(Date 码头作业时间) {
        this.码头作业时间 = 码头作业时间;
    }

    public JobCargotrackinginfo() {
    }
}
