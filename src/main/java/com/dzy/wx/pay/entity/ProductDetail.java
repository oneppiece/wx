package com.dzy.wx.pay.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by 16440 on 2017/3/9.
 */
@JacksonXmlRootElement(localName = "goods_detail")
public class ProductDetail {

    private String goods_id;
    private  String wxpay_goods_id;
    private   String goods_name;
    private   int quantity;
    private   int price;
    private  String goods_category;
    private   String body;
}
