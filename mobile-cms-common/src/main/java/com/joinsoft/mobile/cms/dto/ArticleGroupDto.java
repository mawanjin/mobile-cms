package com.joinsoft.mobile.cms.dto;

import com.joinsoft.mobile.cms.entity.content.TbArticleGroup;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;

/**
 * xingsen@join-cn.com
 */
public class ArticleGroupDto {
    private TbCmsNode contentNode;
    private TbCmsNode node;
    private Integer order;

    public TbCmsNode getNode() {
        return node;
    }

    public void setNode(TbCmsNode node) {
        this.node = node;
    }

    public TbCmsNode getContentNode() {
        return contentNode;
    }

    public void setContentNode(TbCmsNode contentNode) {
        this.contentNode = contentNode;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public static ArticleGroupDto fromEntity(TbArticleGroup multiArticle) {
        ArticleGroupDto dto = new ArticleGroupDto();
        dto.contentNode = multiArticle.getContentNode();
        dto.node = multiArticle.getNode();
        dto.order = multiArticle.getOrderIndex();
        return dto;
    }
}
