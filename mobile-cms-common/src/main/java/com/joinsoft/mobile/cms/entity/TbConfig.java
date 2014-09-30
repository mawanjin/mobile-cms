package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.*;

/**
 * xingsen@join-cn.com
 */
@Entity
@Table(name = "tb_config")
public class TbConfig extends AutoModel {
    private String cnName;
    private String name;
    private String val;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private TbAction action;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public TbAction getAction() {
        return action;
    }

    public void setAction(TbAction action) {
        this.action = action;
    }
}