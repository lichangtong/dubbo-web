package com.ivan.file.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * 压缩网络文件
 *
 * @author lichangtong
 * @create 2017-08-30 22:16
 **/
public class NetFileZipUtils {
    private NetFileZipUtils() {
    }

    public static File zip() {
        return null;
    }

    public static void zip(InputStream input, ZipOutputStream zipStream, String fileName) throws IOException {
        BufferedInputStream bis;
        if (input != null) {
            byte[] buffer = new byte[1024 * 10];
            bis = new BufferedInputStream(input, buffer.length);
            int read = 0;
            //添加一个压缩文件
            zipStream.putNextEntry(new ZipEntry(fileName));
            //读取文件内容 ，写入到压缩文件中
            while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                zipStream.write(buffer, 0, read);
            }
            //关闭文件
            zipStream.closeEntry();
            IOUtil.closeQuietly(bis);
        }
    }

    /**
     * 获取输入流
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static InputStream getInputStream(String path) throws IOException {

        URL url = new URL(path);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        // 设置网络连接超时时间
        httpURLConnection.setConnectTimeout(3000);
        // 设置应用程序要从网络连接读取数据
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        if (200 == responseCode) {
            return httpURLConnection.getInputStream();

        }
        return null;
    }

    public static File save(String path, String filePath) throws IOException {
        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        zip(getInputStream(path), zipOutputStream, "123.jpg");
        IOUtil.closeQuietly(zipOutputStream, fileOutputStream);
        return file;
    }

    public static void main(String[] args) {
        try {
            save("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504114141449&di=d51a1167d32bd68152749c5bd11be340&imgtype=0&src=http%3A%2F%2Fhuo360.com%2Fuploads%2F2015%2F06%2F1433172341127041.jpg", "G:/test/test.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
