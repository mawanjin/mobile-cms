package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbUserProfile;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by wangxulong on 14-8-19.
 */
public interface UserProfileRepository extends JpaRepository<TbUserProfile, Long> {
    @Query("FROM TbUserProfile u WHERE u.user.id=?1")
    TbUserProfile findByUser(Long id);

    TbUserProfile findByMobile(String mobilePhone);
}
