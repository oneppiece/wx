package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"MsgId"})
@Entity
public class VideoResponse extends RespMessage {
    // @JacksonXmlElementWrapper(localName = "Videos", useWrapping = false)//是否显示List的根标签 false:不显示
    @JacksonXmlProperty(localName = "Video")
    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private Video video;

    public VideoResponse(String toUserName, String fromUserName, Long createTime, String msgType, Video video) {
        super(toUserName, fromUserName, createTime, msgType);
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
