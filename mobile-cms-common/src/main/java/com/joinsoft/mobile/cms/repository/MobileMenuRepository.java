package com.joinsoft.mobile.cms.repository;

import com.joinsoft.framework.orm.jpa.JpaRepository;
import com.joinsoft.mobile.cms.entity.TbMobileMenu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * User: wujun
 * Date: 2014/8/14
 */
public interface MobileMenuRepository extends JpaRepository<TbMobileMenu, Long> {
    @Modifying
    @Query("DELETE FROM TbMobileMenu t WHERE t.parent.id = ?1")
    void deleteInParentId(Long parentId);

    @Query("FROM TbMobileMenu WHERE parent.id IS NULL")
    List<TbMobileMenu> findParentIdIsNull();

    @Query("FROM TbMobileMenu WHERE name=?1")
    List<TbMobileMenu> findByName(String name);
}
