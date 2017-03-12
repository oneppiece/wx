package com.dzy.wx.message.entity.req.msg;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
public class Image extends Message {
    // 图片链接
    @JsonProperty("pic_url")
    @Column(name = "pic_url")
    private String picUrl;
    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    @JsonProperty("media_id")
    @Column(name = "media_id")
    private String mediaId;

    public Image(String toUserName, String fromUserName, Double createTime, String msgType, String msgId, String picUrl, String mediaId) {
        super(toUserName, fromUserName, createTime, msgType, msgId);
        this.picUrl = picUrl;
        this.mediaId = mediaId;
    }

    public Image() {
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        picUrl = picUrl;
    }


}