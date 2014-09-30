package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbAutoReplyConfig;
import com.joinsoft.mobile.cms.entity.enumerate.AutoReplyConfigType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * xingsen@join-cn.com
 */
public interface AutoReplyConfigRepository extends JpaRepository<TbAutoReplyConfig, Long> {
    @Query("FROM TbAutoReplyConfig WHERE eventKey=?1")
    TbAutoReplyConfig findByEventKey(String key);

    @Query("FROM TbAutoReplyConfig WHERE keyWord LIKE ?1 AND type=?2")
    List<TbAutoReplyConfig> findByKeyword(String keyword, AutoReplyConfigType type);
}
