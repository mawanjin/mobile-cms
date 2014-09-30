package com.joinsoft.mobile.cms.entity.content;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * xingsen@join-cn.com
 */
@Entity
@Table(name = "tb_text")
public class TbText extends TbNodeChild {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
