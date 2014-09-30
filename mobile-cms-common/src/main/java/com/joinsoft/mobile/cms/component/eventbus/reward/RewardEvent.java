package com.joinsoft.mobile.cms.component.eventbus.reward;

import com.joinsoft.mobile.cms.component.eventbus.SystemEvent;

/**
 * xingsen@join-cn.com
 */
public abstract class RewardEvent extends SystemEvent {
    public RewardEvent(String key, String description) {
        super(key, description);
    }
}
