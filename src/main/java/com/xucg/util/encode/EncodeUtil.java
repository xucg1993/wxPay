package com.xucg.util.encode;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class EncodeUtil {

    public static String getMD5For32(String input) {
        return encode(input, "MD5", 16, "UTF8");
    }

    public static String getMD5For32UTF8(String input) {
        return encode(input, "MD5", 16, "UTF8");
    }

    public static String getSHA1(String input) {
        return encode(input, "SHA-1", 20, "UTF8");
    }

    public static String getSHA1UTF8(String input) {
        return encode(input, "SHA-1", 20, "UTF8");
    }

    public static String encode(String input, String algorithm, int length, String encoding) {
        byte[] source;
        try {
            source = input.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            source = input.getBytes();
        }
        String result = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(source);
            //The result should be one 128 integer
            byte temp[] = md.digest();
            char str[] = new char[length * 2];
            int k = 0;
            for (int i = 0; i < length; i++) {
                byte byte0 = temp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            result = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encodeStr(String value) {
        try {
            return URLEncoder.encode(value, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeStrByDual(String value) {

        return encodeStr(encodeStr(value));
    }

    public static String decodeStr(String value) {
        try {
            return URLDecoder.decode(value, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getMD5For32("richerhead"));
    }
}
