package com.joinsoft.mobile.cms.form;

import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.content.TbText;

/**
 * xingsen@join-cn.com
 */
public class TextEditForm extends NodeEditForm {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void fromEntity(TbText entity) {
        super.fromEntity(entity.getNode());
        this.id = entity.getNode().getId();
        this.title = entity.getNode().getTitle();
        this.content = entity.getContent();
    }

    public TbText toEntity(TbText entity, TbCmsNode node) {
        entity.setContent(this.content);
        entity.setNode(node);
        return entity;
    }
}
