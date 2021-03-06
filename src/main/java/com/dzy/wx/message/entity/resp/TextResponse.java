package com.dzy.wx.message.entity.resp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.Column;
import javax.persistence.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@JacksonXmlRootElement(localName = "xml")
public class TextResponse extends RespMessage {
    public TextResponse(String toUserName, String fromUserName, Long createTime, String msgType, String content) {
        super(toUserName, fromUserName, createTime, msgType);
        this.content = content;
    }

    // 回复的消息内容
    @JsonProperty("Content")
    @JacksonXmlCData
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public TextResponse() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}