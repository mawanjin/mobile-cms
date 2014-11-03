package com.joinsoft.mobile.cms.entity.news;


import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * Created by wangxulong on 14-8-17.
 */
@Entity
@Table(name = "tb_news")
public class TbNews extends AutoModel {
    private String title;
    private String content;
    private Date  operTime;
   private String oper;
    private Long state;
    private  String online;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}



