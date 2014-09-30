package com.joinsoft.mobile.cms.entity.poll;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.enumerate.PollOptionType;

import javax.persistence.*;
import java.util.Set;

/**
 * User: wujun
 * Date: 2014/8/14
 */
@Entity
@Table(name = "tb_poll_option")
public class TbPollOption extends AutoModel {

    private String title;
    @Enumerated
    private PollOptionType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private TbPoll poll;
    private Float score;//每道题目的分数，非计分类题目为null
    @OneToMany
    @JoinColumn(name = "poll_option_id")
    private Set<TbPollOptionValue> values;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PollOptionType getType() {
        return type;
    }

    public void setType(PollOptionType type) {
        this.type = type;
    }

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

    public Set<TbPollOptionValue> getValues() {
        return values;
    }

    public void setValues(Set<TbPollOptionValue> values) {
        this.values = values;
    }
}
