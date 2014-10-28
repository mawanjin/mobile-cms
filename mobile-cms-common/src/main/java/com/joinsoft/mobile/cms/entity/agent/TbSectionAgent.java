package com.joinsoft.mobile.cms.entity.agent;


import com.joinsoft.framework.entity.AutoModel;

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
@Table(name = "tb_section_agent")
public class TbSectionAgent extends AutoModel {
    private String sectionName;

    private Date  operTime;
    private String oper;
    private Integer state;
    @OneToMany(mappedBy = "section",fetch = FetchType.LAZY)
    private Set<TbAgent> agents;


    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}



