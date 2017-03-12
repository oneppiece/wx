package com.dzy.wx.global.entity;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:11:47
 */
@Component
public class Signature {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    @Override
    public String toString() {
        return "Singature{" +
                "signature='" + signature + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", echostr='" + echostr + '\'' +
                '}';
    }
}
