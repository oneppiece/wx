package com.dzy.wx.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "open_id")
    private String openId;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "sex")
    private Boolean sex;
    private String province;
    private String city;
    private String country;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", createTime=" + createTime +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", privilege='" + privilege + '\'' +
                ", unionId='" + unionId + '\'' +
                '}';
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User() {
    }

    @Column(name = "head_img_url")
    private String headImgUrl;
    private String privilege;
    @Column(name = "union_id")
    private String unionId;

    public User(String openId, String nickName, Boolean sex, String province, String city, String country, Date createTime, String headImgUrl, String privilege, String unionId) {
        this.openId = openId;
        this.nickName = nickName;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.country = country;
        this.createTime = createTime;
        this.headImgUrl = headImgUrl;
        this.privilege = privilege;
        this.unionId = unionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
