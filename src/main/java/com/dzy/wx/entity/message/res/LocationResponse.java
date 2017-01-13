package com.dzy.wx.entity.message.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class LocationResponse extends RespMessage {
    // 地理位置维度
    @JsonProperty("Location_X")
    @Column(name = "location_x")
    private String locationX;
    // 地理位置经度
    @JsonProperty("Location_Y")
    @Column(name = "location_y")
    private String locationY;
    // 地图缩放大小
    @JsonProperty("Scale")
    @Column
    private String scale;
    // 地理位置信息
    @JsonProperty("Label")
    @Column
    private String label;

    public LocationResponse(String toUserName, String fromUserName, Long createTime, String msgType, String locationX, String locationY, String scale, String label) {
        super(toUserName, fromUserName, createTime, msgType);
        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }


    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
