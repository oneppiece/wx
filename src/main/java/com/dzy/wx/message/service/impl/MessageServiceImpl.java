package com.dzy.wx.message.service.impl;

import com.dzy.wx.message.entity.req.ReqFactory;
import com.dzy.wx.message.entity.req.ReqModel;
import com.dzy.wx.message.entity.resp.RespFactory;
import com.dzy.wx.message.entity.resp.RespMessage;
import com.dzy.wx.message.req.repository.ReqModelRepository;
import com.dzy.wx.message.resp.repository.RespRepository;
import com.dzy.wx.message.service.MessageService;
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
        //构建请求对象
        ReqModel reqModel = reqFactory.createReq(reqHashMap);
        //将请求的对象存数据库
        reqModelRepository.save(reqModel);
        //根据请求对象,构建响应消息
        RespMessage respMessage = respFactory.createResponse(reqHashMap);
        //判断对象是否为空。空，则直接返回。不空，存数据库
        if (respMessage.getCreateTime() == null)
            return null;
        String className = respMessage.getClass().getSimpleName();
        if (className.equals("MusicResponse"))
            return respMessage;
        else
            return respMessage == null ? null : respRepository.save(respMessage);
    }
}