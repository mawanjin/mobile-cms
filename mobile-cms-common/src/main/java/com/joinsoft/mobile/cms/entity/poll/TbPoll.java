package com.joinsoft.mobile.cms.entity.poll;

import com.joinsoft.mobile.cms.entity.content.TbNodeChild;
import com.joinsoft.mobile.cms.entity.enumerate.PollStatus;
import com.joinsoft.mobile.cms.entity.enumerate.PollType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: wujun
 * Date: 2014/8/14
 */
@Entity
@Table(name = "tb_poll")
public class TbPoll extends TbNodeChild {
    private Date startTime;
    private Date endTime;
    @Enumerated
    private PollType type;
    @Enumerated
    private PollStatus status;

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

    public PollStatus getStatus() {
        return status;
    }

    public void setStatus(PollStatus status) {
        this.status = status;
    }

    public PollType getType() {
        return type;
    }

    public void setType(PollType type) {
        this.type = type;
    }
}
