package com.dzy.wx.entity.media;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Entity
public class NewsMediaItem extends MediaItem {
    public NewsMediaItem(String mediaId, String updateTime, Set<NewsMediaItemContont> newsMediaItemContonts) {
        super(mediaId, updateTime);
        this.newsMediaItemContonts = newsMediaItemContonts;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<NewsMediaItemContont> newsMediaItemContonts;

    public Set<NewsMediaItemContont> getNewsMediaItemContonts() {
        return newsMediaItemContonts;
    }

    public void setNewsMediaItemContonts(Set<NewsMediaItemContont> newsMediaItemContonts) {
        this.newsMediaItemContonts = newsMediaItemContonts;
    }

    public NewsMediaItem(String mediaId, String updateTime) {
        super(mediaId, updateTime);
    }
}
