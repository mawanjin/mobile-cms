package com.joinsoft.mobile.cms.service;

import com.google.common.collect.Lists;
import com.joinsoft.mobile.cms.entity.TbAutoReplyConfig;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.enumerate.AutoReplyConfigType;
import com.joinsoft.mobile.cms.repository.AutoReplyConfigRepository;
import com.joinsoft.mobile.cms.repository.content.CmsNodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangxulong on 14-9-7.
 */
@Service
@Transactional
public class AutoReplyService {
    @Resource
    private AutoReplyConfigRepository autoReplyConfigRepository;
    @Resource
    private CmsNodeRepository cmsNodeRepository;

    public void save(Long id, String keyword, String eventKey, AutoReplyConfigType type, Long nodeId) {
        TbCmsNode node = cmsNodeRepository.findOne(nodeId);
        Assert.notNull(node, "内容不存在");

        TbAutoReplyConfig config = new TbAutoReplyConfig();
        if (id != null) {
            config = autoReplyConfigRepository.findOne(id);
        }
        config.setEventKey(eventKey);
        config.setKeyWord(keyword);
        config.setNode(node);
        config.setType(type);
        autoReplyConfigRepository.save(config);

    }

    public void saveKeywordReply(Long id, String keyword, AutoReplyConfigType type, Long nodeId) {
        save(id, keyword, null, type, nodeId);
    }

    public TbAutoReplyConfig getById(Long id) {
        return autoReplyConfigRepository.findOne(id);
    }

    public void delete(Long[] ids) {
        autoReplyConfigRepository.deleteInId(Arrays.asList(ids));
    }

    public List<TbAutoReplyConfig> getAutoReplyConfig() {
        return Lists.newArrayList(autoReplyConfigRepository.findAll());
    }

    public TbAutoReplyConfig getAutoReplyConfigByEventKey(String key) {
        return autoReplyConfigRepository.findByEventKey(key);
    }

    public TbAutoReplyConfig getByKeyword(String keyword) {
        List<TbAutoReplyConfig> configs = autoReplyConfigRepository.findByKeyword("%" + keyword + "%", AutoReplyConfigType.Keyword);
        for (TbAutoReplyConfig config : configs) {
            List<String> keywords = Arrays.asList(config.getKeyWord().split("\\|"));
            if (keywords.contains(keyword)) {
                return config;
            }
        }
        return null;

    }
}
