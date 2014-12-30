package com.sheleng.java.commontools.codec.digest;

/**
 * MessageDigestAlgorithms
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class MessageDigestAlgorithms {

    private MessageDigestAlgorithms() {
        throw new RuntimeException("MessageDigestAlgorithms.class can't be instantiated");
    }

    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";

    public static final String SHA_1 = "SHA-1";
    public static final String SHA_224 = "SHA-224";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";
}
