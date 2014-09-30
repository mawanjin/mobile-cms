package com.joinsoft.mobile.cms.form;

/**
 * Created by wangxulong on 14-9-4.
 */
public class CmsDialogSearchForm {
    private String title;
    private String type[];
    private Long existIds[];
    private Boolean multi;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public Boolean getMulti() {
        return multi;
    }

    public void setMulti(Boolean multi) {
        this.multi = multi;
    }

    public Long[] getExistIds() {
        return existIds;
    }

    public void setExistIds(Long[] existIds) {
        this.existIds = existIds;
    }
}
