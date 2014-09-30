package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbPointDetail;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wangxulong on 14-8-26.
 */
public interface PointDetailRepository extends JpaRepository<TbPointDetail, Long> {
    @Query("SELECT SUM(s.point) FROM TbPointDetail s WHERE s.userId=?1")
    public BigDecimal findCurrentPoint(Long userId);

    @Query("SELECT SUM(s.point) FROM TbPointDetail s WHERE s.userId=?1 AND (s.createTime between ?2 AND ?3) AND s.point>0")
    public BigDecimal findAllPointByMonth(Long userId, Date beginTime, Date endTime);

    @Query("FROM TbPointDetail s WHERE s.userId=?1 AND (s.createTime between ?2 AND ?3) AND s.point<0")
    public List<TbPointDetail> findCurrentMonthConsume(Long userId, Date beginDate, Date endDate);

}
