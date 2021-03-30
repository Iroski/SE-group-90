package com.se90.java.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ：YanBo Zhang
 * @date ：Created in 2021 2021/3/29 19:30
 * @description：
 * @modified By：
 * @version:
 */
public class Encryption {
    public static String getMD5Str(String str) {

        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16 means converted to hexadecimal number
        assert digest != null;
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }

    public static void main(String[] args) {
        System.out.println(getMD5Str("112133"));
    }
}
