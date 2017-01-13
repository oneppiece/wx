package com.dzy.wx.entity.message.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Link extends Message {

    // 消息标题
    @JsonProperty("Title")
    private String title;
    // 消息描述
    @JsonProperty("Description")
    private String description;
    // 消息链接
    @JsonProperty("Url")
    private String url;

    public Link(String toUserName, String fromUserName, Double createTime, String msgType, String msgId, String title, String description, String url) {
        super(toUserName, fromUserName, createTime, msgType, msgId);
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        url = url;
    }
}