package com.dzy.wx.service;

import com.dzy.wx.entity.message.res.RespMessage;

import java.util.Map;


public interface MessageService {
    RespMessage process(Map<String, Object> reqHashMap);

}

