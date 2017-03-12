package com.dzy.wx.message.entity.req.event;

import com.dzy.wx.message.entity.req.ReqModel;
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
abstract class ReqEvent extends ReqModel {
    public ReqEvent() {
    }

    @JsonProperty("ReqEvent")
    @Column(name = "event")
    protected String event;//事件类型

    public ReqEvent(String toUserName, String fromUserName, Double createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType);
        this.event = event;
    }

}
