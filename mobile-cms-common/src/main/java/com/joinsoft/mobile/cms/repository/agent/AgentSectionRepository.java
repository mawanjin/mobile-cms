package com.joinsoft.mobile.cms.repository.agent;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import com.joinsoft.mobile.cms.entity.agent.TbSectionAgent;
import org.springframework.data.jpa.repository.Query;


public interface AgentSectionRepository extends JpaRepository<TbSectionAgent, Long> {
    @Query("FROM TbSectionAgent WHERE sectionName=?1 ")
    TbSectionAgent findByActionName(String sectionName);
}
