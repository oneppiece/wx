package com.dzy.wx.message.entity.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "response_message_voices")
@Entity
//@JsonRootName(value = "Voice")
public class Voice {
    @JacksonXmlProperty(localName = "MediaId")
    @JacksonXmlCData
    @Column(name = "media_id")
    private String mediaId;
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    public Voice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
