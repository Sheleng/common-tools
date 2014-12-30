package com.sheleng.java.commontools.codec.digest;

/**
 * MessageDigestAlgorithms
 *
 * @author Sheleng
 * @version 0.0.1
 */
class MessageDigestAlgorithms {

    private MessageDigestAlgorithms() {
        throw new RuntimeException("MessageDigestAlgorithms.class can't be instantiated");
    }

    public static final String MD2 = "MD2";
    public static final String MD4 = "MD4";
    public static final String MD5 = "MD5";

    public static final String SHA_1 = "SHA-1";
    public static final String SHA_224 = "SHA-224";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";

    public static final String HMAC_MD2 = "HmacMD2";
    public static final String HMAC_MD4 = "HmacMD4";
    public static final String HMAC_MD5 = "HmacMD5";
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String HMAC_SHA224 = "HmacSHA224";
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String HMAC_SHA384 = "HmacSHA384";
    public static final String HMAC_SHA512 = "HmacSHA512";
}
