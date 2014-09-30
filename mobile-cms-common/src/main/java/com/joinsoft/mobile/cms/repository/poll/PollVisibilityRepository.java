package com.joinsoft.mobile.cms.repository.poll;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.poll.TbPollVisibility;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: wujun
 * Date: 2014/9/7
 */
public interface PollVisibilityRepository extends JpaRepository<TbPollVisibility, Long> {
    List<TbPollVisibility> findByPoll_Node_Id(Long pollNodeId);

    @Query("SELECT COUNT(1) FROM TbPollVisibility t WHERE t.poll.id = ?1 AND t.user.id = ?2")
    long countByPollIdAndUserId(Long pollId, Long userId);

    @Query("SELECT COUNT(1) FROM TbPollVisibility t WHERE t.poll.id = ?1")
    long countByPollId(Long pollId);
}
