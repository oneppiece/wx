package com.dzy.wx.entity.media;

import com.dzy.wx.enums.MediaType;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class MediaPage {
    private MediaType type;
    private Integer offset;
    private Integer count;

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public MediaPage(MediaType type, Integer offset, Integer count) {
        this.type = type;
        this.offset = offset;
        this.count = count;
    }
}
