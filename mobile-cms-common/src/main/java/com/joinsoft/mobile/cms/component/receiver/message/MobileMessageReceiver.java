package com.joinsoft.mobile.cms.component.receiver.message;

import com.joinsoft.mobile.cms.dto.mobile.receive.MobileMessage;

/**
 * xingsen@join-cn.com
 */
public abstract class MobileMessageReceiver {
    public abstract boolean accept(MobileMessage message);

    public abstract String process(MobileMessage message);
}
