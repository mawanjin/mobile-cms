package com.joinsoft.mobile.cms.repository.news;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.agent.TbSectionAgent;
import com.joinsoft.mobile.cms.entity.news.TbNews;
import org.springframework.data.jpa.repository.Query;


public interface newsRepository extends JpaRepository<TbNews, Long> {

}
