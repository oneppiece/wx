package com.dzy.wx.message.entity.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:16:42
 */
@Table(name = "resp_message")
@Entity
@JacksonXmlRootElement(localName = "xml")
public abstract class RespMessage {
    public RespMessage(String toUserName, String fromUserName, Long createTime, String msgType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
    }

    public RespMessage() {
    }

    // 接收方帐号（收到的OpenID）
    @JsonProperty(value = "ToUserName")
    @JacksonXmlCData
    @Column(name = "to_user_name")
    private String toUserName;
    // 开发者微信号
    @JsonProperty("FromUserName")
    @JacksonXmlCData
    @Column(name = "from_user_name")
    private String fromUserName;
    // 消息创建时间 （整型）
    @JsonProperty("CreateTime")
    @Column(name = "create_time", nullable = false)
    private Long createTime;
    // 消息类型（text/music/news）
    @JsonProperty("MsgType")
    @JacksonXmlCData
    @Column(name = "msg_type", nullable = false)
    private String msgType;
    // 消息id，64位整型
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
