package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * xingsen@join-cn.com
 */
@Entity
@Table(name = "tb_menu_event")
public class TbMenuEvent extends AutoModel {
    private String data;
    private String key;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
