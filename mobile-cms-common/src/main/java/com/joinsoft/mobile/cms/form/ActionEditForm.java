package com.joinsoft.mobile.cms.form;

import com.joinsoft.mobile.cms.entity.TbAction;
import com.joinsoft.mobile.cms.entity.enumerate.ActionCycle;
import com.joinsoft.mobile.cms.entity.enumerate.ValueType;

/**
 * xingsen@join-cn.com
 */
public class ActionEditForm {
    private Long id;
    private String key;

    private ValueType valueType;
    private Integer minValue;
    private Integer maxValue;
    private ActionCycle cycle;
    private Integer count;
    private Boolean isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public void formEntity(TbAction entity) {
        this.id = entity.getId();
        this.count = entity.getCount();
        this.cycle = entity.getCycle();
        this.maxValue = entity.getMaxValue();
        this.minValue = entity.getMinValue();
        this.valueType = entity.getValueType();
        this.key = entity.getActionName();
        this.isValid = entity.getIsValid();
    }

    public void toEntity(TbAction action) {
        action.setId(this.id);
        action.setCount(this.count);
        action.setCycle(this.cycle);
        action.setMaxValue(this.maxValue);
        action.setMinValue(this.minValue);
        action.setValueType(this.valueType);
        action.setIsValid(this.isValid);
    }
}
