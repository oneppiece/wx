package com.dzy.wx.entity.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:17:09
 */
public class ClickButton extends Button {
    @JsonProperty(value = "key")
    private String key;
    @JsonProperty(value = "type")
    private String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
