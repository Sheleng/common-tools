package com.sheleng.java.commontools.codec.digest;

import com.sheleng.java.commontools.codec.HexUtils;

import java.security.MessageDigest;

/**
 * MDCoder
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class MDCoder {

    private MDCoder() {
        throw new RuntimeException("MDCoder.class can't be instantiated");
    }

    public static byte[] md2(final byte[] source) throws DigestException {
        MessageDigest digest = DigestUtils.getDigest(MessageDigestAlgorithms.MD2);
        return digest.digest(source);
    }

    public static String md2Hex(
            final byte[] source, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(md2(source), toLowerCase);
    }

    public static byte[] md4(final byte[] source) throws DigestException {
        MessageDigest digest = DigestUtils.getDigest(MessageDigestAlgorithms.MD4);
        return digest.digest(source);
    }

    public static String md4Hex(
            final byte[] source, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(md4(source), toLowerCase);
    }

    public static byte[] md5(final byte[] source) throws DigestException {
        MessageDigest digest = DigestUtils.getDigest(MessageDigestAlgorithms.MD5);
        return digest.digest(source);
    }

    public static String md5Hex(
            final byte[] source, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(md5(source), toLowerCase);
    }
}
