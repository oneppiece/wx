package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table
@Entity
public class Image {
    @JsonProperty("MediaId")
    @JacksonXmlCData
    @Column(name = "media_id")
    private String mediaId;
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

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
