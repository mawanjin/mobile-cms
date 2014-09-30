package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbActionLog;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public interface ActionLogRepository extends JpaRepository<TbActionLog, Long> {
    @Query("SELECT COUNT(*) FROM TbActionLog WHERE user.id=?1 AND action=?2 AND (logTime between ?3 AND ?4)")
    Long countByUserAndActionNameAndBetweenTime(Long id, String actionName, Date begin, Date end);

    @Query("SELECT COUNT(*) FROM TbActionLog WHERE user.id=?1 AND action=?2 AND resource=?3 AND (logTime between ?4 AND ?5)")
    Long countByUserAndActionNameAndBetweenTimeAndResource(Long id, String actionName, String resource, Date begin, Date end);

    @Query("SELECT COUNT(*) FROM TbActionLog WHERE user.id=?1 AND action=?2 ")
    Long countByUserAndNameWithoutCycleLimit(Long id, String actionName);
}
