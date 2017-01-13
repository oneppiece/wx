package com.dzy.wx.service;

import com.dzy.wx.entity.OauthAccessToken;
import com.dzy.wx.entity.User;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:13:57
 */
public interface Oauth2Service {

    OauthAccessToken getAccessToken(String code);

    void refreshAccessToken(OauthAccessToken token);

    User getUserInfoByAccessToken(OauthAccessToken accessToken);
}
