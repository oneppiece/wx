package com.dzy.wx.pay.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.stereotype.Component;

/**
 * Created by 16440 on 2017/3/9.
 */
@Component
@JacksonXmlRootElement(localName = "xml")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"attach", "detail"})
public class PayRequest {
    @JsonProperty(value = "appid", index = 1)
    private String appId;
    //@JsonProperty(value = "attach", index = 2)
    // @JacksonXmlElementWrapper
    private String attach;
    @JsonProperty(value = "body", index = 2)
    private String body;
    @JsonProperty(value = "mch_id", index = 3)
    private String mchId;
    // @JsonProperty(value = "detail", index = 5)
    private String detail;
    @JsonProperty(value = "nonce_str", index = 4)
    private String nonceStr;
    @JsonProperty(value = "notify_url", index = 5)
    private String notifyUrl;
    @JsonProperty(value = "openid", index = 6)
    private String openId;
    @JsonProperty(value = "out_trade_no", index = 7)
    private String outTradeNo;
    @JsonProperty(value = "sign", index = 999)
    private String sign;
    @JsonProperty(value = "spbill_create_ip", index = 8)
    private String spBillCreateIp;
    @JsonProperty(value = "trade_type", index = 10)
    private String tradeType;
    @JsonProperty(value = "total_fee", index = 9)
    private int totalFee;

    public String getAppId() {
        return appId;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }


    public String getSpBillCreateIp() {
        return spBillCreateIp;
    }

    public void setSpBillCreateIp(String spBillCreateIp) {
        this.spBillCreateIp = spBillCreateIp;
    }


    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
