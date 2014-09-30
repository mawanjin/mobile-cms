package com.joinsoft.mobile.cms.component.receiver.event;

import com.joinsoft.mobile.cms.component.ResponseMessageBuilder;
import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.component.eventbus.replymessage.SystemEventSubscribe;
import com.joinsoft.mobile.cms.dto.mobile.receive.event.MobileEvent;
import com.joinsoft.mobile.cms.dto.mobile.receive.event.SubscribeEvent;
import com.joinsoft.mobile.cms.dto.mobile.receive.event.SubscribeFromQrEvent;
import com.joinsoft.mobile.cms.entity.TbMobileMenu;
import com.joinsoft.mobile.cms.repository.MobileMenuRepository;
import com.joinsoft.mobile.cms.service.mobile.MobileSecurityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * xingsen@join-cn.com
 */
@Component
public class SubscribeEventReceiver extends MobileEventReceiver {
    @Resource
    private MobileSecurityService mobileSecurityService;
    @Resource
    private EventBusService eventBusService;
    @Resource
    private ResponseMessageBuilder responseMessageBuilder;
    @Resource
    private MobileMenuRepository mobileMenuRepository;

    @Override
    public boolean accept(MobileEvent key) {
        if (key instanceof SubscribeEvent || key instanceof SubscribeFromQrEvent) {
            return true;
        }
        return false;
    }

    @Override
    public String process(MobileEvent event) {
        String openId = event.getFrom();
        mobileSecurityService.registerUser(openId);

        UsernamePasswordToken token = new UsernamePasswordToken(openId, openId);
        SecurityUtils.getSubject().login(token);

        eventBusService.fireResponseGuestMessageEvent(new SystemEventSubscribe(openId));
        //如果扩展此处可以回复多图文
//        List<TbMobileMenu> menus = mobileMenuRepository.findByName("热门活动");
//        if (!menus.isEmpty()&& menus.get(0).getNode() != null) {
//           responseMessageBuilder.buildAndSendNews(openId, menus.get(0).getNode().getId());
//        }
        return null;
    }
}
