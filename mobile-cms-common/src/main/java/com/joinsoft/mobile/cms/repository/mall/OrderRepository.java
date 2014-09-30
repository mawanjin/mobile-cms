package com.joinsoft.mobile.cms.repository.mall;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.mall.TbOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by wangxulong on 14-8-19.
 */
public interface OrderRepository extends JpaRepository<TbOrder, Long> {

    @Query("FROM TbOrder o WHERE o.user.id=?1 AND (o.createTime BETWEEN ?2 AND ?3) ORDER BY o.createTime DESC")
    List<TbOrder> findMyOrderByYearAndMonth(Long id, Date beginDate, Date endDate);
}
