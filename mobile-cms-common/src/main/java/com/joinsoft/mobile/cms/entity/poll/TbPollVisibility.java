package com.joinsoft.mobile.cms.entity.poll;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.framework.security.entity.User;

import javax.persistence.*;

/**
 * User: wujun
 * Date: 2014/9/5
 */
@Entity
@Table(name = "tb_poll_visibility")
public class TbPollVisibility extends AutoModel {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private TbPoll poll;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;//不存在对应记录时表示对所有用户可见；user_id=null表示不可见

    public TbPoll getPoll() {
        return poll;
    }

    public void setPoll(TbPoll poll) {
        this.poll = poll;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
