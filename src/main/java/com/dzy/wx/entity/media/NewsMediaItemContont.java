package com.dzy.wx.entity.media;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
@Entity
public class NewsMediaItemContont {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title")
    private String title;
    @Column(name = "thumb_media_id")
    private String thumbMediaId;
    @Column(name = "show_cover_pic")
    private Boolean showCoverPic;
    @Column(name = "author")
    private String author;
    @Column(name = "digest")
    private String digest;
    @Column(name = "content")
    private String content;
    @Column(name = "url")
    private String url;
    @Column(name = "content_source_url")
    private String contentSourceUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public Boolean getShowCoverPic() {
        return showCoverPic;
    }

    public void setShowCoverPic(Boolean showCoverPic) {
        this.showCoverPic = showCoverPic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    @Override
    public String toString() {
        return "NewsMediaItemContont{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", thumbMediaId='" + thumbMediaId + '\'' +
                ", showCoverPic='" + showCoverPic + '\'' +
                ", author='" + author + '\'' +
                ", digest='" + digest + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", contentSourceUrl='" + contentSourceUrl + '\'' +
                '}';
    }
}
