package com.joinsoft.mobile.cms.service.content;

import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.content.TbText;
import com.joinsoft.mobile.cms.form.TextEditForm;
import com.joinsoft.mobile.cms.repository.content.CmsNodeRepository;
import com.joinsoft.mobile.cms.repository.content.TextRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * xingsen@join-cn.com
 */
@Transactional
@Component
public class TextManager {
    @Resource
    private TextRepository textRepository;
    @Resource
    private CmsNodeRepository nodeRepository;
    @Resource
    private NodeService nodeService;

    public TbText getContent(Long nodeId) {
        TbCmsNode node = nodeRepository.findOne(nodeId);
        Assert.notNull(node, "内容不存在");

        return textRepository.findByMeta(nodeId);
    }

    public TbCmsNode saveContent(TextEditForm form) {
        TbCmsNode node = nodeService.newOrUpdateNode(form, CmsContentType.Text);

        TbText text = new TbText();
        text.setNode(node);
        if (form.getId() != null) {
            //更新
            text = textRepository.findByMeta(form.getId());
        }

        text.setContent(form.getContent().replaceAll("<br />", "").replaceAll("&nbsp;", ""));//剔除换行
        textRepository.save(text);
        return node;
    }

    public void deleteContent(Long[] ids) {
        nodeRepository.deleteInId(Arrays.asList(ids));
    }
}
