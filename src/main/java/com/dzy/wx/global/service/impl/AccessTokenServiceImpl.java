package com.dzy.wx.global.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dzy.wx.global.StaticParam;
import com.dzy.wx.global.entity.AccessToken;
import com.dzy.wx.global.repository.AccessTokenRepository;
import com.dzy.wx.global.service.AccessTokenService;
import com.dzy.wx.utils.DateUtils;
import com.dzy.wx.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:13:58
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    @Autowired
    private StaticParam staticParam;
    @Autowired
    private AccessTokenRepository accessTokenRepository;

    @Override
    @Transactional
    public String getAccessToken() {
        List<AccessToken> all = accessTokenRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        AccessToken accessToken = all.size() == 0 ? null : all.get(0);
        if (checkTimeOut(accessToken)) {
            accessToken = new AccessToken();
            String url = staticParam.getUrlAccessToken().replace("APPID", staticParam.getAppId()).replace("APPSECRET", staticParam.getSecret());
            JSONObject result = HttpUtils.httpRequest(url, RequestMethod.GET.toString(), null);
            accessToken.setAccessToken(result.getString("access_token"));
            accessToken.setExpiresIn(result.getIntValue("expires_in"));
            accessToken.setReceivedTime(new Date());
            accessToken.setUnderTime(DateUtils.addDate(new Date()));
            accessTokenRepository.save(accessToken);
            return accessToken.getAccessToken();
        } else
            return accessToken.getAccessToken();
    }

    @Override
    public void refreshAccessToken() {
        accessTokenRepository.deleteAll();
        this.getAccessToken();
    }


    /**
     * 判断是否超时
     *
     * @param token token
     * @return boolean
     */
    private boolean checkTimeOut(AccessToken token) {
        return token == null || new Date().compareTo(token.getUnderTime()) > 0;
    }
}
