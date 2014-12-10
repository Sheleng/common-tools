package com.sheleng.java.commontools;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * NumberUtils
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class NumberUtils {

    public static final short U_BYTE_MIN_VALUE = 0, U_BYTE_MAX_VALUE = 255;
    public static final int U_SHORT_MIN_VALUE = 0, U_SHORT_MAX_VALUE = 65535;
    public static final long U_INT_MIN_VALUE = 0, U_INT_MAX_VALUE = 4294967295L;

    private static final String FORMAT_KEY = "0123456789ABCDEF";
    private static final Set<Integer> FORMAT_TYPE = new HashSet<>(Arrays.asList(2, 8, 10, 16));

    /**
     * 取消默认构造函数
     */
    private NumberUtils() {
        throw new RuntimeException("NumberUtils.class can't be instantiated");
    }

    /**
     * 有符号byte转无符号byte，无符号byte用short来替代
     *
     * @param sByte 有符号byte
     * @return 无符号byte，由short类型替代
     */
    public static short sByte2uByte(byte sByte) {
        return (short) (sByte & 0x00FF);
    }

    /**
     * 无符号byte转有符号byte
     *
     * @param uByte 无符号byte
     * @return 有符号byte
     */
    public static byte uByte2sByte(short uByte) {
        if (uByte < U_BYTE_MIN_VALUE) {
            throw new RuntimeException("uByte cannot be minus, uByte = " + uByte);
        }
        if (uByte > U_BYTE_MAX_VALUE) {
            throw new RuntimeException("illegal uByte = " + uByte);
        }
        return (byte) (uByte & 0x00FF);
    }

    /**
     * 有符号short转无符号short，无符号short用int来代替
     *
     * @param sShort 有符号short
     * @return 无符号short，由int类型替代
     */
    public static int sShort2uShort(short sShort) {
        return (int) (sShort & 0x0000FFFF);
    }

    /**
     * 无符号short转有符号short
     *
     * @param uShort 无符号short
     * @return 有符号short
     */
    public static short uShort2sShort(int uShort) {
        if (uShort < U_SHORT_MIN_VALUE) {
            throw new RuntimeException("uShort cannot be minus, uShort = " + uShort);
        }
        if (uShort > U_SHORT_MAX_VALUE) {
            throw new RuntimeException("illegal uShort = " + uShort);
        }
        return (short) (uShort & 0x0000FFFF);
    }

    /**
     * 有符号int转无符号int，无符号int用long来代替
     *
     * @param sInt 有符号int
     * @return 无符号int，由long类型替代
     */
    public static long sInt2uInt(int sInt) {
        return sInt & 0x00000000FFFFFFFFL;
    }

    /**
     * 无符号int转有符号int
     *
     * @param uInt 无符号int
     * @return 有符号int
     */
    public static int uInt2sInt(long uInt) {
        if (uInt < U_INT_MIN_VALUE) {
            throw new RuntimeException("uShort cannot be minus, uInt = " + uInt);
        }
        if (uInt > U_INT_MAX_VALUE) {
            throw new RuntimeException("illegal uInt = " + uInt);
        }
        return (int) (uInt & 0x00000000FFFFFFFFL);
    }

    /**
     * 将short值转成byte数组
     *
     * @param num short值
     * @return short值对应的byte数组
     */
    public static byte[] short2bytes(short num, boolean littleEndian) {
        byte[] bytes = new byte[2];
        if (littleEndian) {
            bytes[0] = (byte) (num >> 0);
            bytes[1] = (byte) (num >> 8);
        } else {
            bytes[1] = (byte) (num >> 0);
            bytes[0] = (byte) (num >> 8);
        }
        return bytes;
    }

    /**
     * 将长度为2的byte数组转成short类型的值
     *
     * @param bytes 长度为2的byte数组
     * @return short类型的值
     */
    public static short bytes2short(byte[] bytes, boolean littleEndian) {
        if (null == bytes) {
            throw new NullPointerException("bytes must not be null");
        } else if (bytes.length != 2) {
            throw new RuntimeException("the length of bytes must be 2");
        } else {
            short ret = 0x0000;
            if (littleEndian) {
                ret |= (0x00FF & (bytes[0] << 0));
                ret |= (0xFF00 & (bytes[1] << 8));
            } else {
                ret |= (0x00FF & (bytes[1] << 0));
                ret |= (0xFF00 & (bytes[0] << 8));
            }
            return ret;
        }
    }

    /**
     * 将int值转成byte数组
     *
     * @param num int值
     * @return int值对应的byte数组
     */
    public static byte[] int2bytes(int num, boolean littleEndian) {
        byte[] bytes = new byte[4];
        if (littleEndian) {
            bytes[0] = (byte) (num >> 0);
            bytes[1] = (byte) (num >> 8);
            bytes[2] = (byte) (num >> 16);
            bytes[3] = (byte) (num >> 24);
        } else {
            bytes[3] = (byte) (num >> 0);
            bytes[2] = (byte) (num >> 8);
            bytes[1] = (byte) (num >> 16);
            bytes[0] = (byte) (num >> 24);
        }
        return bytes;
    }

    /**
     * 将长度为4的byte数组转成int类型的值
     *
     * @param bytes 长度为4的byte数组
     * @return int类型的值
     */
    public static int bytes2int(byte[] bytes, boolean littleEndian) {
        if (null == bytes) {
            throw new NullPointerException("bytes must not be null");
        } else if (bytes.length != 4) {
            throw new RuntimeException("the length of bytes must be 4");
        } else {
            int ret = 0;
            if (littleEndian) {
                ret |= (0x000000FF & (bytes[0] << 0));
                ret |= (0x0000FF00 & (bytes[1] << 8));
                ret |= (0x00FF0000 & (bytes[2] << 16));
                ret |= (0xFF000000 & (bytes[3] << 24));
            } else {
                ret |= (0x000000FF & (bytes[3] << 0));
                ret |= (0x0000FF00 & (bytes[2] << 8));
                ret |= (0x00FF0000 & (bytes[1] << 16));
                ret |= (0xFF000000 & (bytes[0] << 24));
            }
            return ret;
        }
    }

    /**
     * 将long值转成byte数组
     *
     * @param num long值
     * @return long值对应的byte数组
     */
    public static byte[] long2bytes(long num, boolean littleEndian) {
        byte[] bytes = new byte[8];
        if (littleEndian) {
            bytes[0] = (byte) (num >> 0);
            bytes[1] = (byte) (num >> 8);
            bytes[2] = (byte) (num >> 16);
            bytes[3] = (byte) (num >> 24);
            bytes[4] = (byte) (num >> 32);
            bytes[5] = (byte) (num >> 40);
            bytes[6] = (byte) (num >> 48);
            bytes[7] = (byte) (num >> 56);
        } else {
            bytes[7] = (byte) (num >> 0);
            bytes[6] = (byte) (num >> 8);
            bytes[5] = (byte) (num >> 16);
            bytes[4] = (byte) (num >> 24);
            bytes[3] = (byte) (num >> 32);
            bytes[2] = (byte) (num >> 40);
            bytes[1] = (byte) (num >> 48);
            bytes[0] = (byte) (num >> 56);
        }
        return bytes;
    }

    /**
     * 将长度为8的byte数组转成long类型的值
     *
     * @param bytes 长度为8的byte数组
     * @return long类型的值
     */
    public static long bytes2long(byte[] bytes, boolean littleEndian) {
        if (null == bytes) {
            throw new NullPointerException("bytes must not be null");
        } else if (bytes.length != 8) {
            throw new RuntimeException("the length of bytes must be 8");
        } else {
            long ret = 0;
            if (littleEndian) {
                ret |= (0x00000000000000FFL & (((long) bytes[0]) << 0));
                ret |= (0x000000000000FF00L & (((long) bytes[1]) << 8));
                ret |= (0x0000000000FF0000L & (((long) bytes[2]) << 16));
                ret |= (0x00000000FF000000L & (((long) bytes[3]) << 24));
                ret |= (0x000000FF00000000L & (((long) bytes[4]) << 32));
                ret |= (0x0000FF0000000000L & (((long) bytes[5]) << 40));
                ret |= (0x00FF000000000000L & (((long) bytes[6]) << 48));
                ret |= (0xFF00000000000000L & (((long) bytes[7]) << 56));
            } else {
                ret |= (0x00000000000000FFL & (((long) bytes[7]) << 0));
                ret |= (0x000000000000FF00L & (((long) bytes[6]) << 8));
                ret |= (0x0000000000FF0000L & (((long) bytes[5]) << 16));
                ret |= (0x00000000FF000000L & (((long) bytes[4]) << 24));
                ret |= (0x000000FF00000000L & (((long) bytes[3]) << 32));
                ret |= (0x0000FF0000000000L & (((long) bytes[2]) << 40));
                ret |= (0x00FF000000000000L & (((long) bytes[1]) << 48));
                ret |= (0xFF00000000000000L & (((long) bytes[0]) << 56));
            }
            return ret;
        }
    }

    /**
     * 返回将数值转化成指定进制格式的字符串表示
     */
    public static <T extends Number> String format(T number, int base, boolean ignoreHead) {
        if (number == null) {
            throw new IllegalArgumentException("number == null.");
        }
        if (!FORMAT_TYPE.contains(base)) {
            throw new RuntimeException("No support format type = " + base);
        }
        StringBuffer buffer = new StringBuffer();
        long value = number.longValue();
        while (value >= base) {
            buffer.append(FORMAT_KEY.charAt((int) (value % base)));
            value /= base;
        }
        if (value >= 0) {
            buffer.append(FORMAT_KEY.charAt((int) value));
        }
        String head = "";
        if (ignoreHead == false) {
            switch (base) {
                case 2:
                    head = "b";
                    break;
                case 8:
                    head = "0";
                    break;
                case 10:
                    head = "d";
                    break;
                case 16:
                    head = "0x";
                    break;
            }
        }
        return head + buffer.reverse().toString();
    }
}
