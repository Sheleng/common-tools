package com.sheleng.java.commontools;

/**
 * NumberUtils
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class NumberUtils {

    /**
     * 取消默认构造函数
     */
    private NumberUtils() {
        throw new RuntimeException("NumberUtils.class cannot be instantiated");
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
}
