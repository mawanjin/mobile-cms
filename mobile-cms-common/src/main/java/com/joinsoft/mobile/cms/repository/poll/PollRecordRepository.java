package com.joinsoft.mobile.cms.repository.poll;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.poll.TbPollRecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: wujun
 * Date: 2014/8/30
 */
public interface PollRecordRepository extends JpaRepository<TbPollRecord, Long> {
    @Modifying
    @Query("UPDATE TbPollRecord t SET t.reward = ?1 WHERE t.poll.id = ?2 AND t.user.id IN ?3")
    void updateRewardByPollIdAndUserIdIn(Boolean reward, Long pollId, List<Long> userIds);

    @Modifying
    @Query("UPDATE TbPollRecord t SET t.reward = ?1 WHERE t.poll.id = ?2")
    void updateRewardByPollIdAndAllUser(Boolean reward, Long pollId);

    @Query("SELECT COUNT(1) FROM TbPollRecord t WHERE t.poll.id = ?1 AND t.user.id = ?2")
    long countByPollIdAndUserId(Long pollId, Long userId);
}
