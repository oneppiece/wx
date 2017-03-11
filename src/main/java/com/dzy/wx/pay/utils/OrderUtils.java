package com.dzy.wx.pay.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 16440 on 2017/3/10.
 */
public class OrderUtils {
    public static String getOrderNo() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }
}
