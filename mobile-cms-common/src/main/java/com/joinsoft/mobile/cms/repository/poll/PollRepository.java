package com.joinsoft.mobile.cms.repository.poll;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.poll.TbPoll;
import org.springframework.data.jpa.repository.Query;

/**
 * User: wujun
 * Date: 2014/8/14
 */
public interface PollRepository extends JpaRepository<TbPoll, Long> {
    @Query("FROM TbPoll WHERE node.id=?1")
    TbPoll findByMeta(Long id);
}
