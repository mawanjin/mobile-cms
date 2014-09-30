package com.joinsoft.mobile.cms.component.eventbus.replymessage;

/**
 * xingsen@join-cn.com
 */
public class RewardChangeEvent extends ReplyMessageEvent {
    public RewardChangeEvent(String openId) {
        super(openId, "reward.change", "积分或流量变化");
    }

    public RewardChangeEvent() {
        this("");
    }
}
