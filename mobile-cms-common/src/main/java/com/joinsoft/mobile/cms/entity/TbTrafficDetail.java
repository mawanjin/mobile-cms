package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.enumerate.TrafficDetailStatus;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangxulong on 14-8-26.
 */
@Entity
@Table(name = "tb_traffic_detail")
public class TbTrafficDetail extends AutoModel {
    private Long userId;
    private String userName;
    private String loginName;
    private String action;
    private String cnName;
    private Date createTime;
    private Date effectTime;
    private BigDecimal traffic;
    @Enumerated
    private TrafficDetailStatus status;
    private String jobNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public BigDecimal getTraffic() {
        return traffic;
    }

    public void setTraffic(BigDecimal traffic) {
        this.traffic = traffic;
    }

    public TrafficDetailStatus getStatus() {
        return status;
    }

    public void setStatus(TrafficDetailStatus status) {
        this.status = status;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
}
