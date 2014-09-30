package com.joinsoft.mobile.cms.repository.ad;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.ad.TbAdStats;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by wangxulong on 14-8-18.
 */
public interface AdStatsRepository extends JpaRepository<TbAdStats, Long> {
    @Query("FROM TbAdStats s WHERE s.ad.id=?1")
    TbAdStats findByAd(Long id);
}
