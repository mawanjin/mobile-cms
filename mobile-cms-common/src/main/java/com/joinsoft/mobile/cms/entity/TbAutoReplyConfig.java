package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.enumerate.AutoReplyConfigType;

import javax.persistence.*;

/**
 * xingsen@join-cn.com
 */
@Entity
@Table(name = "tb_auto_reply_config")
public class TbAutoReplyConfig extends AutoModel {
    private String eventKey;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "about_node_id")
    private TbCmsNode node;
    private AutoReplyConfigType type;
    private String keyWord;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public TbCmsNode getNode() {
        return node;
    }

    public void setNode(TbCmsNode node) {
        this.node = node;
    }

    public AutoReplyConfigType getType() {
        return type;
    }

    public void setType(AutoReplyConfigType type) {
        this.type = type;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
