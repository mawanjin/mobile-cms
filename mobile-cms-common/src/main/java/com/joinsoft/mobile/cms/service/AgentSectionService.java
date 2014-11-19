package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.mobile.cms.entity.agent.TbSectionAgent;

import com.joinsoft.mobile.cms.repository.agent.AgentSectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * xingsen@join-cn.com
 */
@Service
@Transactional
public class AgentSectionService {
    private Logger logger = LoggerFactory.getLogger(AgentSectionService.class);

    @Resource
    private AgentSectionRepository agentSectionRepository;

    public Page<TbSectionAgent> searchSectionAgent(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbSectionAgent> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbSectionAgent> page = agentSectionRepository.findAll(spec, pageable);
        return page;
    }

    public TbSectionAgent getById(Long id) {
        return agentSectionRepository.findOne(id);
    }

    public void saveAgent(TbSectionAgent form) {
        /*
        TbSectionAgent action = new TbSectionAgent();
        if (form.getId() != null) {
            action = agentSectionRepository.findOne(form.getId());
        }

        form.toEntity(action);
        */

        agentSectionRepository.save(form);
    }

    public void deleteSectionAgent(Long... ids) {
        agentSectionRepository.deleteInId(Arrays.asList(ids));
    }

    public List<TbSectionAgent> getAllAgents() {
        return (List<TbSectionAgent>) agentSectionRepository.findAll();
    }



}
