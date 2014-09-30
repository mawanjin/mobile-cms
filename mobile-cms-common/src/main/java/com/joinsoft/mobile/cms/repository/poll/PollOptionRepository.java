package com.joinsoft.mobile.cms.repository.poll;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.poll.TbPollOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: wujun
 * Date: 2014/8/14
 */
public interface PollOptionRepository extends JpaRepository<TbPollOption, Long> {
    @Query("FROM TbPollOption po WHERE po.poll.node.id = ?1")
    List<TbPollOption> findByPollNodeId(Long id);

    @Query("FROM TbPollOption po WHERE po.poll.node.id = ?1")
    Page<TbPollOption> findByPollNodeId(Long id, Pageable pageable);
}
