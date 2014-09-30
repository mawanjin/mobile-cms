package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.framework.security.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * xingsen@join-cn.com
 */
@Entity
@Table(name = "tb_action_log")
public class TbActionLog extends AutoModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private Date logTime;
    private int value;
    private String action;
    private String cnName;
    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
}
