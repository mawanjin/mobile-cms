package com.joinsoft.mobile.cms.service.content;

/**
 * xingsen@join-cn.com
 */
public enum CmsContentType {
    Text("文本"), Article("单图文"), ArticleGroup("多图文"), Poll("投票");
    private final String title;

    CmsContentType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
