package com.joinsoft.mobile.cms.repository.poll;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.poll.TbPollOptionRecord;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: wujun
 * Date: 2014/9/12
 */
public interface PollOptionRecordRepository extends JpaRepository<TbPollOptionRecord, Long> {
    @Query("SELECT t.pollRecord.user.id, t.correct, count(t.correct) as c FROM TbPollOptionRecord t " +
            "WHERE t.pollRecord.user.id IN ?1 GROUP BY t.pollRecord.user.id, t.correct")
    List<Object[]> findCorrectCountByUserIdIn(List<Long> userIds);
}
