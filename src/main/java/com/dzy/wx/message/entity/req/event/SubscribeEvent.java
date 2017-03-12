package com.dzy.wx.message.entity.req.event;

import javax.persistence.Entity;

/**
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:11:49
 */
@Entity
public class SubscribeEvent extends ReqEvent {

    public SubscribeEvent(String toUserName, String fromUserName, Double createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType, event);
    }

    public SubscribeEvent() {
    }
}
