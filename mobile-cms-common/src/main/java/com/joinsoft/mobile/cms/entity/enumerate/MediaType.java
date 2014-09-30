package com.joinsoft.mobile.cms.entity.enumerate;

/**
 * xingsen@join-cn.com
 */
public enum MediaType {
    Image("图片"), Voice("音频"), Video("视频"), Thumb("缩略图");

    private final String title;

    MediaType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
