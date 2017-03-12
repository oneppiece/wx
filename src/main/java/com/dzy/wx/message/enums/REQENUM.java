package com.dzy.wx.message.enums;

/**
 * Created by 16440 on 2017/3/11.
 */
public enum REQENUM {
    //接收的文本消息
    reqMessageTypeText("text"),
    //接收的图片消息
    reqMessageTypeImage("image"),
    //接收的链接消息
    reqMessageTypeLink("link"),
    //接收的地理位置消息
    reqMessageTypeLocation("location"),
    //接收的语音消息
    reqMessageTypeVoice("voice"),
    //接收的视频消息
    reqMessageTypeVideo("video"),
    //接收的小视频消息
    reqMessageTypeShortVideo("shortvideo");
    public final String value;

    REQENUM(String value) {
        this.value = value;
    }
}
