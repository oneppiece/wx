package com.dzy.wx.menu.entity;

/**
 * Created by Administrator on 2016/12/25.
 * User:Administrator
 * Date:2016/12/25
 * Time:17:09
 */
public class MediaButton extends Button {
    private String mediaId;
    private String type;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = "media";
    }
}
