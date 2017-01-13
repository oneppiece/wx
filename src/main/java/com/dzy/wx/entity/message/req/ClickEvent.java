package com.dzy.wx.entity.message.req;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:14:29
 */
@Entity
public class ClickEvent extends ReqEvent {
    public ClickEvent(String toUserName, String fromUserName, Double createTime, String msgType, String event, String eventKey) {
        super(toUserName, fromUserName, createTime, msgType, event);
        this.eventKey = eventKey;
    }

    @Column
    private String eventKey;


    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
