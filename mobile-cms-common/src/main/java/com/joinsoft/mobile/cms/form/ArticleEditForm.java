package com.joinsoft.mobile.cms.form;

import com.joinsoft.mobile.cms.entity.content.TbArticle;

/**
 * xingsen@join-cn.com
 */
public class ArticleEditForm extends NodeEditForm {
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

    public void fromEntity(TbArticle article) {
        super.fromEntity(article.getNode());
        this.content = article.getContent();
        this.sourceUrl = article.getSourceUrl();
    }

}
