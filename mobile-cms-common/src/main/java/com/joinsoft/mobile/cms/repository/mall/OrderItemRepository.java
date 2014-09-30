package com.joinsoft.mobile.cms.repository.mall;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.mall.TbOrderItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangxulong on 14-8-19.
 */
public interface OrderItemRepository extends JpaRepository<TbOrderItem, Long> {
    @Query("FROM TbOrderItem  WHERE order.id =?1")
    List<TbOrderItem> findByOrder(Long id);
}
