package com.sheleng.java.commontools;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class StringUtils {

    /**
     * 取消默认构造函数
     */
    private StringUtils() {
        throw new RuntimeException("StringUtils.class can't be instantiated");
    }

    /**
     * byte数组转字符串方法
     *
     * @param source      byte数组
     * @param charsetName 指定的字符集解码
     * @return 由byte数组转出的字符串
     * @throws IllegalArgumentException,UnsupportedEncodingException
     */
    public static String bytes2string(byte[] source, String charsetName)
            throws IllegalArgumentException, UnsupportedEncodingException {
        if (source == null) {
            throw new IllegalArgumentException("source == null.");
        }
        if (charsetName == null) {
            throw new IllegalArgumentException("charsetName == null.");
        }
        int len = source.length;
        String result = new String(source, 0, len, charsetName);
        return result;
    }

    /**
     * 字符串转byte数组方法
     *
     * @param source      待转字符串
     * @param charsetName 指定的字符集解码
     * @return 由字符串转出的byte数组
     * @throws IllegalArgumentException,UnsupportedEncodingException
     */
    public static byte[] string2bytes(String source, String charsetName)
            throws IllegalArgumentException, UnsupportedEncodingException {
        if (source == null) {
            throw new IllegalArgumentException("source == null.");
        }
        if (charsetName == null) {
            throw new IllegalArgumentException("charsetName == null.");
        }
        return source.getBytes(charsetName);
    }
}
