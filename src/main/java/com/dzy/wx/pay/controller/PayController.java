package com.dzy.wx.pay.controller;

import com.dzy.wx.global.StaticParam;
import com.dzy.wx.pay.entity.PayRequest;
import com.dzy.wx.pay.entity.PayResponse;
import com.dzy.wx.pay.service.PayService;
import com.dzy.wx.pay.utils.LocalIPUtil;
import com.dzy.wx.pay.utils.OrderUtils;
import com.dzy.wx.pay.utils.SignUtils;
import com.dzy.wx.user.entity.User;
import com.dzy.wx.utils.DateUtils;
import com.dzy.wx.utils.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 16440 on 2017/3/9.
 */
@Controller
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private StaticParam staticParam;

    @RequestMapping(value = "/pay/payIndex", method = RequestMethod.GET)
    public String payIndex(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("pay页面获取到的user:" + user.toString());
        return "/pay/payIndex";
    }

    @RequestMapping(value = "/pay/preOrder", method = RequestMethod.POST)
    @ResponseBody
    public PayResponse preOrder(@RequestParam("voucherId") String voucherId,
                                @RequestParam("total") int total, HttpServletRequest request) throws JsonProcessingException {
        User user = (User) request.getSession().getAttribute("user");
        String openId = user.getOpenId();
        PayRequest payRequest = new PayRequest();
        String random = SignUtils.createRandom();
        payRequest.setAppId(staticParam.getAppId());
        payRequest.setMchId(StaticParam.getMchId());
        payRequest.setNonceStr(random);
        payRequest.setBody("kk网页测试详情");
        payRequest.setOutTradeNo(OrderUtils.getOrderNo());
        payRequest.setTotalFee(total);
        payRequest.setSpBillCreateIp(LocalIPUtil.getLocalAddr());
        payRequest.setNotifyUrl(staticParam.getNotifyUrl());
        payRequest.setTradeType("JSAPI");
        payRequest.setOpenId(openId);
        Map map = SignUtils.Object2Map(payRequest);
        String sign = SignUtils.md5(map, staticParam.getApiKey());
        System.out.println("sign:" + sign);
        payRequest.setSign(sign);
        XmlMapper xml = new XmlMapper();
        String s = xml.writeValueAsString(payRequest);

        System.out.println("s:" + s);

        HashMap resultMap = HttpUtils.httpRequestMap(staticParam.getPreOrderUrl(), HttpMethod.POST.toString(), s);
        Map responseMap = new HashMap();
        String secondTimestamp = DateUtils.getSecondTimestamp();
        System.out.println("resultMap::::" + resultMap.toString());
        responseMap.put("appId", staticParam.getAppId());
        responseMap.put("timeStamp", secondTimestamp);
        responseMap.put("nonceStr", random);
        responseMap.put("package", "prepay_id=" + resultMap.get("prepay_id").toString());
        responseMap.put("signType", "MD5");
        String responseSign = SignUtils.md5(responseMap, staticParam.getApiKey());
        PayResponse payResponse = new PayResponse();
        payResponse.setAppId(staticParam.getAppId());
        payResponse.setTimeStamp(secondTimestamp);
        payResponse.setNonceStr(random);
        payResponse.setPrepayId("prepay_id=" + resultMap.get("prepay_id").toString());
        payResponse.setSignType("MD5");
        payResponse.setPaySign(responseSign);

        return payResponse;
    }


}
