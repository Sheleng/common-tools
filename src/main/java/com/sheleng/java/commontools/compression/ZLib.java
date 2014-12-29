package com.sheleng.java.commontools.compression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * 不可被实例化的ZLib压缩、解压缩工具类
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class ZLib {
    /**
     * 最佳压缩的压缩级别。
     */
    public static final int BEST_COMPRESSION = Deflater.BEST_COMPRESSION;
    /**
     * 默认压缩的压缩级别。
     */
    public static final int DEFAULT_COMPRESSION = Deflater.DEFAULT_COMPRESSION;
    /**
     * 最快压缩的压缩级别。
     */
    public static final int BEST_SPEED = Deflater.BEST_SPEED;
    /**
     * 默认压缩策略。
     */
    public static final int DEFAULT_STRATEGY = Deflater.DEFAULT_STRATEGY;

    /**
     * 仅适用于 Huffman 编码的压缩策略。
     */
    public static final int HUFFMAN_ONLY = Deflater.HUFFMAN_ONLY;
    /**
     * 无压缩的压缩级别。
     */
    public static final int NO_COMPRESSION = Deflater.NO_COMPRESSION;

    /**
     * 取消默认构造函数
     */
    private ZLib() {
        throw new RuntimeException("ZLib.class cannot be instantiated");
    }

    /**
     * 压缩方法
     *
     * @param source 待压缩数据
     * @param level  压缩级别
     * @return byte[] 压缩后的数据
     */
    public static byte[] compress(byte[] source, int level, int strategy) {
        byte[] result = new byte[0];
        Deflater compressor = new Deflater(level);
        compressor.setStrategy(strategy);
        compressor.setInput(source);
        // 指示压缩应当以输入缓冲区的当前内容结尾
        compressor.finish();
        // 创建一个新的byte数组输出流，指定缓存区大小为source数组的长度
        ByteArrayOutputStream bOutStream = new ByteArrayOutputStream(source.length);
        try {
            byte[] buffer = new byte[1024];
            // 如果已到达压缩数据输出流的结尾,则跳出循环
            while (!compressor.finished()) {
                // 使用压缩数据填充指定缓冲区
                int len = compressor.deflate(buffer);
                bOutStream.write(buffer, 0, len);
            }
            result = bOutStream.toByteArray();
        } catch (Exception e) {
            result = source;
            e.printStackTrace();
        } finally {
            try {
                bOutStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 关闭解压缩器并放弃所有未处理的输入
        compressor.end();
        return result;
    }

    /**
     * 采用默认的压缩级别和压缩策略进行压缩的压缩方法
     *
     * @param source 待压缩的数据
     * @return byte[] 压缩后的数据
     */
    public static byte[] compress(byte[] source) {
        byte[] result = compress(source, DEFAULT_COMPRESSION, DEFAULT_STRATEGY);
        return result;
    }

    /**
     * 解压缩
     *
     * @param source 待解压的数据
     * @return byte[] 解压缩后的数据
     */
    public static byte[] decompress(byte[] source) throws DataFormatException, IOException {
        byte[] result = new byte[0];
        Inflater decompressor = new Inflater();
        decompressor.setInput(source);
        ByteArrayOutputStream bOutStream = new ByteArrayOutputStream(source.length);
        try {
            byte[] buffer = new byte[1024];
            while (!decompressor.finished()) {
                int len = decompressor.inflate(buffer);
                bOutStream.write(buffer, 0, len);
            }
            result = bOutStream.toByteArray();
        } catch (DataFormatException e) {
            result = source;
            e.printStackTrace();
        } finally {
            try {
                bOutStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        decompressor.end();
        return result;
    }
}
