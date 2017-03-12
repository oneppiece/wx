package com.dzy.wx.message.entity.resp;

import javax.persistence.*;

@Entity
@Table(name = "response_temple")
public class ResponseTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String msgType;

    public ResponseTemplate(int id, String msgType) {
        this.id = id;
        this.msgType = msgType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }


    public ResponseTemplate() {

    }


}
