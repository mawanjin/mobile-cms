package com.joinsoft.mobile.cms.repository.content;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.content.TbText;
import org.springframework.data.jpa.repository.Query;

/**
 * xingsen@join-cn.com
 */
public interface TextRepository extends JpaRepository<TbText, Long> {
    @Query("FROM TbText WHERE node.id=?1")
    TbText findByMeta(Long id);
}
