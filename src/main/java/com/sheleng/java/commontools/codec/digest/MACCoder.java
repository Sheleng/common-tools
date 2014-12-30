package com.sheleng.java.commontools.codec.digest;

import com.sheleng.java.commontools.codec.HexUtils;

/**
 * MACCoder
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class MACCoder {

    private MACCoder() {
        throw new RuntimeException("MACCoder.class can't be instantiated");
    }

    /*      Hmac_MD2        */
    public static byte[] hmacMd2Key() throws DigestException {
        return DigestUtils.initHmacKey(MessageDigestAlgorithms.HMAC_MD2);
    }

    public static byte[] hmacMd2(final byte[] data, final byte[] key) throws DigestException {
        return DigestUtils.encodeHmac(MessageDigestAlgorithms.HMAC_MD2, data, key);
    }

    public static String hmacMd2Hex(
            final byte[] data, final byte[] key, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(hmacMd2(data, key), toLowerCase);
    }

    /*      Hmac_MD4        */
    public static byte[] hmacMd4Key() throws DigestException {
        return DigestUtils.initHmacKey(MessageDigestAlgorithms.HMAC_MD4);
    }

    public static byte[] hmacMd4(final byte[] data, final byte[] key) throws DigestException {
        return DigestUtils.encodeHmac(MessageDigestAlgorithms.HMAC_MD4, data, key);
    }

    public static String hmacMd4Hex(
            final byte[] data, final byte[] key, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(hmacMd4(data, key), toLowerCase);
    }

    /*      Hmac_MD5       */
    public static byte[] hmacMd5Key() throws DigestException {
        return DigestUtils.initHmacKey(MessageDigestAlgorithms.HMAC_MD5);
    }

    public static byte[] hmacMd5(final byte[] data, final byte[] key) throws DigestException {
        return DigestUtils.encodeHmac(MessageDigestAlgorithms.HMAC_MD5, data, key);
    }

    public static String hmacMd5Hex(
            final byte[] data, final byte[] key, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(hmacMd5(data, key), toLowerCase);
    }

    /*      Hmac_Sha1      */
    public static byte[] hmacSha1Key() throws DigestException {
        return DigestUtils.initHmacKey(MessageDigestAlgorithms.HMAC_SHA1);
    }

    public static byte[] hmacSha1(final byte[] data, final byte[] key) throws DigestException {
        return DigestUtils.encodeHmac(MessageDigestAlgorithms.HMAC_SHA1, data, key);
    }

    public static String hmacSha1Hex(
            final byte[] data, final byte[] key, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(hmacSha1(data, key), toLowerCase);
    }

    /*      Hmac_Sha224      */
    public static byte[] hmacSha224Key() throws DigestException {
        return DigestUtils.initHmacKey(MessageDigestAlgorithms.HMAC_SHA224);
    }

    public static byte[] hmacSha224(final byte[] data, final byte[] key) throws DigestException {
        return DigestUtils.encodeHmac(MessageDigestAlgorithms.HMAC_SHA224, data, key);
    }

    public static String hmacSha224Hex(
            final byte[] data, final byte[] key, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(hmacSha224(data, key), toLowerCase);
    }

    /*      Hmac_Sha256      */
    public static byte[] hmacSha256Key() throws DigestException {
        return DigestUtils.initHmacKey(MessageDigestAlgorithms.HMAC_SHA256);
    }

    public static byte[] hmacSha256(final byte[] data, final byte[] key) throws DigestException {
        return DigestUtils.encodeHmac(MessageDigestAlgorithms.HMAC_SHA256, data, key);
    }

    public static String hmacSha256Hex(
            final byte[] data, final byte[] key, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(hmacSha256(data, key), toLowerCase);
    }

    /*      Hmac_Sha384      */
    public static byte[] hmacSha384Key() throws DigestException {
        return DigestUtils.initHmacKey(MessageDigestAlgorithms.HMAC_SHA384);
    }

    public static byte[] hmacSha384(final byte[] data, final byte[] key) throws DigestException {
        return DigestUtils.encodeHmac(MessageDigestAlgorithms.HMAC_SHA384, data, key);
    }

    public static String hmacSha384Hex(
            final byte[] data, final byte[] key, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(hmacSha384(data, key), toLowerCase);
    }

    /*      Hmac_Sha512     */
    public static byte[] hmacSha512Key() throws DigestException {
        return DigestUtils.initHmacKey(MessageDigestAlgorithms.HMAC_SHA512);
    }

    public static byte[] hmacSha512(final byte[] data, final byte[] key) throws DigestException {
        return DigestUtils.encodeHmac(MessageDigestAlgorithms.HMAC_SHA512, data, key);
    }

    public static String hmacSha512Hex(
            final byte[] data, final byte[] key, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(hmacSha512(data, key), toLowerCase);
    }
}
