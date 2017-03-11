package com.dzy.wx.controller;

import com.dzy.wx.entity.OauthAccessToken;
import com.dzy.wx.entity.User;
import com.dzy.wx.service.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@Controller
@RequestMapping
public class OauthController {
    @Autowired
    private Oauth2Service oauth2Service;

    @RequestMapping(value = "/home")
    public String home(@RequestParam(name = "code", required = true) String code, @RequestParam(name = "state", required = true) String state, HttpServletRequest request) {
        System.out.println("code:::::"+code);
        OauthAccessToken accessToken = new OauthAccessToken();
        synchronized (accessToken) {
            accessToken = oauth2Service.getAccessToken(code);
        }
        User userInfoByAccessToken = oauth2Service.getUserInfoByAccessToken(accessToken);
        request.getSession().setAttribute("user", userInfoByAccessToken);
        return "forward:/pay/payIndex";
    }


}
