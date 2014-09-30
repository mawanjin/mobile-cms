package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbCache;

/**
 * xingsen@join-cn.com
 */
public interface CacheRepository extends JpaRepository<TbCache, Long> {
    TbCache findByCacheKey(String key);
}
