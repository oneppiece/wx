package com.dzy.wx.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.dzy.wx.pay.entity.PayRequest;

/**
 * Created by 16440 on 2017/3/9.
 */
public interface PayService {
    JSONObject prePay(PayRequest payRequest);
}
