package com.joinsoft.mobile.cms.repository.news;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.news.TbNews;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by Administrator on 14-11-5.
 */
public interface TbNewsRepository extends JpaRepository<TbNews, Long> {

    @Query("from TbNews t where t.id=?1")
    TbNews findByTbNewsId(Long id);

}
