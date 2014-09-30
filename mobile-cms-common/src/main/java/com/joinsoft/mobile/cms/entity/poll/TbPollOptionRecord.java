package com.joinsoft.mobile.cms.entity.poll;

import com.joinsoft.framework.entity.AutoModel;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * User: wujun
 * Date: 2014/9/11
 */
@Entity
@Table(name = "tb_poll_option_record")
public class TbPollOptionRecord extends AutoModel {
    @ManyToOne
    @JoinColumn(name = "poll_record_id")
    private TbPollRecord pollRecord;
    @ManyToOne
    @JoinColumn(name = "poll_option_id")
    private TbPollOption pollOption;
    @Type(type = "yes_no")
    private boolean correct;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "poll_option_record_id")
    private Set<TbPollOptionValueRecord> valueRecords;

    public TbPollRecord getPollRecord() {
        return pollRecord;
    }

    public void setPollRecord(TbPollRecord pollRecord) {
        this.pollRecord = pollRecord;
    }

    public TbPollOption getPollOption() {
        return pollOption;
    }

    public void setPollOption(TbPollOption pollOption) {
        this.pollOption = pollOption;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Set<TbPollOptionValueRecord> getValueRecords() {
        return valueRecords;
    }

    public void setValueRecords(Set<TbPollOptionValueRecord> valueRecords) {
        this.valueRecords = valueRecords;
    }
}
