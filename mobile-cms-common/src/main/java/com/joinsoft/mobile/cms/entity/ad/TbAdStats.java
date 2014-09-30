package com.joinsoft.mobile.cms.entity.ad;


import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by wangxulong on 14-8-18.
 */
@Entity
@Table(name = "tb_ad_stats")
public class TbAdStats extends AutoModel {
    @ManyToOne
    @JoinColumn(name = "ad_id")
    private TbAd ad;
    private Long visitCount;
    private Long refCount;

    public TbAd getAd() {
        return ad;
    }

    public void setAd(TbAd ad) {
        this.ad = ad;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public Long getRefCount() {
        return refCount;
    }

    public void setRefCount(Long refCount) {
        this.refCount = refCount;
    }
}
