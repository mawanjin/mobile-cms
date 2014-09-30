package com.joinsoft.mobile.cms.form;

import com.joinsoft.framework.security.entity.User;
import com.joinsoft.mobile.cms.entity.content.TbCmsNode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxulong on 14-8-12.
 */
public class CmsContentForm {
    private Long id;
    private String title;
    private String content;
    private String type;
    private String typeDescription;
    private Date lastModified;
    private String authorName;
    private Map<String, Object> other = new HashMap<String, Object>();

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getOther() {
        return other;
    }

    public void setOther(Map<String, Object> other) {
        this.other = other;
    }

    public static CmsContentForm formMeta(TbCmsNode meta, CmsContentForm form) {
        form.setId(meta.getId());
        form.setTitle(meta.getTitle());
        form.setType(meta.getType());
        form.setAuthorName(meta.getAuthorName());
        form.setLastModified(meta.getLastModified());
        form.setTypeDescription(meta.getTypeDescription());
        return form;
    }

    public TbCmsNode toMeta(User loginUser) {
        TbCmsNode meta = new TbCmsNode();
        meta.setId(this.id);
        meta.setTitle(this.title);
        meta.setType(this.type);
        meta.setLastModified(new Date());
        meta.setAuthor(loginUser);
        meta.setTypeDescription(typeDescription);
        return meta;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
