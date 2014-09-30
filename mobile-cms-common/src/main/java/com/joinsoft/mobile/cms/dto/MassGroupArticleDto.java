package com.joinsoft.mobile.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangxulong on 14-9-13.
 */
public class MassGroupArticleDto {
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;
    private String author;
    private String title;
    @JsonProperty("content_source_url")
    private String contentSourceUrl;
    private String digest;
    @JsonProperty("show_cover_pic")
    private String showCoverPic;

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getShowCoverPic() {
        return showCoverPic;
    }

    public void setShowCoverPic(String showCoverPic) {
        this.showCoverPic = showCoverPic;
    }
}
