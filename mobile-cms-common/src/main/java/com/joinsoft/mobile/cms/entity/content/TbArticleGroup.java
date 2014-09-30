package com.joinsoft.mobile.cms.entity.content;

import javax.persistence.*;

/**
 * Created by wangxulong on 14-8-14.
 */
@Entity
@Table(name = "tb_article_group")
public class TbArticleGroup extends TbNodeChild {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_node_id")
    private TbCmsNode contentNode;
    private Integer orderIndex;

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public TbCmsNode getContentNode() {
        return contentNode;
    }

    public void setContentNode(TbCmsNode contentNode) {
        this.contentNode = contentNode;
    }
}
