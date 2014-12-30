package com.sheleng.java.commontools.codec.digest;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.Security;

/**
 * DigestUtils
 *
 * @author Sheleng
 * @version 0.0.1
 */
class DigestUtils {

    private static final int BUFFER_SIZE = 1024;

    private DigestUtils() {
        throw new RuntimeException("DigestUtils.class can't be instantiated");
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static MessageDigest getDigest(final String algorithm) throws DigestException {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            throw new DigestException(e);
        }
    }

    protected static byte[] digest(final MessageDigest digest, final String data) {
        return digest.digest(data.getBytes());
    }

    protected static byte[] digest(final MessageDigest digest, final byte[] data) {
        return digest.digest(data);
    }

    protected static byte[] digest(final MessageDigest digest, final ByteBuffer data) {
        digest.update(data);
        return digest.digest();
    }

    protected static byte[] digest(
            final MessageDigest digest, final InputStream data) throws IOException {
        final byte[] buffer = new byte[BUFFER_SIZE];
        int count = data.read(buffer, 0, BUFFER_SIZE);
        while (count > -1) {
            digest.update(buffer, 0, count);
            count = data.read(buffer, 0, BUFFER_SIZE);
        }
        return digest.digest();
    }

    public static byte[] initHmacKey(final String algorithm) throws DigestException {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
            SecretKey secretKey = keyGen.generateKey();
            return secretKey.getEncoded();
        } catch (Exception e) {
            throw new DigestException(e);
        }
    }

    public static byte[] encodeHmac(
            final String algorithm, final byte[] data, final byte[] key) throws DigestException {
        try {
            SecretKey secretKey = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (Exception e) {
            throw new DigestException(e);
        }
    }
}
