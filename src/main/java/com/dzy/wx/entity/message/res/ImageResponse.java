package com.dzy.wx.entity.message.res;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 图片消息
 *
 * @author liufeng
 * @date 2013-05-19
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"MsgId"})
@Entity
public class ImageResponse extends RespMessage {
    public ImageResponse(String toUserName, String fromUserName, Long createTime, String msgType, Set<Image> images) {
        super(toUserName, fromUserName, createTime, msgType);
        this.images = images;
    }

    // 图片链接
    @JacksonXmlElementWrapper(localName = "Image", useWrapping = false)//是否显示List的根标签 false:不显示
    @JacksonXmlProperty(localName = "Image")
    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Image> images;

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

}