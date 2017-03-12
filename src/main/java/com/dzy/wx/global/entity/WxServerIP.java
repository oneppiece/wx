package com.dzy.wx.global.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:15:53
 */
@Table(name = "wx_server_ip")
@Entity
public class WxServerIP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "receive_time", nullable = false, unique = false)
    private Date receiveTime;
    @Column(name = "address", nullable = false, unique = false)
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
