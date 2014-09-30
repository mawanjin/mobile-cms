package com.joinsoft.mobile.cms.repository.poll;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.poll.TbPollOptionValueRecord;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: wujun
 * Date: 2014/8/30
 */
public interface PollOptionValueRecordRepository extends JpaRepository<TbPollOptionValueRecord, Long> {
    @Query(nativeQuery = true, value = "SELECT count(1) as c, t.poll_option_value_id FROM tb_poll_option_value_record t " +
            "LEFT JOIN tb_poll_option_record r ON t.poll_option_record_id = r.id " +
            "WHERE t.poll_option_value_id IS NOT NULL AND r.poll_option_id = ?1 " +
            "GROUP BY t.poll_option_value_id")
    List<Object[]> findCountAndValueIdByPollOptionId(Long optionId);

}
