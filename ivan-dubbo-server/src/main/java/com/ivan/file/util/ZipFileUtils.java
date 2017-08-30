package com.ivan.file.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author lichangtong
 * @create 2017-08-30 21:15
 **/
public class ZipFileUtils {
    private ZipFileUtils() {

    }

    /**
     * 传入文件路径，如果文件不存在或者文件夹不存在返回 null.
     *
     * @param path
     * @return targetFile
     */
    public static File zip(String path) {
        File sourceFile = new File(path);
        String zipFileName;
        String prefix = ".zip";
        if (!sourceFile.exists()) {
            return null;
        }
        if (sourceFile.isDirectory()) {
            zipFileName = sourceFile.getName() + prefix;
        } else {
            String fileName = sourceFile.getName();
            zipFileName = fileName.substring(0, fileName.lastIndexOf(".")) + prefix;
        }
        System.out.println(zipFileName);
        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        File targetFile = new File(sourceFile.getParent(), zipFileName);

        if (targetFile.exists()) {
            //压缩文件存在删除
            targetFile.delete();
        }
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
            //添加压缩文件
            addEntry("/", sourceFile, zipOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(zipOutputStream, fileOutputStream);
        }
        return targetFile;
    }

    //添加文件
    private static void addEntry(String basePath, File sourceFile, ZipOutputStream zipOutputStream) throws IOException {

        //需要添加的文件
        String filePath = basePath + sourceFile.getName();

        //如果目标文件是个目录 创建目录层级，递归添加文件
        if (sourceFile.isDirectory()) {
            for (File file : sourceFile.listFiles()) {
                //递归调用
                addEntry(filePath, file, zipOutputStream);
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            byte[] buffer = new byte[1024 * 10];
            fis = new FileInputStream(sourceFile);
            bis = new BufferedInputStream(fis, buffer.length);
            int read = 0;

            //添加一个压缩文件
            zipOutputStream.putNextEntry(new ZipEntry(filePath));
            //读取文件内容 ，写入到压缩文件中
            while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                zipOutputStream.write(buffer, 0, read);
            }
            //关闭文件
            zipOutputStream.closeEntry();
            IOUtil.closeQuietly(bis, fis);
        }


    }

    public static void main(String[] args) {

        ZipFileUtils.zip("g:/test");
    }
}
