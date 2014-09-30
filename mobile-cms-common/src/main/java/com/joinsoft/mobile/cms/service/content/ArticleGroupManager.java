package com.joinsoft.mobile.cms.service.content;

import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.component.ResponseMessageBuilder;
import com.joinsoft.mobile.cms.dto.ArticleGroupDto;
import com.joinsoft.mobile.cms.dto.ContentNodeDto;
import com.joinsoft.mobile.cms.entity.TbMobileMenu;
import com.joinsoft.mobile.cms.entity.TbUserProfile;
import com.joinsoft.mobile.cms.entity.content.TbArticleGroup;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.form.ArticleGroupEditForm;
import com.joinsoft.mobile.cms.repository.MobileMenuRepository;
import com.joinsoft.mobile.cms.repository.content.ArticleGroupRepository;
import com.joinsoft.mobile.cms.repository.content.CmsNodeRepository;
import com.joinsoft.mobile.cms.service.mobile.MobileService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangxulong on 14-8-14.
 */
@Transactional
@Component
public class ArticleGroupManager {
    @Resource
    private ArticleGroupRepository articleGroupRepository;
    @Resource
    private CmsNodeRepository cmsNodeRepository;
    @Resource
    private NodeService metaService;
    @Resource
    private CmsNodeRepository nodeRepository;
    @Resource
    private MobileMenuRepository mobileMenuRepository;
    @Resource
    private MobileService mobileService;
    @Resource
    private ResponseMessageBuilder responseMessageBuilder;
    @Resource
    private SecurityService securityService;


    public TbCmsNode saveOrUpdateArticleGroup(ArticleGroupEditForm form) {
        //清除历史数据
        if (form.getNodeIds() == null || form.getNodeIds().isEmpty()) {
            List<Long> nodeIds = new ArrayList<Long>();
            nodeIds.add(0L);
            form.setNodeIds(nodeIds);
        }
        List<TbArticleGroup> articleGroups = articleGroupRepository.findByNodeAndArticleNNotIn(form.getId(),
                form.getNodeIds());
        List<Long> ids = new ArrayList<Long>();
        for (TbArticleGroup articleGroup : articleGroups) {
            ids.add(articleGroup.getId());
        }
        articleGroupRepository.deleteInId(ids);

        //保存
        form.setTitle("未命名");
        TbCmsNode articleGroupNode = metaService.newOrUpdateNode(form, CmsContentType.ArticleGroup);
        Integer maxCount = articleGroupRepository.countArticleByNode(form.getId()).intValue();

        //查找第一条图文的标题
        Integer minOrder = Integer.MAX_VALUE;
        String minxArticleTitle = "";
        //添加/更新数据
        for (Long nodeId : form.getNodeIds()) {
            TbArticleGroup articleGroup = articleGroupRepository.findMetaAndArticleMeta(form.getId(), nodeId);
            if (articleGroup == null) {
                articleGroup = new TbArticleGroup();
                TbCmsNode node = nodeRepository.findOne(nodeId);
                if (node == null) {
                    //跳过不存在的文章(守卫)
                    continue;
                }

                articleGroup.setContentNode(node);
                articleGroup.setNode(articleGroupNode);
                articleGroup.setOrderIndex(maxCount++);
            } else {
                //更新
                articleGroup.setOrderIndex(form.getOrderMap().get(nodeId));
            }
            if (articleGroup.getOrderIndex() < minOrder) {
                minOrder = articleGroup.getOrderIndex();
                minxArticleTitle = articleGroup.getContentNode().getTitle();
            }
            articleGroupRepository.save(articleGroup);
        }
        //更新标题
        articleGroupNode.setTitle(minxArticleTitle);
        cmsNodeRepository.save(articleGroupNode);

        return articleGroupNode;
    }

    public List<ArticleGroupDto> getArticleGroup(Long metaId) {
        List<ArticleGroupDto> articleGroupDtos = new ArrayList<ArticleGroupDto>();
        List<TbArticleGroup> articles = articleGroupRepository.findMultiArticleByMeta(metaId);
        for (TbArticleGroup article : articles) {
            articleGroupDtos.add(ArticleGroupDto.fromEntity(article));
        }
        return articleGroupDtos;
    }

    public void removeArticle(Long id, Long nodeId) {
        TbArticleGroup group = articleGroupRepository.findMetaAndArticleMeta(id, nodeId);
        articleGroupRepository.delete(group.getId());
    }

    public void deleteContent(Long[] ids) {
        nodeRepository.deleteInId(Arrays.asList(ids));
    }

    public List<ContentNodeDto> getArticleGroupNode(Long cmsMetaId) {
        User user = securityService.getLoginUser();
        Assert.notNull(user, "用户没有登录");
        List<ContentNodeDto> contentNodeDtoList = new ArrayList<ContentNodeDto>();
        List<TbCmsNode> nodes = articleGroupRepository.findArticleByMeta(cmsMetaId);
        if (nodes == null) {
            return contentNodeDtoList;
        }
        for (TbCmsNode node : nodes) {
            ContentNodeDto dto = responseMessageBuilder.buildContentNodeDtoFromCmsNode(node, user.getLoginName());
            contentNodeDtoList.add(dto);
        }
        return contentNodeDtoList;
    }

    public List<ContentNodeDto> getArticlesForFront() {
        List<TbMobileMenu> menus = mobileMenuRepository.findByName("热门活动");
        if (menus.isEmpty() ||
                menus.get(0).getNode() == null) {
            return new ArrayList<ContentNodeDto>();
        }

        return this.getArticleGroupNode(menus.get(0).getNode().getId());
    }
}
