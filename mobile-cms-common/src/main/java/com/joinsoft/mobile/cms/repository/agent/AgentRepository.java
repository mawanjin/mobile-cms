package com.joinsoft.mobile.cms.repository.agent;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbAction;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AgentRepository extends JpaRepository<TbAgent, Long> {
    @Query("FROM TbAgent WHERE agentName=?1 ")
    TbAgent findByActionName(String agentName);

    @Query("from TbAgent t where t.section.id=?1")
    List<TbAgent> findByTbSectionAgentId(Long id);
}
