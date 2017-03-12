package com.dzy.wx.message.service;

import com.dzy.wx.message.entity.resp.RespMessage;

import java.util.Map;


public interface MessageService {
    RespMessage process(Map<String, Object> reqHashMap);

}

