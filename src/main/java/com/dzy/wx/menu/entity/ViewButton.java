package com.dzy.wx.menu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:17:09
 */
public class ViewButton extends Button {
    @JsonProperty(value = "url")
    private String url;
    @JsonProperty(value = "type")
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
