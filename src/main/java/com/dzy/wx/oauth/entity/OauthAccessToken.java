package com.dzy.wx.oauth.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@Entity
@Table(name = "oauth_access_token")
public class OauthAccessToken {
    @Override
    public String toString() {
        return "OauthAccessToken{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                ", receivedTime=" + receivedTime +
                ", UnderTime=" + UnderTime +
                '}';
    }

    public OauthAccessToken(String accessToken, Integer expiresIn, String refreshToken, String openId, String scope, Date receivedTime, Date underTime) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.openId = openId;
        this.scope = scope;
        this.receivedTime = receivedTime;
        UnderTime = underTime;
    }

    @Id

    @GeneratedValue
    private Integer id;
    @Column(name = "access_token")
    private String accessToken;
    @Column(name = "expires_in")
    private Integer expiresIn;
    @Column(name = "refresh_token")
    private String refreshToken;

    public OauthAccessToken() {
    }

    @Column(name = "open_id")
    private String openId;
    @Column(name = "scope")
    private String scope;
    //写入时间
    @Column(name = "received_time", unique = false, nullable = false)
    private Date receivedTime;
    //失效的时间
    @Column(name = "under_time", unique = false, nullable = false)
    private Date UnderTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
