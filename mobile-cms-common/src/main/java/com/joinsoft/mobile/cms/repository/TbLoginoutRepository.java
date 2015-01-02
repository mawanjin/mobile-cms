package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbLoginout;
import com.joinsoft.mobile.cms.entity.agent.TbAgent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by abcd on 15/1/1.
 */
public interface TbLoginoutRepository extends JpaRepository<TbLoginout, Long> {

//    @Query("from TbLoginout where loginName=? order by id desc ")
    public Page<TbLoginout> findByLoginName(String loginName,Pageable pageable);
}
