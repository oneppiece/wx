package com.dzy.wx.entity.message.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:16:21
 */
@MappedSuperclass
abstract class Message extends ReqModel {
    // 消息id，64位整型
    @JsonProperty("MsgId")
    @Column(name = "msg_id")
    private String msgId;

    public Message(String toUserName, String fromUserName, Double createTime, String msgType, String msgId) {
        super(toUserName, fromUserName, createTime, msgType);
        this.msgId = msgId;
    }
}
