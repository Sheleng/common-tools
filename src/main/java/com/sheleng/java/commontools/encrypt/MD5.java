package com.sheleng.java.commontools.encrypt;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class MD5 {
    // 加密算法
    private static final String ALGORITHM = "MD5";
    // 用于加密的字符
    private static final char UPPER_MD5_KEY[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char LOWER_MD5_KEY[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final Logger LOGGER = Logger.getLogger(MD5.class);

    /**
     * 取消默认构造函数
     */
    private MD5() {
        throw new RuntimeException("MD5.class can't be instantiated");
    }

    /**
     * 获取16位的MD5加密结果
     *
     * @return 16位的MD5加密结果
     */
    public static String getMD5_16(String source, boolean upper) {
        String md5_32 = getMD5_32(source, upper);
        if (md5_32 == null) {
            return null;
        } else {
            return md5_32.substring(8, 24);
        }
    }

    /**
     * 获取32位的MD5加密结果
     *
     * @return 32位的MD5加密结果
     */
    public static String getMD5_32(String source, boolean upper) {
        if (source == null) {
            return null;
        }
        try {
            char[] md5_key = upper ? UPPER_MD5_KEY : LOWER_MD5_KEY;
            // 使用平台的默认字符集将此字符串source编码为byte数组
            byte[] sourceArray = source.getBytes();
            // 获得指定摘要算法的 MessageDigest对象，此处为MD5
            // MessageDigest类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
            // 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest digestInstance = MessageDigest.getInstance(ALGORITHM);
            // MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            digestInstance.update(sourceArray);
            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] cipherBytes = digestInstance.digest();
            // 把密文转换成十六进制的字符串形式
            int cipherLen = cipherBytes.length;
            char cipherChars[] = new char[cipherLen * 2];
            int k = 0;
            for (int i = 0; i < cipherLen; i++) {   //  i = 0
                byte cipher = cipherBytes[i];     //95
                cipherChars[k++] = md5_key[cipher >>> 4 & 0xf];    //5
                cipherChars[k++] = md5_key[cipher & 0xf];   //F
            }
            // 返回经过加密后的字符串
            return new String(cipherChars);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
