package com.dzy.wx.entity.message.req;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 扫描带参数二维码事件
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:14:27
 */
@Entity
public class ScanEvent extends ReqEvent {

    public ScanEvent(String toUserName, String fromUserName, Double createTime, String msgType, String event, String eventKey, String ticket) {
        super(toUserName, fromUserName, createTime, msgType, event);
        this.eventKey = eventKey;
        this.ticket = ticket;
    }

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String eventKey;
    @Column
    private String ticket;//二维码的ticket，可用来换取二维码图片

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
