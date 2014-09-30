package com.joinsoft.mobile.cms.form;

import com.joinsoft.mobile.cms.entity.content.TbCmsNode;
import org.springframework.web.multipart.MultipartFile;

/**
 * xingsen@join-cn.com
 */
public class NodeEditForm {
    protected Long id;
    protected String title;
    protected String authorName;
    private String summary;
    private String picPath;
    private MultipartFile pic;

    public NodeEditForm() {
    }

    public NodeEditForm(String title) {
        this.title = title;
    }

    public NodeEditForm(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public NodeEditForm(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public MultipartFile getPic() {
        return pic;
    }

    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }

    public void fromEntity(TbCmsNode node) {
        this.id = node.getId();
        this.title = node.getTitle();
        this.authorName = node.getAuthorName();
        this.summary = node.getSummary();
        this.picPath = node.getPicPath();
    }
}
