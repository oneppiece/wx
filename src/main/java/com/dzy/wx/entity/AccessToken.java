package com.dzy.wx.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:14:57
 */
@Component
@Table(name = "access_token")
@Entity
public class AccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "access_token", unique = false, nullable = false)
    private String accessToken;
    @Column(name = "expires_in", unique = false, nullable = false)
    private Integer expiresIn;
    //写入时间
    @Column(name = "received_time", unique = false, nullable = false)
    private Date receivedTime;
    //失效的时间
    @Column(name = "under_time", unique = false, nullable = false)
    private Date UnderTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Date getUnderTime() {
        return UnderTime;
    }

    public void setUnderTime(Date underTime) {
        UnderTime = underTime;
    }
}
