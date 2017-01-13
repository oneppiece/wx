package com.dzy.wx.entity.media;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Entity
@Table
public class Media {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;
    @Column(name = "total_count")
    private Integer totalCount;
    @Column(name = "item_count")
    private Integer itemCount;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MediaItem> mediaItems;

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", totalCount=" + totalCount +
                ", itemCount=" + itemCount +
                ", mediaItems=" + mediaItems +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Set<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(Set<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    public Media(Integer totalCount, Integer itemCount, Set<MediaItem> mediaItems) {

        this.totalCount = totalCount;
        this.itemCount = itemCount;
        this.mediaItems = mediaItems;
    }

    public Media() {
    }
}
