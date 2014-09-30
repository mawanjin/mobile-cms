package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.framework.security.entity.User;

import javax.persistence.*;

/**
 * Created by wangxulong on 14-8-19.
 */
@Entity
@Table(name = "tb_user_profile")
public class TbUserProfile extends AutoModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String mobile;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
