package com.joinsoft.mobile.cms.dto;

import com.joinsoft.mobile.cms.entity.mall.TbOrder;
import com.joinsoft.mobile.cms.entity.mall.TbOrderItem;

import java.util.List;

/**
 * Created by wangxulong on 14-9-11.
 */
public class MyOrderDto {
    private TbOrder order;
    private List<TbOrderItem> orderItems;

    public TbOrder getOrder() {
        return order;
    }

    public void setOrder(TbOrder order) {
        this.order = order;
    }

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
