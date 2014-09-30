package com.joinsoft.mobile.cms.entity.mall;


import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.*;

/**
 * Created by wangxulong on 14-8-19.
 */
@Entity
@Table(name = "tb_order_item")
public class TbOrderItem extends AutoModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private TbOrder order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private TbProduct product;
    private Integer count;
    @Transient
    private String wifiCode;

    public TbOrder getOrder() {
        return order;
    }

    public void setOrder(TbOrder order) {
        this.order = order;
    }

    public TbProduct getProduct() {
        return product;
    }

    public void setProduct(TbProduct product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getWifiCode() {
        return wifiCode;
    }

    public void setWifiCode(String wifiCode) {
        this.wifiCode = wifiCode;
    }
}
