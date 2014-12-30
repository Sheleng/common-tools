package com.sheleng.java.commontools.codec.digest;

import com.sheleng.java.commontools.codec.HexUtils;

import java.security.MessageDigest;

/**
 * SHACoder
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class SHACoder {

    private SHACoder() {
        throw new RuntimeException("SHACoder.class can't be instantiated");
    }

    public static byte[] sha1(final byte[] source) throws DigestException {
        MessageDigest digest = DigestUtils.getDigest(MessageDigestAlgorithms.SHA_1);
        return digest.digest(source);
    }

    public static String sha1Hex(
            final byte[] source, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(sha1(source), toLowerCase);
    }

    public static byte[] sha224(final byte[] source) throws DigestException {
        MessageDigest digest = DigestUtils.getDigest(MessageDigestAlgorithms.SHA_224);
        return digest.digest(source);
    }

    public static String sha224Hex(
            final byte[] source, final boolean toLowerCase) throws DigestException {
            return HexUtils.encodeHexString(sha224(source), toLowerCase);
    }

    public static byte[] sha256(final byte[] source) throws DigestException {
        MessageDigest digest = DigestUtils.getDigest(MessageDigestAlgorithms.SHA_256);
        return digest.digest(source);
    }

    public static String sha256Hex(
            final byte[] source, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(sha256(source), toLowerCase);
    }

    public static byte[] sha384(final byte[] source) throws DigestException {
        MessageDigest digest = DigestUtils.getDigest(MessageDigestAlgorithms.SHA_384);
        return digest.digest(source);
    }

    public static String sha384Hex(
            final byte[] source, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(sha384(source), toLowerCase);
    }

    public static byte[] sha512(final byte[] source) throws DigestException {
        MessageDigest digest = DigestUtils.getDigest(MessageDigestAlgorithms.SHA_512);
        return digest.digest(source);
    }

    public static String sha512Hex(
            final byte[] source, final boolean toLowerCase) throws DigestException {
        return HexUtils.encodeHexString(sha512(source), toLowerCase);
    }
}
