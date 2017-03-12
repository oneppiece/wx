package com.dzy.wx.global.service;

import com.dzy.wx.global.StaticParam;
import com.dzy.wx.global.entity.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
public class SignatureUtils {

    @Autowired
    private static StaticParam staticParam;

    /**
     * 将参数排序，计算签名
     *
     * @param signature
     * @return
     */
    public static String checkSignature(Signature signature) {
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[]{staticParam.getToken(), signature.getTimestamp(), signature.getNonce()};
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 2）将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        boolean result = tmpStr != null && tmpStr.equals(signature.getSignature().toUpperCase());
        return result == true ? signature.getEchostr() : null;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }
}
