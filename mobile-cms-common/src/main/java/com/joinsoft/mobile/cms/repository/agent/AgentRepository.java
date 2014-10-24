package com.joinsoft.mobile.cms.repository.agent;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbAction;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import org.springframework.data.jpa.repository.Query;


public interface AgentRepository extends JpaRepository<TbAgent, Long> {
    @Query("FROM TbAgent WHERE agentName=?1 ")
    TbAgent findByActionName(String agentName);
}
