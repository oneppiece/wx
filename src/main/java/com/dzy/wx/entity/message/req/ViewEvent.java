package com.dzy.wx.entity.message.req;

import javax.persistence.Entity;

@Entity
public class ViewEvent extends ReqEvent {
    private String eventKey;

    public ViewEvent(String toUserName, String fromUserName, Double createTime, String msgType, String event, String eventKey) {
        super(toUserName, fromUserName, createTime, msgType, event);
        this.eventKey = eventKey;
    }
}
