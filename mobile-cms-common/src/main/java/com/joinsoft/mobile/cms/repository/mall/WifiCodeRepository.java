package com.joinsoft.mobile.cms.repository.mall;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.enumerate.WifiCodeStatus;
import com.joinsoft.mobile.cms.entity.mall.TbWifiCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangxulong on 14-9-17.
 */
public interface WifiCodeRepository extends JpaRepository<TbWifiCode, Long> {

    public TbWifiCode findByCode(String code);

    @Query("FROM TbWifiCode w WHERE w.status=?1")
    List<TbWifiCode> findByStatus(Pageable pageRequest, WifiCodeStatus status);

    @Query("FROM TbWifiCode w WHERE w.orderItemId=?1")
    TbWifiCode findByOrderItemId(Long orderItemId);
}
