package com.joinsoft.mobile.cms.entity.poll;


import com.joinsoft.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User: wujun
 * Date: 2014/8/30
 */
@Entity
@Table(name = "tb_poll_option_value_record")
public class TbPollOptionValueRecord extends AutoModel {
    @ManyToOne
    @JoinColumn(name = "poll_option_record_id")
    private TbPollOptionRecord optionRecord;
    @ManyToOne
    @JoinColumn(name = "poll_option_value_id")
    private TbPollOptionValue optionValue;
    private String textValue;

    public TbPollOptionRecord getOptionRecord() {
        return optionRecord;
    }

    public void setOptionRecord(TbPollOptionRecord optionRecord) {
        this.optionRecord = optionRecord;
    }

    public TbPollOptionValue getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(TbPollOptionValue optionValue) {
        this.optionValue = optionValue;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }
}
