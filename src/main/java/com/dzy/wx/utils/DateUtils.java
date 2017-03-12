package com.dzy.wx.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateUtils {
    public static Date addDate(Date date) {
        date.setTime(date.getTime() + 120 * 60 * 1000);
        return date;
    }

    @Test
    public void test() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(addDate(date));
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @return
     */
    public static String getSecondTimestamp() {
        String timestamp = String.valueOf(new Date().getTime());
        int length = timestamp.length();
        return String.valueOf(timestamp.substring(0, length - 3));
    }
}
