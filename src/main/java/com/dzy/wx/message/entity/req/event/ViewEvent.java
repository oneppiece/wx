package com.dzy.wx.message.entity.req.event;

import javax.persistence.Entity;

@Entity
public class ViewEvent extends ReqEvent {
    private String eventKey;

    public ViewEvent(String toUserName, String fromUserName, Double createTime, String msgType, String event, String eventKey) {
        super(toUserName, fromUserName, createTime, msgType, event);
        this.eventKey = eventKey;
    }

    public ViewEvent() {
    }
}
