package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbAudit;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AuditRepository extends JpaRepository<TbAudit, Long> {


}
