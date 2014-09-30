package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * xingsen@join-cn.com
 */
@Entity
@Table(name = "tb_cache")
public class TbCache extends AutoModel {
    private String cacheKey;
    private String cacheValue;
    private Date cacheTime;
    private Integer expiresTime;

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(String cacheValue) {
        this.cacheValue = cacheValue;
    }

    public Date getCacheTime() {
        return cacheTime;
    }

    public void setCacheTime(Date cacheTime) {
        this.cacheTime = cacheTime;
    }

    public Integer getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Integer expiresTime) {
        this.expiresTime = expiresTime;
    }

    @Transient
    public boolean isExpires() {
        Date now = new Date();
        return now.after(DateUtils.addSeconds(cacheTime, expiresTime));
    }
}
