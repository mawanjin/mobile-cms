package com.joinsoft.mobile.cms.entity;


import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.agent.TbSectionAgent;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangxulong on 14-8-17.
 */
@Entity
@Table(name = "tb_audit")
public class TbAudit extends AutoModel {
    private String target;
    private String web;
    private String action;
    private String oper;
    private Date  operTime;
    private String operIp;


    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }
}



