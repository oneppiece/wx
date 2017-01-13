package com.dzy.wx.service.impl;

import com.dzy.wx.entity.message.req.ReqFactory;
import com.dzy.wx.entity.message.req.ReqModel;
import com.dzy.wx.entity.message.res.RespFactory;
import com.dzy.wx.entity.message.res.RespMessage;
import com.dzy.wx.repository.ReqModelRepository;
import com.dzy.wx.repository.RespRepository;
import com.dzy.wx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private ReqFactory reqFactory;
    @Autowired
    private RespFactory respFactory;
    @Autowired
    private ReqModelRepository reqModelRepository;
    @Autowired
    private RespRepository respRepository;

    @Override
    public RespMessage process(Map<String, Object> reqHashMap) {
        ReqModel reqModel = reqFactory.createReq(reqHashMap);//构建请求对象
        reqModelRepository.save(reqModel);//存数据库
        RespMessage respMessage = respFactory.createResponse(reqHashMap);//处理返回
        return respMessage == null ? null : respRepository.save(respMessage);
    }
}