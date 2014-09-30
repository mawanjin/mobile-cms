package com.joinsoft.mobile.cms.entity.poll;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.framework.security.entity.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * User: wujun
 * Date: 2014/8/30
 */
@Entity
@Table(name = "tb_poll_record")
public class TbPollRecord extends AutoModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private TbPoll poll;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private Float score;//该次投票该用户的总得分
    @Type(type = "yes_no")
    private boolean reward;//是否已经兑奖

    public TbPoll getPoll() {
        return poll;
    }

    public void setPoll(TbPoll poll) {
        this.poll = poll;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isReward() {
        return reward;
    }

    public void setReward(boolean reward) {
        this.reward = reward;
    }
}
