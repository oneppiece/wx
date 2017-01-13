package com.dzy.wx.entity.message.req;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 上报地理位置事件
 * Created by Administrator on 2016/12/27.
 * User:Administrator
 * Date:2016/12/27
 * Time:14:28
 */
@Entity
public class LocationEvent extends ReqEvent {
    public LocationEvent(String toUserName, String fromUserName, Double createTime, String msgType, String event, Double latitude, Double longitude, Double precision) {
        super(toUserName, fromUserName, createTime, msgType, event);
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
    }

    @Column
    private Double latitude;//地理位置纬度
    @Column
    private Double longitude;//地理位置经度
    @Column(name = "precision_event")
    private Double precision;//地理位置精度


    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }

}
