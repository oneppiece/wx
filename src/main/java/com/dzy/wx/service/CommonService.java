package com.dzy.wx.service;

import com.dzy.wx.entity.Singature;
import com.dzy.wx.entity.WxServerIP;

import java.util.List;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:16:33
 */
public interface CommonService {
    String checkSignature(Singature singature);

    String byteToStr(byte[] byteArray);

    String byteToHexStr(byte mByte);

    String getAccessToken();

    List<WxServerIP> getWxServerIPFromWxServer();


}
