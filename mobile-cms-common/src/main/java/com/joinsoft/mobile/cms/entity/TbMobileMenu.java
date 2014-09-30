package com.joinsoft.mobile.cms.entity;

import com.joinsoft.framework.entity.AutoModel;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.enumerate.MobileMenuType;

import javax.persistence.*;
import java.util.Set;

/**
 * User: wujun
 * Date: 2014/8/19
 */
@Entity
@Table(name = "tb_m_menu")
public class TbMobileMenu extends AutoModel {
    private String name;
    private String value;
    @Enumerated
    private MobileMenuType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private TbMobileMenu parent;
    @OneToMany(mappedBy = "parent")
    private Set<TbMobileMenu> subMenu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    private TbCmsNode node;

    public Set<TbMobileMenu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Set<TbMobileMenu> subMenu) {
        this.subMenu = subMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MobileMenuType getType() {
        return type;
    }

    public void setType(MobileMenuType type) {
        this.type = type;
    }

    public TbMobileMenu getParent() {
        return parent;
    }

    public void setParent(TbMobileMenu parent) {
        this.parent = parent;
    }

    public TbCmsNode getNode() {
        return node;
    }

    public void setNode(TbCmsNode node) {
        this.node = node;
    }
}
