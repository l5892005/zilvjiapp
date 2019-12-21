package com.rongwei.fastcodeaccumulate.utils;

import android.content.Context;
import android.os.Environment;


import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by maoqi on 2018/6/11.
 */

public class StorageUtils {
    private static String sCacheFatherDir = "/anlu/luod";

    /**
     * 缓存目录
     *
     * @param
     * @return
     */
    public static Observable<String> getRootDirPath(final BaseActivity baseActivity) {
        return PermissionUtils.takeStoragePermission(baseActivity)
                .map(new Function<Boolean, String>() {
                    @Override
                    public String apply(Boolean aBoolean) throws Exception {
                        String externalRootFile = getExternalRootFile();
                        if (aBoolean && externalRootFile != null) {
                            //授权成功
                            return externalRootFile + sCacheFatherDir;
                        } else {
                            //权限被拒绝
                            return baseActivity.getFilesDir().getPath() + sCacheFatherDir;
                        }
                    }
                });
    }

    public static Observable<String> getImageDirPath(final BaseActivity baseActivity) {
        return getRootDirPath(baseActivity)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "/image";
                    }
                });
    }

    public static Observable<String> getFileDirPath(final BaseActivity baseActivity) {
        return getRootDirPath(baseActivity)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + "/file";
                    }
                });
    }

    /**
     * 外部存储root目录
     *
     * @return
     */
    private static String getExternalRootFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            return externalStorageDirectory.getPath();
        }
        return null;
    }

    private static String getRootDirPath(Context context) {
        String externalRootFile = getExternalRootFile();
        if (PermissionUtils.isStoragePermissionEnabel(context) && externalRootFile != null) {
            return externalRootFile + sCacheFatherDir;
        } else {
            return context.getFilesDir().getPath() + sCacheFatherDir;
        }
    }

    public static String getCacheDirPath(Context context) {
        return context.getCacheDir().getPath() + sCacheFatherDir + "/cache";
    }

    /**
     * 图片存储，可以清空
     *
     * @param context
     * @return
     */
    public static String getImageDirPath(Context context) {
        return getRootDirPath(context) + "/image";
    }

    /**
     * 文件存储，不删除
     *
     * @param context
     * @return
     */
    public static String getFileDirPath(Context context) {
        return getRootDirPath(context) + "/file";
    }

    //————————————————————
    public static String getExternalCacheDirPath(Context context) {
        String path = "";
        if (context != null) {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                    && context.getExternalCacheDir() != null) {
                path = context.getExternalCacheDir().getPath();
            } else {
                path = context.getCacheDir().getPath();
            }
        } else {
            path = Environment.getExternalStoragePublicDirectory(context.getPackageName()).getPath() + "/cache";
        }
        return path;
    }

    public static File getCacheDir(Context context) {
        File file = FileUtils.createFileDir(getExternalCacheDirPath(context));
        return file;
    }

    public static String getImgCachePath(Context context) {
        return getExternalCacheDirPath(context) + "/img/";
    }

    public static String getExternalFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            return externalStorageDirectory.getPath() + "/kuaixun/mgkd";
        }
        return null;
    }
}
