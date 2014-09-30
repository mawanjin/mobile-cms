package com.joinsoft.mobile.cms.form;

import com.joinsoft.mobile.cms.entity.TbMobileMenu;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import com.joinsoft.mobile.cms.entity.enumerate.MobileMenuType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * xingsen@join-cn.com
 */
public class MobileMenuEditForm {
    private Long id;
    private String name;
    private String value;
    private MobileMenuType type;
    private Long parentId;

    private String dlgResult; //for对话框
    private String articleTitle;//关联的文本

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDlgResult() {
        return dlgResult;
    }

    public void setDlgResult(String dlgResult) {
        this.dlgResult = dlgResult;
    }

    public void formEntity(TbMobileMenu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.value = menu.getValue();
        this.type = menu.getType();
        this.dlgResult = StringUtils.isNotEmpty(menu.getType().getPrefix()) && menu.getValue().startsWith(menu.getType().getPrefix())
                ? menu.getValue().replace(menu.getType().getPrefix(), "") : "";

        if (menu.getNode() != null) {
            this.articleTitle = menu.getNode().getTitle();
        }
        if (menu.getParent() != null) {
            this.parentId = menu.getParent().getId();
        }
    }

    public TbMobileMenu toEntity() {
        TbMobileMenu menu = new TbMobileMenu();
        menu.setId(this.id);
        menu.setName(this.name);
        menu.setValue(this.value);
        if (this.type == MobileMenuType.CMS) {
            menu.setValue(String.format("%s%s", this.getType().getPrefix(), this.dlgResult));
            Long nodeId = NumberUtils.toLong(this.dlgResult, 0);
            if (nodeId != 0L) {
                TbCmsNode node = new TbCmsNode();
                node.setId(nodeId);
                menu.setNode(node);
            }
        }
        menu.setType(this.type);
        if (parentId != null) {
            TbMobileMenu parent = new TbMobileMenu();
            parent.setId(this.parentId);
            menu.setParent(parent);
        }
        return menu;
    }
}
