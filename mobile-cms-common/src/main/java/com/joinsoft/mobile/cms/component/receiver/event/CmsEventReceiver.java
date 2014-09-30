package com.joinsoft.mobile.cms.component.receiver.event;

import com.joinsoft.mobile.cms.component.ResponseMessageBuilder;
import com.joinsoft.mobile.cms.dto.mobile.receive.event.ClickEvent;
import com.joinsoft.mobile.cms.dto.mobile.receive.event.MobileEvent;
import com.joinsoft.mobile.cms.entity.enumerate.MobileMenuType;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * xingsen@join-cn.com
 */
@Component
public class CmsEventReceiver extends MobileEventReceiver {
    private Logger logger = LoggerFactory.getLogger(CmsEventReceiver.class);
    @Resource
    private ResponseMessageBuilder responseMessageBuilder;

    @Override
    public boolean accept(MobileEvent event) {
        if (!(event instanceof ClickEvent)) {
            return false;
        }
        ClickEvent clickEvent = (ClickEvent) event;
        logger.info("accept {} {}", event, clickEvent.getEventKey());
        return clickEvent.getEventKey().startsWith(MobileMenuType.CMS.getPrefix());
    }

    @Override
    public String process(MobileEvent event) {
        ClickEvent clickEvent = (ClickEvent) event;
        String key = clickEvent.getEventKey();
        String id = key.substring(MobileMenuType.CMS.getPrefix().length());
        Long idNumber = NumberUtils.toLong(id, 0);
        if (idNumber.equals(0)) {
            logger.warn("查找id出错 {} {}", key, id);
            return null;
        }
        return responseMessageBuilder.buildContentForResponseMobile(event.getTo(), event.getFrom(), idNumber);
    }

}
