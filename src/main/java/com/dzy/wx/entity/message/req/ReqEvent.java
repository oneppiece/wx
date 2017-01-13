package com.dzy.wx.entity.message.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:15:45
 */
@Entity
public abstract class ReqEvent extends ReqModel {
    @JsonProperty("ReqEvent")
    @Column(name = "event")
    protected String event;//事件类型

    public ReqEvent(String toUserName, String fromUserName, Double createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType);
        this.event = event;
    }

}
