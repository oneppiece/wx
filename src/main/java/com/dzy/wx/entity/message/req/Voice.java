package com.dzy.wx.entity.message.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 音频消息
 *
 * @author liufeng
 * @date 2013-05-19
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Voice extends Message {
    public Voice(String toUserName, String fromUserName, Double createTime, String msgType, String msgId, String mediaId, String format) {
        super(toUserName, fromUserName, createTime, msgType, msgId);
        this.mediaId = mediaId;
        this.format = format;
    }

    // 媒体ID
    @JsonProperty("MediaId")
    @Column(name = "media_id")
    private String mediaId;
    // 语音格式
    @JsonProperty("Format")
    private String format;


    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        format = format;
    }
}