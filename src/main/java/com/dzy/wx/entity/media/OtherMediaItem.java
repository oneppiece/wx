package com.dzy.wx.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Entity
public class OtherMediaItem extends MediaItem {
    @Column
    private String name;
    @Column
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public OtherMediaItem(String mediaId, String updateTime, String name, String url) {
        super(mediaId, updateTime);
        this.name = name;
        this.url = url;
    }
}
