package com.dzy.wx.Utils;

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
}
