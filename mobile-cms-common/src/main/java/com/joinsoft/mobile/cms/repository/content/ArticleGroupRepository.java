package com.joinsoft.mobile.cms.repository.content;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.content.TbArticleGroup;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangxulong on 14-8-14.
 */
public interface ArticleGroupRepository extends JpaRepository<TbArticleGroup, Long> {

    @Query("FROM TbArticleGroup m WHERE m.node.id=?1 ORDER BY m.orderIndex")
    List<TbArticleGroup> findMultiArticleByMeta(Long id);

    @Query("SELECT m.contentNode FROM TbArticleGroup m WHERE m.node.id=?1 ORDER BY m.orderIndex")
    List<TbCmsNode> findArticleByMeta(Long id);

    @Query("SELECT COUNT(ag.id) FROM TbArticleGroup ag WHERE ag.node.id=?1")
    Long countArticleByNode(Long metaId);

    @Query("FROM TbArticleGroup ag WHERE ag.node.id=?1 AND ag.contentNode.id=?2")
    TbArticleGroup findMetaAndArticleMeta(Long metaId, Long nodeId);

    @Query("FROM TbArticleGroup ag WHERE ag.node.id=?1 AND ag.contentNode.id NOT IN(?2)")
    List<TbArticleGroup> findByNodeAndArticleNNotIn(Long id, List<Long> nodeIds);
}