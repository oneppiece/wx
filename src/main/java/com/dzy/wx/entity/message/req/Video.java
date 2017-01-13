package com.dzy.wx.entity.message.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 视频消息，同小视频
 * Created by Administrator on 2016/9/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Video extends Message {
    @JsonProperty("MediaId")
    @Column(name = "media_id")
    private String mediaId;
    @JsonProperty("ThumbMediaId")
    @Column(name = "thumb_media_id")
    private String thumbMediaId;

    public Video(String toUserName, String fromUserName, Double createTime, String msgType, String msgId, String mediaId, String thumbMediaId) {
        super(toUserName, fromUserName, createTime, msgType, msgId);
        this.mediaId = mediaId;
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        thumbMediaId = thumbMediaId;
    }
}
