package com.joinsoft.mobile.cms.service;

import com.joinsoft.framework.orm.DynamicSpecifications;
import com.joinsoft.framework.orm.SearchFilter;
import com.joinsoft.framework.security.SecurityService;
import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.component.eventbus.EventBusService;
import com.joinsoft.mobile.cms.dto.SystemEventDto;
import com.joinsoft.mobile.cms.entity.TbAction;
import com.joinsoft.mobile.cms.entity.TbActionLog;
import com.joinsoft.mobile.cms.entity.TbConfig;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import com.joinsoft.mobile.cms.entity.enumerate.ActionCycle;
import com.joinsoft.mobile.cms.entity.enumerate.ValueType;
import com.joinsoft.mobile.cms.form.ActionEditForm;
import com.joinsoft.mobile.cms.repository.ActionLogRepository;
import com.joinsoft.mobile.cms.repository.ActionRepository;
import com.joinsoft.mobile.cms.repository.ConfigRepository;
import com.joinsoft.mobile.cms.repository.agent.AgentRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * xingsen@join-cn.com
 */
@Service
@Transactional
public class AgentService {
    private Logger logger = LoggerFactory.getLogger(AgentService.class);

    @Resource
    private AgentRepository agentRepository;



    public Page<TbAgent> searchAgent(Map<String, Object> searchParams, Pageable pageable) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<TbAgent> spec = DynamicSpecifications.bySearchFilter(filters.values());
        Page<TbAgent> page = agentRepository.findAll(spec, pageable);
        return page;
    }

    public TbAgent getById(Long id) {
        return agentRepository.findOne(id);
    }

    public void saveAgent(TbAgent form) {
        /*
        TbAgent action = new TbAgent();
        if (form.getId() != null) {
            action = agentRepository.findOne(form.getId());
        }

        form.toEntity(action);
        */

        agentRepository.save(form);
    }

    public void deleteAgent(Long... ids) {
        agentRepository.deleteInId(Arrays.asList(ids));
    }

    public List<TbAgent> getAllAgents() {
        return (List<TbAgent>) agentRepository.findAll();
    }



}
