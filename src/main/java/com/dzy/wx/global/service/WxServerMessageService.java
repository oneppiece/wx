package com.dzy.wx.global.service;

import com.dzy.wx.global.entity.WxServerIP;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:15:49
 */
public interface WxServerMessageService {
    List<WxServerIP> getWxServerIPFromWxServer();
}
