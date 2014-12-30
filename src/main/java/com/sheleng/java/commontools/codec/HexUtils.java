package com.sheleng.java.commontools.codec;

import com.sheleng.java.commontools.codec.digest.DigestException;

/**
 * HexUtils
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class HexUtils {

    private static final char[] DIGITS_LOWER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int len = data.length;
        // ret.length = len * 2
        final char[] ret = new char[len << 1];

        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            ret[j++] = toDigits[(0x0F & data[i])];
        }
        return ret;
    }

    public static String encodeHexString(final byte[] data, final boolean toLowerCase) {
        return new String(encodeHex(data, toLowerCase));
    }

    public static byte[] decodeHex(final char[] data) throws DigestException {
        final int len = data.length;

        if ((len & 0x01) != 0) {
            throw new DigestException("Odd number of characters.");
        }
        // ret.length = len / 2
        final byte[] ret = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j++) << 4 | toDigit(data[j], j++);
            ret[i] = (byte) (f & 0xFF);
        }
        return ret;
    }

    protected static int toDigit(final char ch, final int index) throws DigestException {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new DigestException(
                    "Illegal hex character " + ch + " at index " + index);
        }
        return digit;
    }
}
