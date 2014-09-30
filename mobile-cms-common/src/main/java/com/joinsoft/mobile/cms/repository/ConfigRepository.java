package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbConfig;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * xingsen@join-cn.com
 */
public interface ConfigRepository extends JpaRepository<TbConfig, Long> {
    @Query("FROM TbConfig c WHERE c.name=?1 AND c.action IS NULL")
    TbConfig findByName(String name);

    @Query("FROM TbConfig c WHERE c.name=?1 AND c.action.id=?2")
    TbConfig findByNameAndAction(String name, Long actionId);

    @Modifying
    @Query("DELETE FROM TbConfig  WHERE action.id=?1")
    void deleteByAction(Long actionId);

    @Query("FROM TbConfig c WHERE c.action.id=?1")
    List<TbConfig> findByAction(Long actionId);

    @Query("FROM TbConfig c WHERE c.action IS NULL")
    List<TbConfig> findGlobalConfig();
}
