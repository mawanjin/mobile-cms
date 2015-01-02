package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by abcd on 15/1/1.
 */
@Entity
@Table(name = "tb_loginout")
public class TbLoginout extends AutoModel {

    String loginName;
    String name;
    String loginTime;
    String logoutTime;
    String ip;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
