package com.joinsoft.mobile.cms.form;

import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.enumerate.PollStatus;
import com.joinsoft.mobile.cms.entity.enumerate.PollType;
import com.joinsoft.mobile.cms.entity.poll.TbPoll;

import java.util.Date;

/**
 * User: wujun
 * Date: 2014/9/7
 */
public class PollEditForm extends NodeEditForm {
    private Date startTime;
    private Date endTime;
    private PollType type;
    private PollStatus status;

    public void fromEntity(TbPoll entity) {
        super.fromEntity(entity.getNode());
        this.id = entity.getNode().getId();
        this.title = entity.getNode().getTitle();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        this.type = entity.getType();
        this.status = entity.getStatus();
    }

    public TbPoll toEntity(TbPoll entity, TbCmsNode node) {
        entity.setStartTime(this.startTime);
        entity.setEndTime(this.endTime);
        entity.setType(this.type);
        entity.setStatus(this.status);
        entity.setNode(node);
        return entity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public PollType getType() {
        return type;
    }

    public void setType(PollType type) {
        this.type = type;
    }

    public PollStatus getStatus() {
        return status;
    }

    public void setStatus(PollStatus status) {
        this.status = status;
    }
}
