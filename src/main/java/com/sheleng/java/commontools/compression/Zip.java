package com.sheleng.java.commontools.compression;

import java.io.*;
import java.util.zip.*;

/**
 * Zip算法的压缩、解压缩工具类
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class Zip {

    private Zip() {
        throw new RuntimeException("Zip.class can't be instantiated");
    }

    private static final String EXT = ".zip";
    private static final String BASE_DIR = "";
    private static final String PATH = File.separator;
    private static final int BUFFER_SIZE = 1024;

    public static void compress(String srcPath) throws Exception {
        compress(new File(srcPath));
    }

    public static void compress(File srcFile) throws Exception {
        String destPath = srcFile.getParent() + PATH + srcFile.getName() + EXT;
        compress(srcFile, destPath);
    }

    public static void compress(File srcFile, String destPath) throws Exception {
        compress(srcFile, new File(destPath));
    }

    public static void compress(File src, File dest) throws Exception {
        // 对输出文件做CRC32校验
        CheckedOutputStream cOutput =
                new CheckedOutputStream(new FileOutputStream(dest), new CRC32());
        ZipOutputStream zOutput = new ZipOutputStream(cOutput);
        compress(src, zOutput, BASE_DIR);
        zOutput.flush();
        zOutput.close();
    }

    private static void compress(
            File srcFile, ZipOutputStream zOutput, String basePath) throws Exception {
        if (srcFile.isDirectory()) {
            compressDir(srcFile, zOutput, basePath);
        } else {
            compressFile(srcFile, zOutput, basePath);
        }
    }

    public static void compress(String srcPath, String destPath) throws Exception {
        File srcFile = new File(srcPath);
        compress(srcFile, destPath);
    }

    private static void compressDir(
            File dir, ZipOutputStream zOutput, String basePath) throws Exception {
        File[] files = dir.listFiles();
        if (files.length <= 0) {
            ZipEntry entry = new ZipEntry(basePath + dir.getName() + PATH);
            zOutput.putNextEntry(entry);
            zOutput.closeEntry();
        }
        for (File file : files) {
            compress(file, zOutput, basePath + dir.getName() + PATH);
        }
    }

    private static void compressFile(File file, ZipOutputStream zOutput, String dir) throws Exception {
        ZipEntry entry = new ZipEntry(dir + file.getName());
        zOutput.putNextEntry(entry);
        BufferedInputStream bInput = new BufferedInputStream(new FileInputStream(file));
        int count;
        byte[] data = new byte[BUFFER_SIZE];
        while ((count = bInput.read(data, 0, BUFFER_SIZE)) != -1) {
            zOutput.write(data, 0, count);
        }
        bInput.close();
        zOutput.closeEntry();
    }

    public static byte[] compressBytes(byte[] source) throws Exception {
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
        ZipOutputStream zOutput = new ZipOutputStream(bOutput);
        ZipEntry entry = new ZipEntry("zip");
        entry.setSize(source.length);
        zOutput.putNextEntry(entry);
        zOutput.write(source);
        zOutput.closeEntry();
        zOutput.close();
        return bOutput.toByteArray();
    }

    public static byte[] decompressBytes(byte[] source) throws Exception {
        ByteArrayInputStream bInput = new ByteArrayInputStream(source);
        ZipInputStream zInput = new ZipInputStream(bInput);
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
        while (zInput.getNextEntry() != null) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = zInput.read(buffer, 0, buffer.length)) != -1) {
                bOutput.write(buffer, 0, count);
            }
        }
        bOutput.flush();
        byte[] result = bOutput.toByteArray();

        zInput.close();
        bInput.close();
        bOutput.close();

        return result;
    }
}
