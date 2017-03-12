package com.dzy.wx.message.enums;

/**
 * 消息和事件的类型枚举
 * Created by Administrator on 2016/12/26.
 * User:Administrator
 * Date:2016/12/26
 * Time:15:30
 */
public enum MessageType {
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
    reqMessageTypeShortVideo("shortvideo"),

    //回复文本消息
    respMessageTypeText("text"),
    //回复音乐消息
    respMessageTypeMusic("music"),
    //回复图文消息
    respMessageTypeNews("news"),
    //回复图片消息
    respMessageTypeImage("image"),
    // 回复语音消息
    respMessageTypeVoice("voice"),
    //回复视频消息
    respMessageTypeVideo("video"),

    reqMessageTypeEvent("event"),
    //关注
    EventTypeSubscribe("subscribe"),
    //取消关注
    EventTypeUnSubscribe("unsubscribe"),
    //点击菜单跳转链接时的事件
    EventTypeView("VIEW"),
    //上报地理位置事件
    EventTypeLocation("LOCATION"),
    //扫描带参数二维码事件
    EventTypeScan("SCAN"),
    //点击菜单拉取消息时的事件推送
    EventTypeClick("CLICK");


    public String value;

    MessageType(String value) {
        this.value = value;
    }

}
