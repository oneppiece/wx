package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Entity
public class LinkResp extends RespMessage {
    @Column
    @JsonProperty(value = "Title")
    private String title;
    @JsonProperty(value = "Description")
    private String description;
    @JsonProperty(value = "Url")
    private String url;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LinkResp(String toUserName, String fromUserName, Long createTime, String msgType, String title, String description, String url) {

        super(toUserName, fromUserName, createTime, msgType);
        this.title = title;
        this.description = description;
        this.url = url;
    }
}
