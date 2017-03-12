package com.dzy.wx.menu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:17:00
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Button {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "sub_button")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Button> subButton;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Button> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Button> subButton) {
        this.subButton = subButton;
    }
}
