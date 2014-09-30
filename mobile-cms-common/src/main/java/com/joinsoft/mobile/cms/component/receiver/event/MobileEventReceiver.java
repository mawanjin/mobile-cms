package com.joinsoft.mobile.cms.component.receiver.event;

import com.joinsoft.mobile.cms.dto.mobile.receive.event.MobileEvent;

/**
 * xingsen@join-cn.com
 */
public abstract class MobileEventReceiver {
    public abstract boolean accept(MobileEvent key);

    public abstract String process(MobileEvent event);
}
