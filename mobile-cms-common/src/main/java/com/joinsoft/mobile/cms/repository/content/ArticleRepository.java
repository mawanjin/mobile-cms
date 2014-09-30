package com.joinsoft.mobile.cms.repository.content;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.content.TbArticle;
import org.springframework.data.jpa.repository.Query;

/**
 * xingsen@join-cn.com
 */
public interface ArticleRepository extends JpaRepository<TbArticle, Long> {
    @Query("FROM TbArticle WHERE node.id=?1")
    TbArticle findByMeta(Long id);
}
