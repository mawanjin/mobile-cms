package com.joinsoft.mobile.cms.entity.content;

import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * xingsen@join-cn.com
 */
@MappedSuperclass
public abstract class TbNodeChild extends AutoModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    protected TbCmsNode node;

    public TbCmsNode getNode() {
        return node;
    }

    public void setNode(TbCmsNode meta) {
        this.node = meta;
    }
}
