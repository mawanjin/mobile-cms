package com.joinsoft.mobile.cms.component.eventbus;

import com.google.common.collect.Lists;
import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.component.ResponseMessageBuilder;
import com.joinsoft.mobile.cms.component.eventbus.replymessage.ExchangeProductEvent;
import com.joinsoft.mobile.cms.component.eventbus.replymessage.ReplyMessageEvent;
import com.joinsoft.mobile.cms.component.eventbus.replymessage.RewardChangeEvent;
import com.joinsoft.mobile.cms.component.eventbus.replymessage.SystemEventSubscribe;
import com.joinsoft.mobile.cms.component.eventbus.reward.*;
import com.joinsoft.mobile.cms.dto.RewardEventResult;
import com.joinsoft.mobile.cms.dto.SystemEventDto;
import com.joinsoft.mobile.cms.entity.TbAutoReplyConfig;
import com.joinsoft.mobile.cms.service.AutoReplyService;
import com.joinsoft.mobile.cms.service.RewardService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Component
public class EventBusService {
    private Logger logger = LoggerFactory.getLogger(EventBusService.class);

    @Resource
    private RewardService rewardService;
    @Resource
    private ResponseMessageBuilder responseMessageBuilder;
    @Resource
    private SecurityService securityService;
    @Resource
    private AutoReplyService autoReplyService;

    private Map<String, SystemEventDto> rewardEventMap = new HashMap<String, SystemEventDto>();
    private Map<String, SystemEventDto> replyMessageMap = new HashMap<String, SystemEventDto>();

    @PostConstruct
    public void init() {
        addEvent(replyMessageMap, new SystemEventSubscribe());
        addEvent(replyMessageMap, new RewardChangeEvent());
        addEvent(replyMessageMap, new ExchangeProductEvent());

        addEvent(rewardEventMap, new SystemEventSign());
        addEvent(rewardEventMap, new SystemEventBound());
        addEvent(rewardEventMap, new SystemEventActivitySign());
        addEvent(rewardEventMap, new SystemEventInvite());
    }

    public RewardEventResult fireRewardEvent(RewardEvent rewardEvent) {
        User user = securityService.getLoginUser();
        return this.fireRewardEvent(rewardEvent, user.getId());
    }

    public RewardEventResult fireRewardEvent(RewardEvent rewardEvent, Long userId) {
        User user = securityService.getUser(userId);
        Assert.notNull(user, "用户不存在");

        String fromOpenId = user.getLoginName();
        logger.info("接收到事件 {} 发送者{}", rewardEvent, fromOpenId);
        RewardEventResult rewardEventResult = new RewardEventResult(new BigDecimal(0), new BigDecimal(0));
        if (StringUtils.isNotEmpty(fromOpenId)) {
            //积分、流量
            rewardEventResult = rewardService.fireEvent(rewardEvent.getKey(), fromOpenId);
            if (rewardEventResult.getPointVal().longValue() != 0 ||
                    rewardEventResult.getTrafficVal().longValue() != 0) {
                logger.info("产生新的奖励 {}", rewardEventResult);
                this.fireResponseGuestMessageEvent(new RewardChangeEvent(fromOpenId));
            }
            logger.info("产生奖励事件 {}", rewardEventResult);
        }
        return rewardEventResult;
    }

    public void fireResponseGuestMessageEvent(ReplyMessageEvent messageEvent) {
        User user = securityService.getLoginUser();
        String fromOpenId = messageEvent.getFrom();
//        if (user != null) {
//            fromOpenId = user.getLoginName();
//        }
        logger.info("接收到事件 {} 发送者{}", messageEvent, fromOpenId);
        if (StringUtils.isNotEmpty(fromOpenId)) {
            //发送消息
         //   responseMessageBuilder.sendTextForGuestMessage(fromOpenId, messageEvent.getKey());
        }
    }

    public List<SystemEventDto> getRewardEventHooks() {
        List<SystemEventDto> r = Lists.newArrayList(rewardEventMap.values());
        Collections.sort(r);
        return r;
    }

    public List<SystemEventDto> getReplyMessageEventHooks() {
        List<SystemEventDto> r = Lists.newArrayList(replyMessageMap.values());
        Collections.sort(r);
        return r;
    }

    public SystemEventDto getRewardEvent(String key) {
        return rewardEventMap.get(key);
    }

    public SystemEventDto getReplyMessageEvent(String key) {
        return replyMessageMap.get(key);
    }

    public String fireKeywordEvent(String to, String from, String keyword) {
        TbAutoReplyConfig autoReplyConfig = autoReplyService.getByKeyword(keyword);
        if (null == autoReplyConfig) {
            logger.warn("无效关键字 {}", keyword);
            return "";
        }
        return responseMessageBuilder.buildContentForResponseMobile(to, from, autoReplyConfig.getNode().getId());
    }

    public SystemEventDto getEvent(String key) {
        SystemEventDto dto = getRewardEvent(key);
        if (dto == null) {
            return getReplyMessageEvent(key);
        }
        return dto;
    }

    protected void addEvent(Map<String, SystemEventDto> map, SystemEvent event) {
        map.put(event.getKey(), new SystemEventDto(event));
    }
}
