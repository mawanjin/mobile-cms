package com.joinsoft.mobile.cms.entity.content;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * xingsen@join-cn.com
 */
@Entity
@Table(name = "tb_article")
public class TbArticle extends TbNodeChild {
    private String content;
    private String sourceUrl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
