package com.joinsoft.mobile.cms.service.mobile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.joinsoft.mobile.cms.component.EasyJson;
import com.joinsoft.mobile.cms.component.Http;
import com.joinsoft.mobile.cms.component.receiver.event.MobileEventReceiver;
import com.joinsoft.mobile.cms.component.receiver.message.MobileMessageReceiver;
import com.joinsoft.mobile.cms.dto.GroupDto;
import com.joinsoft.mobile.cms.dto.MobileMenuButton;
import com.joinsoft.mobile.cms.dto.mobile.receive.*;
import com.joinsoft.mobile.cms.dto.mobile.receive.event.*;
import com.joinsoft.mobile.cms.entity.TbCache;
import com.joinsoft.mobile.cms.repository.CacheRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;

/**
 * xingsen@join-cn.com
 */
@Component
public class MobileService {
    private Logger logger = LoggerFactory.getLogger(MobileService.class);

    @Value("${config.weixin.appId}")
    private String appId;
    @Value("${config.weixin.appSecret}")
    private String appSecret;
    @Value("${config.weixin.callbackToken}")
    private String callBackToken;

    @Value("${config.weixin.apiUrl}")
    private String apiUrl;
    @Value("${config.weixin.openId.url}")
    private String openIdUrl;
    @Value("${config.weixin.access_token.url}")
    private String authAccessTokenUrl;
    @Value("${config.weixin.userinfo}")
    private String userInfoUrl;
    @Value("${config.weixin.group.url}")
    private String groupUrl;
    @Value("${config.cms.pic}")
    private String cmsPic;
    @Value("${config.cms.article}")
    private String cmsArticle;

    @Resource
    private EasyJson easyJson;
    @Resource
    private CacheRepository cacheRepository;
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private Http http;

    public String getOrNewToken() {
        String weixinTokenCacheKey = "weixin.access.token";
        TbCache cache = cacheRepository.findByCacheKey(weixinTokenCacheKey);
        if (cache == null || cache.isExpires()) {
            String url = String.format(apiUrl + "/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
                    appId, appSecret);
            JsonNode tokenNode = http.get(url);
            if (cache == null) {
                cache = new TbCache();
                cache.setCacheKey(weixinTokenCacheKey);
            }
            cache.setCacheValue(tokenNode.get("access_token").asText());
            cache.setCacheTime(new Date());
            cache.setExpiresTime(3600);

            cacheRepository.save(cache);
        }
        return cache.getCacheValue();
    }

    public List<MobileMenuButton> getMenu() {
        String url = String.format(apiUrl + "/cgi-bin/menu/get?access_token=" + getOrNewToken());
        JsonNode menuNode = http.get(url);
        try {
            return easyJson.treeToValue(menuNode.get("menu").get("button"),
                    new ArrayList<MobileMenuButton>().getClass());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMenu(List<MobileMenuButton> menu) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("button", menu);
        String json = easyJson.toJson(map);
        logger.debug("MobileMenu json string: {}", json);
        String url = String.format(apiUrl + "/cgi-bin/menu/create?access_token=" + getOrNewToken());
        http.post(url, json);
    }

    public String onMessage(InputStream xmlInputStream) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(xmlInputStream);
            Element root = document.getRootElement();
            String msgType = root.elementText("MsgType");
            String from = root.elementText("FromUserName");
            String to = root.elementText("ToUserName");
            Date createTime = new Date(Long.parseLong(root.element("CreateTime").getText()));
            String msgId = root.elementText("MsgId");
            if (msgType.equalsIgnoreCase("text")) {
                TextMessage message = new TextMessage(from, to, msgId, createTime);
                message.setContent(root.elementText("Content"));
                return sendMessage(message);
            }
            if (msgType.equalsIgnoreCase("image")) {
                ImageMessage message = new ImageMessage(from, to, msgId, createTime);
                message.setPicUrl(root.elementText("PicUrl"));
                message.setMediaId(root.elementText("MediaId"));
                return sendMessage(message);
            }
            if (msgType.equalsIgnoreCase("voice")) {
                VoiceMessage message = new VoiceMessage(from, to, msgId, createTime);
                message.setFormat(root.elementText("Format"));
                message.setMediaId(root.elementText("MediaId"));
                return sendMessage(message);
            }
            if (msgType.equalsIgnoreCase("video")) {
                VideoMessage message = new VideoMessage(from, to, msgId, createTime);
                message.setThumbMediaId(root.elementText("ThumbMediaId"));
                message.setMediaId(root.elementText("MediaId"));
                return sendMessage(message);
            }
            if (msgType.equalsIgnoreCase("location")) {
                LocationMessage message = new LocationMessage(from, to, msgId, createTime);
                message.setX(root.elementText("Location_X"));
                message.setY(root.elementText("Location_Y"));
                message.setScale(root.elementText("Scale"));
                message.setLabel(root.elementText("Label"));
                return sendMessage(message);
            }
            if (msgType.equalsIgnoreCase("url")) {
                UrlMessage message = new UrlMessage(from, to, msgId, createTime);
                message.setTitle(root.elementText("Title"));
                message.setDescription(root.elementText("Description"));
                message.setUrl(root.elementText("Url"));
                return sendMessage(message);
            }
            if (msgType.equalsIgnoreCase("event")) {
                String event = root.elementText("Event");
                return onEvent(from, to, msgId, createTime, event, root);
            }
            logger.warn("未识别的消息类型 {}", msgType);
            return null;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public String getOpenId(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        String url = String.format("%s?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                authAccessTokenUrl, appId, appSecret, code);
        JsonNode jsonNode = http.get(url);
        return jsonNode.get("openid").asText();
    }

    public String buildGetCodeUrl(String reqURI) {
        return String.format("%s?appid=" +
                "%s&redirect_uri=%s&response_type=code&scope=snsapi_base" +
                "&state=%s#wechat_redirect", openIdUrl, appId, reqURI, reqURI);
    }

    public boolean checkSignature(String signature, String timestamp, String nonce) {
        logger.info(String.format("token=%s, timestamp=%s, nonce=%s, signature=%s", callBackToken, timestamp, nonce, signature));
        String[] arr = new String[]{callBackToken, timestamp, nonce};
        //排序
        Arrays.sort(arr);
        String digestStr = DigestUtils.shaHex(StringUtils.join(arr));
        return digestStr.equalsIgnoreCase(signature);
    }

    public JsonNode getUserInfo(String openId) {
        JsonNode node = http.get(String.format("%s?access_token=%s&openid=%s", userInfoUrl, getOrNewToken(), openId));
        return node;
    }

    public String getNickName(String openId) {
        JsonNode node = getUserInfo(openId);
        return node.get("nickname").asText();
    }

    public List<GroupDto> getAllGroup() {
        String url = String.format("%s/get?access_token=%s", groupUrl, getOrNewToken());
        JsonNode node = http.get(url);

        try {
            return easyJson.treeToValue(node.get("groups"),
                    new ArrayList<GroupDto>().getClass());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getArticleUrl(Long id) {
        if (id == null) {
            return null;
        }
        return cmsArticle.replace("@{article}", id.toString());
    }

    public String getPicUrl(String picPath) {
        if (StringUtils.isEmpty(picPath)) {
            return null;
        }
        return String.format(cmsPic.replace("@{pic}", picPath)+"?r=%s",System.currentTimeMillis()) ;
    }

    protected String onEvent(String from, String to, String msgId, Date createTime, String eventName, Element root) {
        if (eventName.equalsIgnoreCase("subscribe")) {
            if (root.element("EventKey") != null) {
                SubscribeFromQrEvent event = new SubscribeFromQrEvent(eventName, from, to, msgId, createTime);
                event.setTicket(root.elementText("Ticket"));
                event.setEventKey(root.elementText("EventKey"));
                return sendEvent(event);
            }
            SubscribeEvent message = new SubscribeEvent(eventName, from, to, msgId, createTime);
            return sendEvent(message);
        }
        if (eventName.equalsIgnoreCase("unsubscribe")) {
            UnsubscribeEvent event = new UnsubscribeEvent(eventName, from, to, msgId, createTime);
            return sendEvent(event);
        }
        if (eventName.equalsIgnoreCase("scan")) {
            ScanEvent event = new ScanEvent(eventName, from, to, msgId, createTime);
            event.setTicket(root.elementText("Ticket"));
            event.setEventKey(root.elementText("EventKey"));
            return sendEvent(event);
        }
        if (eventName.equalsIgnoreCase("location")) {
            LocationEvent event = new LocationEvent(eventName, from, to, msgId, createTime);
            event.setLatitude(root.elementText("Latitude"));
            event.setLongitude(root.elementText("Longitude"));
            event.setPrecision(root.elementText("Precision"));
            return sendEvent(event);
        }
        if (eventName.equalsIgnoreCase("click")) {
            ClickEvent event = new ClickEvent(eventName, from, to, msgId, createTime);
            event.setEventKey(root.elementText("EventKey"));
            return sendEvent(event);
        }
        if (eventName.equalsIgnoreCase("view")) {
            ViewEvent event = new ViewEvent(eventName, from, to, msgId, createTime);
            event.setEventKey(root.elementText("EventKey"));
            return sendEvent(event);
        }
        logger.warn("未识别的事件类型 {}", eventName);
        return "";
    }

    protected String sendEvent(MobileEvent event) {
        logger.info("接收到 event {} class{}", event, event.getClass());
        Map<String, MobileEventReceiver> receivers = applicationContext.getBeansOfType(MobileEventReceiver.class);
        logger.info("eventReceiver total {}", receivers.values().size());
        for (MobileEventReceiver eventReceiver : receivers.values()) {
            //好像只有这种方法
            logger.info("try accept eventReceiver {}", eventReceiver);
            if (eventReceiver.accept(event)) {
                return eventReceiver.process(event);
            }
        }
        return null;
    }

    protected String sendMessage(MobileMessage message) {
        logger.info("接收到 message {}", message);
        Map<String, MobileMessageReceiver> receivers = applicationContext.getBeansOfType(MobileMessageReceiver.class);
        for (MobileMessageReceiver messageReceiver : receivers.values()) {
            if (messageReceiver.accept(message)) {
                return messageReceiver.process(message);
            }
        }
        return null;
    }
}
