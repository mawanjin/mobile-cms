package com.joinsoft.mobile.cms.entity.mall;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.enumerate.WifiCodeStatus;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wangxulong on 14-9-17.
 */
@Entity
@Table(name = "tb_wifi_code")
public class TbWifiCode extends AutoModel {
    private String code;
    private Long feedUserId;
    private String feedUserName;
    @Enumerated
    private WifiCodeStatus status;
    private Date createTime;
    private Date feedTime;
    private Long orderItemId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getFeedUserId() {
        return feedUserId;
    }

    public void setFeedUserId(Long feedUserId) {
        this.feedUserId = feedUserId;
    }

    public String getFeedUserName() {
        return feedUserName;
    }

    public void setFeedUserName(String feedUserName) {
        this.feedUserName = feedUserName;
    }

    public WifiCodeStatus getStatus() {
        return status;
    }

    public void setStatus(WifiCodeStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFeedTime() {
        return feedTime;
    }

    public void setFeedTime(Date feedTime) {
        this.feedTime = feedTime;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }
}
