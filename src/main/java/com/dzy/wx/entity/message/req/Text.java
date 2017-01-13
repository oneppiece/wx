package com.dzy.wx.entity.message.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Text extends Message {
    // 消息内容
    @JsonProperty("Content")
    @Column
    private String content;

    public Text(String toUserName, String fromUserName, Double createTime, String msgType, String msgId) {
        super(toUserName, fromUserName, createTime, msgType, msgId);
    }

    public Text(String toUserName, String fromUserName, Double createTime, String msgType, String msgId, String content) {
        super(toUserName, fromUserName, createTime, msgType, msgId);
        this.content = content;
    }

    @Override
    public String toString() {
        return "Text{" +
                "content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        content = content;
    }
}