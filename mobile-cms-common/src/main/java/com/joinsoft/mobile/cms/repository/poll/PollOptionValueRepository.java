package com.joinsoft.mobile.cms.repository.poll;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.poll.TbPollOptionValue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: wujun
 * Date: 2014/8/14
 */
public interface PollOptionValueRepository extends JpaRepository<TbPollOptionValue, Long> {
    List<TbPollOptionValue> findByPollOption_Id(Long optionId);

    @Modifying
    @Query("DELETE FROM TbPollOptionValue t WHERE t.pollOption.id = ?1")
    void deleteByPollOption_Id(Long optionId);
}
