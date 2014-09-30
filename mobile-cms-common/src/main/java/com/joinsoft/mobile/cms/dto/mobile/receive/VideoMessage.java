package com.joinsoft.mobile.cms.dto.mobile.receive;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class VideoMessage extends MobileMessage {
    private String thumbMediaId;
    private String mediaId;

    public VideoMessage(String from, String to, String msgId, Date createTime) {
        super(from, to, msgId, createTime);
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
