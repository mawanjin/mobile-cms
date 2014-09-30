package com.joinsoft.mobile.cms.repository.content;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by wangxulong on 14-8-12.
 */
public interface CmsNodeRepository extends JpaRepository<TbCmsNode, Long>{
    @Query(value = "FROM TbCmsNode WHERE type=?1",
            countQuery = "SELECT COUNT(*) FROM TbCmsNode WHERE type=?1")
    Page<TbCmsNode> findAll(String type, Pageable pageable);
}
