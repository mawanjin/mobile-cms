package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * Created by onlineportal on 14-9-23.
 */
public enum TrafficDetailStatus {
    Acquired("已获取"), Applied("已申请"), Processing("处理中"), Executed("已生效");
    private final String title;


    TrafficDetailStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
