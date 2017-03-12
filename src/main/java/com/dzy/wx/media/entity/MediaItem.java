package com.dzy.wx.media.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Entity
public abstract class MediaItem {
    public MediaItem() {
    }

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "media_id")
    private String mediaId;
    @Column(name = "update_time")
    private String updateTime;

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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public MediaItem(String mediaId, String updateTime) {
        this.mediaId = mediaId;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "id=" + id +
                ", mediaId='" + mediaId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
