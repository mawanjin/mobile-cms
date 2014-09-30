package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbAction;
import org.springframework.data.jpa.repository.Query;

/**
 * xingsen@join-cn.com
 */
public interface ActionRepository extends JpaRepository<TbAction, Long> {
    @Query("FROM TbAction WHERE actionName=?1 AND isValid=true")
    TbAction findByActionName(String actionName);
}
