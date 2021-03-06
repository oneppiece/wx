package com.dzy.wx.global.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dzy.wx.global.StaticParam;
import com.dzy.wx.global.entity.WxServerIP;
import com.dzy.wx.global.repository.WxServerIPRepository;
import com.dzy.wx.global.service.AccessTokenService;
import com.dzy.wx.global.service.WxServerMessageService;
import com.dzy.wx.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:15:59
 */
@Service
public class WxServerMessageImpl implements WxServerMessageService {

    @Autowired
    private StaticParam staticParam;
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private WxServerIPRepository wxServerIPRepository;

    @Override
    public List<WxServerIP> getWxServerIPFromWxServer() {
        String url = staticParam.getUrlIpList().replace("ACCESS_TOKEN", accessTokenService.getAccessToken());
        JSONObject result = HttpUtils.httpRequest(url, RequestMethod.GET.toString(), null);
        try {
            result.getString("errcode");
            accessTokenService.refreshAccessToken();
            this.getWxServerIPFromWxServer();
        } catch (JSONException e) {
            System.out.println("获取微信服务器IP");
        }
        JSONArray ip_list = result.getJSONArray("ip_list");
        Set<WxServerIP> ipSet = new HashSet<>();
        Date date = new Date();
        //noinspection unchecked
        ip_list.forEach((ip) -> {
            WxServerIP wxServerIP = new WxServerIP();
            wxServerIP.setAddress((String) ip);
            wxServerIP.setReceiveTime(date);
            ipSet.add(wxServerIP);
        });
        return wxServerIPRepository.save(ipSet);
    }
}
