package com.joinsoft.mobile.cms.entity.mall;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.framework.security.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wangxulong on 14-8-19.
 */
@Entity
@Table(name = "tb_order")
public class TbOrder extends AutoModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String orderNum;
    private BigDecimal totalPrice;
    private Date createTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
