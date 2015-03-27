package com.sheleng.java.commontools.codec;

import com.sheleng.java.commontools.system.SystemProperties;
import org.apache.commons.codec.binary.Base64;

/**
 * Base64Coder,依赖Commons Codec工具包
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class Base64Coder {

    private Base64Coder() {
        throw new RuntimeException("Base64Coder.class can't be instantiated");
    }

    /**
     * 对指定的明文采用Base64编码，未遵循RFC 2045
     *
     * @param data        明文
     * @param charsetName 将明文转成字节数组时采用的字符集
     * @return 密文
     * @throws Exception
     */
    public static String encode(String data, String charsetName) throws Exception {
        byte[] bytes = Base64.encodeBase64(data.getBytes(charsetName));
        return new String(bytes, charsetName);
    }

    /**
     * 对指定的明文采用Base64编码，未遵循RFC 2045
     *
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String encode(String data) throws Exception {
        return encode(data, SystemProperties.getFileEncoding());
    }

    /**
     * 对指定的明文采用Base64编码，遵循RFC 2045
     *
     * @param data        明文
     * @param charsetName 将明文转成字节数组时采用的字符集
     * @return 密文
     * @throws Exception
     */
    public static String encodeSafe(String data, String charsetName) throws Exception {
        byte[] bytes = Base64.encodeBase64Chunked(data.getBytes(charsetName));
        return new String(bytes, charsetName);
    }

    /**
     * 对指定的明文采用Base64编码，遵循RFC 2045
     *
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String encodeSafe(String data) throws Exception {
        return encodeSafe(data, SystemProperties.getFileEncoding());
    }

    /**
     * 对指定的明文采用URL Base64编码
     *
     * @param data        明文
     * @param charsetName 将明文转成字节数组时采用的字符集
     * @return 密文
     * @throws Exception
     */
    public static String encodeURLSafe(String data, String charsetName) throws Exception {
        byte[] bytes = Base64.encodeBase64URLSafe(data.getBytes(charsetName));
        return new String(bytes, charsetName);
    }

    /**
     * 对指定的明文采用URL Base64编码
     *
     * @param data 明文
     * @return 密文
     * @throws Exception
     */
    public static String encodeURLSafe(String data) throws Exception {
        return encodeURLSafe(data, SystemProperties.getFileEncoding());
    }

    /**
     * 对指定的密文采用Base64解码
     *
     * @param data        密文
     * @param charsetName 将密文转成字节数组时采用的字符集
     * @return 明文
     * @throws Exception
     */
    public static String decode(String data, String charsetName) throws Exception {
        byte[] bytes = Base64.decodeBase64(data.getBytes(charsetName));
        return new String(bytes, charsetName);
    }

    /**
     * 对指定的密文采用Base64解码
     *
     * @param data 密文
     * @return 明文
     * @throws Exception
     */
    public static String decode(String data) throws Exception {
        return decode(data, SystemProperties.getFileEncoding());
    }
}
