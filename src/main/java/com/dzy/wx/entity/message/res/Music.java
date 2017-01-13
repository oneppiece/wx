package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Table
@Entity
public class Music {
    @Id
    @GeneratedValue
    private Integer id;
    // 音乐名称
    @JsonProperty("Title")
    @JacksonXmlCData
    private String title;
    // 音乐描述
    @JsonProperty("Description")
    @JacksonXmlCData
    private String description;
    // 音乐链接
    @JsonProperty("MusicUrl")
    @JacksonXmlCData
    @Column(name = "music_url")
    private String musicUrl;
    // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
    @JsonProperty("HQMusicUrl")
    @JacksonXmlCData
    @Column(name = "hq_music_url")
    private String hQMusicUrl;
    // 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
    @JsonProperty("ThumbMediaId")
    @JacksonXmlCData
    @Column(name = "thumb_media_id")
    private String thumbMediaId;

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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
