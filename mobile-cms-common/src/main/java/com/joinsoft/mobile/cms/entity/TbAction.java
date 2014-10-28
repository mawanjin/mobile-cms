package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.enumerate.ActionCycle;
import com.joinsoft.mobile.cms.entity.enumerate.ValueType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * xingsen@join-cn.com
 */
@Entity
@Table(name = "tb_action")
public class TbAction extends AutoModel {
    private String actionName;
    private String cnName;
    private ValueType valueType;
    private Integer minValue;
    private Integer maxValue;

    private ActionCycle cycle;
    private Integer count;
    private Boolean isValid;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public ActionCycle getCycle() {
        return cycle;
    }

    public void setCycle(ActionCycle cycle) {
        this.cycle = cycle;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "TbAction{" +
                "actionName='" + actionName + '\'' +
                ", cnName='" + cnName + '\'' +
                ", valueType=" + valueType +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", cycle=" + cycle +
                ", count=" + count +
                '}';
    }
}
