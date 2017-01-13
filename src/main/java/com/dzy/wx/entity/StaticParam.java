package com.dzy.wx.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:14:01
 */
@Component
@ConfigurationProperties(prefix = "wx", locations = "classpath:properties/wx.properties")
public class StaticParam {
    public static final String respMessageTypeText = "text";
    public static final String respMessageTypeMusic = "music";
    public static final String respMessageTypeNews = "news";
    public static final String respMessageTypeImage = "image";
    public static final String reqMessageTypeText = "text";
    public static final String reqMessageTypeImage = "image";
    public static final String reqMessageTypeLink = "link";
    public static final String reqMessageTypeLocation = "location";
    public static final String reqMessageTypeVoice = "voice";
    public static final String reqMessageTypeVideo = "video";
    public static final String reqMessageTypeShortVideo = "shortvideo";
    public static final String reqMessageTypeEvent = "event";
    public static final String EventTypeSubscribe = "subscribe";
    public static final String EventTypeUnSubscribe = "unsubscribe";
    public static final String EventTypeView = "VIEW";
    public static final String EventTypeLocation = "LOCATION";
    public static final String EventTypeScan = "SCAN";
    public static final String EventTypeClick = "CLICK";
    public static final String respMessageTypeVoice = "voice";
    public static final String respMessageTypeVideo = "video";
    public static final String respMessageTypeLocation = "location";
    public static final String respMessageTypeShortVideo = "shortvideo";
    public static final String respMessageTypeEvent = "event";
    public static final String respMessageTypeLink = "link";
    private String urlAccessToken;
    private String appId;
    private String secret;
    private String urlIpList;
    private String urlMenuCreate;
    private String urlMenuGetCurrent;
    private String urlMediaMaterialGetCurrent;
    private String urlMediaGetCurrenthttps;
    private String urlOauthGetToken;
    private String urlOauthGetUserInfo;

    public String getUrlOauthCheckToken() {
        return urlOauthCheckToken;
    }

    public void setUrlOauthCheckToken(String urlOauthCheckToken) {
        this.urlOauthCheckToken = urlOauthCheckToken;
    }

    private String urlOauthCheckToken;

    public String getUrlOauthGetUserInfo() {
        return urlOauthGetUserInfo;
    }

    public void setUrlOauthGetUserInfo(String urlOauthGetUserInfo) {
        this.urlOauthGetUserInfo = urlOauthGetUserInfo;
    }

    public String getUrlMenuGetCurrent() {
        return urlMenuGetCurrent;
    }

    public void setUrlMenuGetCurrent(String urlMenuGetCurrent) {
        this.urlMenuGetCurrent = urlMenuGetCurrent;
    }

    public String getUrlMenuCreate() {
        return urlMenuCreate;
    }

    public void setUrlMenuCreate(String urlMenuCreate) {
        this.urlMenuCreate = urlMenuCreate;
    }

    public String getUrlAccessToken() {
        return urlAccessToken;
    }

    public void setUrlAccessToken(String urlAccessToken) {
        this.urlAccessToken = urlAccessToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUrlIpList() {
        return urlIpList;
    }

    public void setUrlIpList(String urlIpList) {
        this.urlIpList = urlIpList;
    }

    public String getUrlMediaMaterialGetCurrent() {
        return urlMediaMaterialGetCurrent;
    }

    public void setUrlMediaMaterialGetCurrent(String urlMediaMaterialGetCurrent) {
        this.urlMediaMaterialGetCurrent = urlMediaMaterialGetCurrent;
    }

    public String getUrlMediaGetCurrenthttps() {
        return urlMediaGetCurrenthttps;
    }

    public void setUrlMediaGetCurrenthttps(String urlMediaGetCurrenthttps) {
        this.urlMediaGetCurrenthttps = urlMediaGetCurrenthttps;
    }

    public String getUrlOauthGetToken() {
        return urlOauthGetToken;
    }

    public void setUrlOauthGetToken(String urlOauthGetToken) {
        this.urlOauthGetToken = urlOauthGetToken;
    }
}
