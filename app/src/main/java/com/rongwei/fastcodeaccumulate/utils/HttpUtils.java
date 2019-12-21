package com.rongwei.fastcodeaccumulate.utils;



import com.rongwei.fastcodeaccumulate.listener.IDownloadProgressListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by maoqi on 2018/7/16.
 */
public class HttpUtils {

    /**
     * 下载
     * @param downloadUrl  下载url
     * @param destFile      本地保存的目标文件
     * @param listener      下载监听，不需要可传null
     */
    public static void download(String downloadUrl, File destFile, IDownloadProgressListener listener) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(downloadUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.connect();
            int contentLength = connection.getContentLength();
            inputStream = connection.getInputStream();
            outputStream = new FileOutputStream(destFile);
            byte[] bytes = new byte[1024];
            long totalReaded = 0;
            int temp_Len;
            while ((temp_Len = inputStream.read(bytes)) != -1) {
                totalReaded += temp_Len;
                int progress = (int) (totalReaded * 100 / contentLength);
                if (listener != null) {
                    listener.onProgress(progress);
                }
                outputStream.write(bytes, 0, temp_Len);
            }
            if (listener != null) {
                listener.onSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onError(e);
            }
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
