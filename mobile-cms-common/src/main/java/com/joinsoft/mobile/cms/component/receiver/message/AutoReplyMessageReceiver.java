package com.joinsoft.mobile.cms.component.receiver.message;

import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.dto.mobile.receive.MobileMessage;
import com.joinsoft.mobile.cms.dto.mobile.receive.TextMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * xingsen@join-cn.com
 */
@Component
public class AutoReplyMessageReceiver extends MobileMessageReceiver {
    @Resource
    private EventBusService eventBusService;

    @Override
    public boolean accept(MobileMessage message) {
        if (message instanceof TextMessage) {
            return true;
        }
        return false;
    }

    @Override
    public String process(MobileMessage message) {
        TextMessage textMessage = (TextMessage) message;
        return eventBusService.fireKeywordEvent(textMessage.getTo(), textMessage.getFrom(), textMessage.getContent());
    }
}
