package com.dzy.wx.service;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:13:57
 */
public interface AccessTokenService {

    String getAccessToken();

    void refreshAccessToken();
}
