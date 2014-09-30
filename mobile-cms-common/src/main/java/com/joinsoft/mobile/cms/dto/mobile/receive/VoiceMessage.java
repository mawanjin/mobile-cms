package com.joinsoft.mobile.cms.dto.mobile.receive;

import java.util.Date;

/**
 * xingsen@join-cn.com
 */
public class VoiceMessage extends MobileMessage {
    private String format;
    private String mediaId;

    public VoiceMessage(String from, String to, String msgId, Date createTime) {
        super(from, to, msgId, createTime);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
