package com.joinsoft.mobile.cms.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.framework.security.repository.UserRepository;
import com.joinsoft.framework.util.Json;
import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.component.template.VelocityTemplate;
import com.joinsoft.mobile.cms.dto.*;
import com.joinsoft.mobile.cms.dto.template.UserMessageTemplate;
import com.joinsoft.mobile.cms.entity.TbAutoReplyConfig;
import com.joinsoft.mobile.cms.entity.TbCache;
import com.joinsoft.mobile.cms.entity.TbUserProfile;
import com.joinsoft.mobile.cms.entity.content.TbArticle;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.content.TbText;
import com.joinsoft.mobile.cms.entity.enumerate.MediaType;
import com.joinsoft.mobile.cms.repository.CacheRepository;
import com.joinsoft.mobile.cms.repository.UserProfileRepository;
import com.joinsoft.mobile.cms.repository.content.ArticleGroupRepository;
import com.joinsoft.mobile.cms.repository.content.ArticleRepository;
import com.joinsoft.mobile.cms.repository.content.CmsNodeRepository;
import com.joinsoft.mobile.cms.repository.content.TextRepository;
import com.joinsoft.mobile.cms.service.AutoReplyService;
import com.joinsoft.mobile.cms.service.RewardService;
import com.joinsoft.mobile.cms.service.content.ArticleGroupManager;
import com.joinsoft.mobile.cms.service.content.CmsContentType;
import com.joinsoft.mobile.cms.service.content.TextManager;
import com.joinsoft.mobile.cms.service.mobile.MobileService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: wujun
 * Date: 2014/8/24
 */
@Component
public class ResponseMessageBuilder {
    public static final String GroupMessagePrefix = CmsContentType.ArticleGroup.name().toLowerCase() + "-";
    private Logger logger = LoggerFactory.getLogger(ResponseMessageBuilder.class);

    @Value("${config.weixin.media.upload.url}")
    private String mediaUploadUrl;
    @Value("${config.weixin.news.upload.url}")
    private String newsUploadUrl;
    @Value("${config.weixin.mass.url}")
    private String massUrl;
    @Value("${config.weixin.custom}")
    private String guestMessageUrl;

    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private ArticleGroupRepository articleGroupRepository;
    @Resource
    private CmsNodeRepository nodeRepository;
    @Resource
    private EasyJson easyJson;
    @Resource
    private Http http;
    @Resource
    private CacheRepository cacheRepository;
    @Resource
    private VfsComponent vfsComponent;
    @Resource
    private TextManager textManager;
    @Resource
    private VelocityTemplate velocityTemplate;
    @Resource
    private EventBusService eventBusService;
    @Resource
    private UserProfileRepository userProfileRepository;
    @Resource
    private RewardService rewardService;
    @Resource
    private SecurityService securityService;
    @Resource
    private AutoReplyService autoReplyService;
    @Resource
    private MobileService mobileService;
    @Resource
    private TextRepository textRepository;
    @Resource
    private ArticleGroupManager articleGroupManager;
    @Resource
    private UserRepository userRepository;

    /**
     * 主动发送客服消息
     */
    public void sendTextForGuestMessage(String to, String key) {
        String accessToken = mobileService.getOrNewToken();
        TbAutoReplyConfig eventConfig = autoReplyService.getAutoReplyConfigByEventKey(key);
        SystemEventDto systemEventDto = eventBusService.getEvent(key);
        if (systemEventDto == null) {
            logger.warn("{}不存在", systemEventDto);
            return;
        }
        if (eventConfig == null) {
            logger.warn("自动回复没有配置 {}", key);
            return;
        }
        TbCmsNode cmsNode = eventConfig.getNode();
        if (cmsNode == null) {
            logger.warn("关联内容不存在 {}", key);
            return;
        }
        TbText tbText = textManager.getContent(cmsNode.getId());
        Assert.notNull(tbText, "消息不存在");

        //渲染模版所需要的数据
        Map<String, Object> renderData = getMessageTemplateData(systemEventDto, to);
        String message = velocityTemplate.renderText(tbText.getContent(), renderData);
        if (StringUtils.isNotEmpty(message)) {
            Map<String, String> content = new HashMap<String, String>();
            logger.info("产生客服消息事件 {}", message);
            content.put("content", message);
            JsonNode node = Json.toJson(getData(to, "text", content));
            http.post(guestMessageUrl + "?access_token=" + accessToken, node.toString());
        }
    }

    /**
     * 群发消息
     */
    public JsonNode sendArticleGroupForMass(Long groupId, Long nodeId) {
        String accessToken = mobileService.getOrNewToken();
        String mediaId = getOrUploadMassGroupMessage(nodeId, accessToken);
        Map<String, Object> message = new HashMap<String, Object>();
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("group_id", groupId.toString());
        Map<String, Object> mpnews = new HashMap<String, Object>();
        mpnews.put("media_id", mediaId);

        message.put("filter", filter);
        message.put("mpnews", mpnews);
        message.put("msgtype", "mpnews");
        String massMessage = easyJson.toJson(message);
        String url = String.format(massUrl + "?access_token=%s", accessToken);
        logger.info("群发消息 {}", massMessage);
        return http.post(url, massMessage);
    }

    /**
     * 菜单关联CMS类型消息
     */
    public String buildContentForResponseMobile(String from, String to, Long id) {
        TbCmsNode node = nodeRepository.findOne(id);
        CmsContentType nodeType = CmsContentType.valueOf(node.getType());
        if (nodeType.equals(CmsContentType.Text)) {
            TbText text = textRepository.findByMeta(id);
            return this.buildTextMessage(from, to, text.getContent());
        }
        if (nodeType.equals(CmsContentType.Article) || nodeType.equals(CmsContentType.Poll)) {
            return this.buildNode(from, to, node.getId());
        }
        if (nodeType.equals(CmsContentType.ArticleGroup)) {
            return buildNode(from, to, buildArticleGroupContentNodeDtos(node.getId(), to));
        }
        logger.warn("未识别的类型 {} {}", id, nodeType.getTitle());
        return null;
    }

    protected String xmlToString(Document document) {
        return document.asXML();
    }

    protected Element buildArticleItem(String title, String description, String articleUrl, String picUrl) {
        Document document = DocumentHelper.createDocument();
        Element itemRoot = document.addElement("item");
        Element titleElement = itemRoot.addElement("Title");
        titleElement.addCDATA(title);
        Element descriptionElement = itemRoot.addElement("Description");
        descriptionElement.addCDATA(description);
        Element picUrlElement = itemRoot.addElement("PicUrl");
        picUrlElement.addCDATA(picUrl);
        Element urlElement = itemRoot.addElement("Url");
        urlElement.addCDATA(articleUrl);
        return itemRoot;
    }

    protected Map<String, Object> getMessageTemplateData(SystemEventDto systemEventDto, String to) {
        Map<String, Object> renderData = new HashMap<String, Object>();
        UserMessageTemplate userMessageTemplate = new UserMessageTemplate();
        User user = userRepository.findByLoginName(to);
        TbUserProfile userProfile = userProfileRepository.findByUser(user.getId());
        if (null != userProfile) {
            userMessageTemplate.setMobilePhone(userProfile.getMobile());
        }
        userMessageTemplate.setName(user.getName());

        MyPointDto pointDto = rewardService.getMyPoint(null, null);
        userMessageTemplate.setCurrentPoint(pointDto.getTotalPoint());

        MyTrafficDto trafficDto = rewardService.getMyTraffic(null, null);
        userMessageTemplate.setCurrentTraffic(trafficDto.getAvailableTraffic());

        renderData.put("user", userMessageTemplate);
        renderData.put("message", systemEventDto.getDescription());
        return renderData;
    }

    protected Map<String, Object> getData(String to, String type, Map<String, String> data) {
        Map<String, Object> send = new HashMap<String, Object>();
        send.put("touser", to);
        send.put("msgtype", type);
        send.put(type, data);
        return send;
    }

    @Transactional
    protected String getOrUploadMassGroupMessage(Long id, String accessToken) {
        String key = GroupMessagePrefix + id;
        TbCache cache = cacheRepository.findByCacheKey(key);
        if (cache == null || cache.isExpires()) {
            String cacheValue = uploadMassGroupMessage(id, accessToken);
            if (cache == null) {
                cache = new TbCache();
                cache.setCacheKey(key);
            }
            cache.setCacheValue(cacheValue);
            cache.setCacheTime(new Date());
            cache.setExpiresTime(3 * 24 * 59 * 60);

            cacheRepository.save(cache);
        }
        return cache.getCacheValue();
    }

    protected String uploadImage(VirtualFile image, String accessToken) {
        String url = String.format(mediaUploadUrl + "?access_token=%s&type=%s",
                accessToken, MediaType.Image.name().toLowerCase());
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("image", image.getRealFile(), ContentType.MULTIPART_FORM_DATA, image.getName());
        HttpEntity multipart = builder.build();
        JsonNode jsonNode = http.multipartPost(url, multipart);
        return jsonNode.get("media_id").asText();
    }

    protected String uploadMassGroupMessage(Long id, String accessToken) {
        List<TbCmsNode> nodes = articleGroupRepository.findArticleByMeta(id);
        if (nodes == null) {
            logger.warn("{}多图文文章不存在", id);
            return "";
        }
        List<MassGroupArticleDto> massGroupArticleDtos = new ArrayList<MassGroupArticleDto>();
        for (TbCmsNode node : nodes) {
            MassGroupArticleDto massGroupArticleDto = new MassGroupArticleDto();
            VirtualFile image = vfsComponent.getNodeImage(node.getType(), node.getId(),
                    FilenameUtils.getExtension(node.getPicPath()));
            if (!image.exists()) {
                throw new RuntimeException(String.format("多图文中：%s 图片不存在", node.getId()));
            }
            massGroupArticleDto.setThumbMediaId(uploadImage(image, accessToken));
            massGroupArticleDto.setAuthor(node.getAuthorName());
            massGroupArticleDto.setDigest(node.getSummary());
            massGroupArticleDto.setTitle(node.getTitle());
            massGroupArticleDto.setContentSourceUrl(mobileService.getArticleUrl(node.getId()));
            massGroupArticleDto.setShowCoverPic("1");
            massGroupArticleDtos.add(massGroupArticleDto);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articles", massGroupArticleDtos);
        String massGroupMessage = easyJson.toJson(map);
        String url = String.format(newsUploadUrl + "?access_token=%s", accessToken);
        JsonNode newsNode = http.post(url, massGroupMessage);
        return newsNode.get("media_id").asText();
    }

    protected String buildTextMessage(String from, String to, String content) {
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("xml");
        Element toUserNameElement = rootElement.addElement("ToUserName");
        toUserNameElement.addCDATA(to);
        Element fromUserNameElement = rootElement.addElement("FromUserName");
        fromUserNameElement.addCDATA(from);
        Element createTimeElement = rootElement.addElement("CreateTime");
        createTimeElement.setText(String.valueOf(System.currentTimeMillis()));
        Element msgTypeElement = rootElement.addElement("MsgType");
        msgTypeElement.addCDATA("text");
        Element contentElement = rootElement.addElement("Content");
        contentElement.addCDATA(content);
        return xmlToString(document);
    }

    protected String buildNode(String from, String to, ContentNodeDto contentNodeDto) {
        return buildNode(from, to, Arrays.asList(contentNodeDto));
    }

    protected String buildNode(String from, String to, List<ContentNodeDto> contentNodeDtoList) {
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("xml");
        Element toUserNameElement = rootElement.addElement("ToUserName");
        toUserNameElement.addCDATA(to);
        Element fromUserNameElement = rootElement.addElement("FromUserName");
        fromUserNameElement.addCDATA(from);
        Element createTimeElement = rootElement.addElement("CreateTime");
        createTimeElement.setText(String.valueOf(System.currentTimeMillis()));
        Element msgTypeElement = rootElement.addElement("MsgType");
        msgTypeElement.addCDATA("news");
        Element articleCountElement = rootElement.addElement("ArticleCount");
        articleCountElement.setText(String.valueOf(contentNodeDtoList.size()));
        Element articlesElement = rootElement.addElement("Articles");
        for (ContentNodeDto article : contentNodeDtoList) {
            Element articleElement = buildArticleItem(article.getTitle(), article.getSummary(),
                    article.getContentUrl(), article.getPicUrl());
            articlesElement.add(articleElement);
        }
        return xmlToString(document);
    }

    protected String buildNode(String from, String to, Long cmsMetaId) {
        TbCmsNode cmsNode = nodeRepository.findOne(cmsMetaId);
        if (cmsNode == null) {
            logger.warn("{}单图文文章不存在", cmsMetaId);
            return "";
        }
        ContentNodeDto dto = buildContentNodeDtoFromCmsNode(cmsNode, to);
        return buildNode(from, to, dto);
    }

    public ContentNodeDto buildContentNodeDtoFromCmsNode(TbCmsNode node, String to) {
        ContentNodeDto dto = new ContentNodeDto();
        dto.setTitle(node.getTitle());
        dto.setSummary(node.getSummary());
        String articleUrl = buildRedirectUrl(mobileService.getArticleUrl(node.getId()), to);
        if (CmsContentType.valueOf(node.getType()).equals(CmsContentType.Article)) {
            TbArticle article = articleRepository.findByMeta(node.getId());
            if (StringUtils.isNotEmpty(article.getSourceUrl())) {
                articleUrl = buildRedirectUrl(article.getSourceUrl(), to);
            }
        }
        String picUrl = mobileService.getPicUrl(node.getPicPath());
        dto.setContentUrl(articleUrl);
        dto.setPicUrl(picUrl);
        return dto;
    }

    protected List<ContentNodeDto> buildArticleGroupContentNodeDtos(Long cmsNodeId, String to) {
        List<ContentNodeDto> contentNodeDtoList = new ArrayList<ContentNodeDto>();
        List<TbCmsNode> nodes = articleGroupRepository.findArticleByMeta(cmsNodeId);
        if (nodes == null) {
            return contentNodeDtoList;
        }
        for (TbCmsNode node : nodes) {
            contentNodeDtoList.add(buildContentNodeDtoFromCmsNode(node, to));
        }
        return contentNodeDtoList;
    }

    protected String buildRedirectUrl(String url, String to) {
        Long time = new Date().getTime();
        String signature = new String(DigestUtils.md5Hex(String.format("%s@%s", to, time.longValue())));
        String redirectUrl = "%s?openId=%s&time=%s&signature=%s";
        if (-1 != url.indexOf("?")) {
            redirectUrl = "%s&openId=%s&time=%s&signature=%s";
        }
        redirectUrl = String.format(redirectUrl, url, to, time, signature);
        logger.info("单图文中的sourceURL: {}", redirectUrl);
        return redirectUrl;
    }

    @Transactional
    public void buildAndSendNews(String to, Long nodeId) {
        TbCmsNode node = nodeRepository.findOne(nodeId);
        Assert.notNull(node, "消息不内容不存在");
        List<TbCmsNode> cmsNodes = new ArrayList<TbCmsNode>();
        CmsContentType type = CmsContentType.valueOf(node.getType());
        switch (type) {
            case Article:
                cmsNodes.add(nodeRepository.findOne(nodeId));
                break;
            case ArticleGroup:
                cmsNodes = articleGroupRepository.findArticleByMeta(nodeId);
                break;
            case Poll:
                cmsNodes.add(nodeRepository.findOne(nodeId));
                break;
        }
        List<ContentNodeDto> contentNodeDtos = new ArrayList<ContentNodeDto>();
        for (TbCmsNode cmsNode : cmsNodes) {
            contentNodeDtos.add(buildContentNodeDtoFromCmsNode(cmsNode, to));
        }
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("touser", to);
        message.put("msgtype", "news");
        Map<String, Object> articleMap = new HashMap<String, Object>();
        articleMap.put("articles", contentNodeDtos);
        message.put("news", articleMap);

        String news = easyJson.toJson(message);
        logger.info("主动发送图文消息：{}", news);
        http.post(guestMessageUrl + "?access_token=" + mobileService.getOrNewToken(), news);
    }

}
