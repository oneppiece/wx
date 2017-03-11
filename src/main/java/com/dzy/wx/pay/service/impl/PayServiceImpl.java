package com.dzy.wx.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dzy.wx.Utils.HttpUtils;
import com.dzy.wx.entity.StaticParam;
import com.dzy.wx.pay.entity.PayRequest;
import com.dzy.wx.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * Created by 16440 on 2017/3/9.
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private StaticParam staticParam;

    @Override
    public JSONObject prePay(PayRequest payRequest) {
        String postData = "";
        return HttpUtils.httpRequest(staticParam.getPreOrderUrl(), HttpMethod.POST.toString(), postData);
    }
}
