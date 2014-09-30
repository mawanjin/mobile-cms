package com.joinsoft.mobile.cms.service.content;

import com.joinsoft.mobile.cms.component.VfsComponent;
import com.joinsoft.mobile.cms.entity.content.TbArticle;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.form.ArticleEditForm;
import com.joinsoft.mobile.cms.repository.content.ArticleRepository;
import com.joinsoft.mobile.cms.repository.content.CmsNodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by wangxulong on 14-8-13.
 */
@Transactional
@Component
public class ArticleManager {
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private NodeService nodeService;
    @Resource
    private CmsNodeRepository nodeRepository;
    @Resource
    private VfsComponent vfsComponent;

    public TbArticle getContent(Long nodeId) {
        TbCmsNode node = nodeRepository.findOne(nodeId);
        Assert.notNull(node, "内容不存在");

        TbArticle article = articleRepository.findByMeta(nodeId);
        return article;
    }

    public TbCmsNode saveContent(ArticleEditForm form) {
        TbCmsNode node = nodeService.newOrUpdateNode(form, CmsContentType.Article);

        TbArticle article = new TbArticle();
        article.setNode(node);
        if (form.getId() != null) {
            //更新
            article = articleRepository.findByMeta(form.getId());
        }
        String content = form.getContent();
        article.setContent(removeHtml(form.getContent()));
        article.setSourceUrl(form.getSourceUrl());
        articleRepository.save(article);
        return node;
    }

    public void deleteContent(Long[] ids) {
        nodeService.deleteCmsNode(ids);
    }

    protected String removeHtml(String content){
        if(StringUtils.isNotEmpty(content)){
            content=content.replaceAll("<html>", "").replaceAll("</html>", "");
            content=content.replaceAll("<title>", "").replaceAll("</title>", "");
            content=content.replaceAll("<head>", "").replaceAll("</head>", "");
            content=content.replaceAll("<body>", "").replaceAll("</body>", "");
        }
        return content;
    }
}
