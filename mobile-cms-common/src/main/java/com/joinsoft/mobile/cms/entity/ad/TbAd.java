package com.joinsoft.mobile.cms.entity.ad;


import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by wangxulong on 14-8-17.
 */
@Entity
@Table(name = "tb_ad")
public class TbAd extends AutoModel {
    private String title;
    private String pic;
    private String description;
    private Date createTime;
    private String picUrl;

    @Transient
    private TbAdStats adStats;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public TbAdStats getAdStats() {
        return adStats;
    }

    public void setAdStats(TbAdStats adStats) {
        this.adStats = adStats;
    }
}
