package com.dzy.wx.message.entity.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "response_message_videos")
@Entity
public class Video {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;
    @JsonProperty("MediaId")
    @JacksonXmlCData
    @Column(name = "media_id")
    private String mediaId;
    @JsonProperty(value = "Title", defaultValue = "默认标题", required = true)
    @JacksonXmlCData
    private String title;
    @JsonProperty(value = "Description", defaultValue = "默认描述")
    @JacksonXmlCData
    private String description;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Video() {
    }

    public Video(String mediaId, String title, String description) {

        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }
}
