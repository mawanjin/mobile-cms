package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbTrafficDetail;
import com.joinsoft.mobile.cms.entity.enumerate.TrafficDetailStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangxulong on 14-8-26.
 */
public interface TrafficDetailRepository extends JpaRepository<TbTrafficDetail, Long> {
    @Query("SELECT SUM(t.traffic) FROM TbTrafficDetail t WHERE t.userId=?1 AND (t.createTime BETWEEN ?2 AND ?3)")
    BigDecimal findAllTrafficByMonth(Long userId, Date beginTime, Date endTime);

    @Query("FROM TbTrafficDetail t WHERE t.userId=?1 AND (t.createTime BETWEEN ?2 AND ?3) AND t.effectTime " +
            " IS NOT NULL AND t.traffic>0")
    List<TbTrafficDetail> findEffectTrafficByMonth(Long userId, Date beginTime, Date endTime);

    @Query("FROM TbTrafficDetail t WHERE t.userId=?1 AND (t.createTime BETWEEN ?2 AND ?3) AND t.effectTime " +
            " IS NULL AND t.traffic>0")
    List<TbTrafficDetail> findUnEffectTrafficByMonth(Long userId, Date beginTime, Date endTime);

    @Query("SELECT SUM(t.traffic) FROM TbTrafficDetail t WHERE t.userId=?1")
    BigDecimal findAvailableTraffic(Long id);

    @Query("FROM TbTrafficDetail t WHERE t.userId=?1 AND (t.createTime BETWEEN ?2 AND ?3) AND t.traffic<>0 ORDER BY t.createTime DESC")
    List<TbTrafficDetail> findCurrentMonthTrafficRecord(Long id, Date beginTime, Date endTime);

    @Query("FROM TbTrafficDetail t WHERE t.status=?1 AND t.traffic<0")
    List<TbTrafficDetail> findTrafficForApply(Pageable pageRequest, TrafficDetailStatus status);
}
