package com.joinsoft.mobile.cms.repository.news;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import com.joinsoft.mobile.cms.entity.agent.TbSectionAgent;
import com.joinsoft.mobile.cms.entity.news.TbNews;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface newsRepository extends JpaRepository<TbNews, Long> {
    @Modifying
    @Query("update TbNews set online=?1,operTime=now(),oper=?2  WHERE id IN(?3)")
     void updateIt(String flag,String user,List<Long> ids);
}
