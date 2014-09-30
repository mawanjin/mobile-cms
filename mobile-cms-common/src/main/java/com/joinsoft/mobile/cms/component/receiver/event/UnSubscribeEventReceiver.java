package com.joinsoft.mobile.cms.component.receiver.event;

import com.joinsoft.mobile.cms.dto.mobile.receive.event.MobileEvent;
import com.joinsoft.mobile.cms.dto.mobile.receive.event.UnsubscribeEvent;
import org.springframework.stereotype.Component;

/**
 * xingsen@join-cn.com
 */
@Component
public class UnSubscribeEventReceiver extends MobileEventReceiver {

    @Override
    public boolean accept(MobileEvent key) {
        if (key instanceof UnsubscribeEvent) {
            return true;
        }
        return false;
    }

    @Override
    public String process(MobileEvent event) {
        return null;
    }
}
