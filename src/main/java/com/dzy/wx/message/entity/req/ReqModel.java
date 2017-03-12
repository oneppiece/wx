package com.dzy.wx.message.entity.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 请求的基类.
 * 若注解为@MappedSuperclass,每个继承此类的实体类都将生成一张表。
 * 若注解为@Entity,则只生成一张表.
 * 若子类中含有@Table,也会生成表
 */
@Entity
@JacksonXmlRootElement(localName = "xml")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"id"})
public class ReqModel {

    @Id
    @GeneratedValue
    @JsonIgnore
    protected Integer id;
    @Column(name = "to_user_name")
    @JsonProperty("ToUserName")
    protected String toUserName; //开发者微信号
    @JsonProperty("FromUserName")
    @Column(name = "from_user_name")
    protected String fromUserName; //发送方帐号（一个OpenID）
    @JsonProperty("CreateTime")
    @Column(name = "crate_time")
    protected Double createTime;//消息创建时间 （整型）
    @JsonProperty("MsgType")
    @Column(name = "msg_type")
    protected String msgType;//消息类型，event

    public ReqModel() {
    }

    public ReqModel(String toUserName, String fromUserName, Double createTime, String msgType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "ReqModel{" +
                "id=" + id +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Double getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Double createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
