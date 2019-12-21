package com.rongwei.fastcodeaccumulate.listener;

/**
 * Created by maoqi on 2018/7/16.
 */
public interface IDownloadProgressListener {
    void onProgress(int progress);
    void onError(Exception e);
    void onSuccess();
}
