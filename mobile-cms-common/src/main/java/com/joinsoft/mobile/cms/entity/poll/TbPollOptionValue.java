package com.joinsoft.mobile.cms.entity.poll;


import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.enumerate.PollOptionValueType;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * User: wujun
 * Date: 2014/8/14
 */
@Entity
@Table(name = "tb_poll_option_value")
public class TbPollOptionValue extends AutoModel {
    private String value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_option_id")
    private TbPollOption pollOption;
    @Type(type = "yes_no")
    private Boolean answer;
    @Enumerated
    private PollOptionValueType type;
    @Transient
    private String pic;
    @Transient
    private String video;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TbPollOption getPollOption() {
        return pollOption;
    }

    public void setPollOption(TbPollOption pollOption) {
        this.pollOption = pollOption;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public PollOptionValueType getType() {
        return type;
    }

    public void setType(PollOptionValueType type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
