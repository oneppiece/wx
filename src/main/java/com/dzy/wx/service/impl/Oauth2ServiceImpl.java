package com.dzy.wx.service.impl;

import com.dzy.wx.Utils.DateUtils;
import com.dzy.wx.Utils.HttpUtils;
import com.dzy.wx.entity.OauthAccessToken;
import com.dzy.wx.entity.StaticParam;
import com.dzy.wx.entity.User;
import com.dzy.wx.repository.OauthAccessTokenRepository;
import com.dzy.wx.repository.UserRepository;
import com.dzy.wx.service.Oauth2Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:13:58
 */
@Service
public class Oauth2ServiceImpl implements Oauth2Service {
    @Autowired
    private StaticParam staticParam;
    @Autowired
    private OauthAccessTokenRepository oauthAccessTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public OauthAccessToken getAccessToken(String code) {
        OauthAccessToken oauthAccessToken = getAccessTokenFromWxServer(code);
        String openId = oauthAccessToken.getOpenId();
        return (oauthAccessTokenRepository.findOneByOpenId(openId) == null) ? oauthAccessTokenRepository.save(oauthAccessToken) : oauthAccessToken;
    }

    private OauthAccessToken getAccessTokenFromWxServer(String code) {
        String url = staticParam.getUrlOauthGetToken().replace("APPID", staticParam.getAppId())
                .replace("SECRET", staticParam.getSecret())
                .replace("CODE", code);
        JSONObject jsonObject = HttpUtils.httpRequest(url, HttpMethod.GET.toString(), null);
        String accessToken = jsonObject.getString("access_token");
        Integer expiresIn = jsonObject.getInt("expires_in");
        String refreshToken = jsonObject.getString("refresh_token");
        String openid = jsonObject.getString("openid");
        String scope = jsonObject.getString("scope");
        Date receivedTime = new Date();
        Date underTime = DateUtils.addDate(receivedTime);
        jsonObject.forEach((k, v) -> System.out.println(k + ":" + v));
        return new OauthAccessToken(accessToken, expiresIn, refreshToken, openid, scope, receivedTime, underTime);
    }

    @Override
    public void refreshAccessToken(OauthAccessToken token) {
        oauthAccessTokenRepository.delete(token);

    }

    @Override
    public User getUserInfoByAccessToken(OauthAccessToken accessToken) {
        String token = accessToken.getAccessToken();
        String openId = accessToken.getOpenId();
        String url = staticParam.getUrlOauthGetUserInfo().replace("ACCESS_TOKEN", token).replace("OPENID", openId);
        JSONObject jsonObject = HttpUtils.httpRequest(url, HttpMethod.GET.toString(), null);
        String openid = jsonObject.getString("openid");
        String nickName = jsonObject.getString("nickname");
        Boolean sex = jsonObject.getString("sex").equalsIgnoreCase("1") ? Boolean.TRUE : Boolean.FALSE;
        String province = jsonObject.getString("province");
        String city = jsonObject.getString("city");
        String country = jsonObject.getString("country");
        String headImgUrl = jsonObject.getString("headimgurl");
        JSONArray privilege = jsonObject.getJSONArray("privilege");
        String unionId = jsonObject.get("unionid") == null ? "未绑定微信开放平台" : jsonObject.getString("unionid");
        jsonObject.forEach((k, v) -> System.out.println(k + ":" + v));
        User user = new User(openid, nickName, sex, province, city, country, new Date(), headImgUrl, privilege.toString(), unionId);
        return (userRepository.findOneByOpenId(openid) == null) ? userRepository.save(user) : user;
    }


    /**
     * 判断是否超时
     *
     * @param token token
     * @return boolean
     */
    private boolean check(OauthAccessToken token) {
        boolean isTimeOut = token == null || new Date().compareTo(token.getUnderTime()) > 0;
        boolean isValid = checkIsValid(token);
        if (!isValid)
            refreshAccessToken(token);
        return isTimeOut || isValid;
    }

    /**
     * 判断是否可用
     *
     * @param token token
     * @return boolean
     */
    private boolean checkIsValid(OauthAccessToken token) {
        String urlOauthCheckToken = staticParam.getUrlOauthCheckToken().replace("ACCESS_TOKEN", token.getAccessToken()).replace("OPENID", token.getOpenId());
        JSONObject jsonObject = HttpUtils.httpRequest(urlOauthCheckToken, HttpMethod.GET.toString(), null);
        return jsonObject.getInt("errcode") == 0;
    }
}
