package com.rongwei.fastcodeaccumulate.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;


import com.rongwei.fastcodeaccumulate.BuildConfig;

import java.util.List;
import java.util.Locale;

/**
 * Created by maoqi on 2018/6/15.
 */
public class SystemUtil {

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    public static String getAppVersionName(Context context) {
//        try {
//            PackageManager manager = context.getPackageManager();
//            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
//            String version = info.versionName;
//            Log.d("Version", "版本信息：" + version);
//            return version;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
        return BuildConfig.VERSION_NAME;
    }


    public static boolean isWeixinAvilible(Context context) {

        return isSoftwareAvilible(context, "com.tencent.mm");
    }

    public static boolean isQQAvilible(Context context) {

        return isSoftwareAvilible(context, "com.tencent.mobileqq");
    }

    public static boolean isWeiboAvilible(Context context) {

        return isSoftwareAvilible(context, "com.sina.weibo");
    }


    public static boolean isSoftwareAvilible(Context context, String packgeName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(packgeName);
            List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);

            if (list.size() > 0) {
                return true;
            }
        } catch (Exception e) {

        }

        return false;

//        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
//        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
//        if (pinfo != null) {
//            for (int i = 0; i < pinfo.size(); i++) {
//                String pn = pinfo.get(i).packageName;
//                if (pn.equals(packgeName)) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
    }

    public static boolean isProessRunning(Context context, String proessName) {

        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : lists) {
            if (info.processName.equals(proessName)) {
                //Log.i("Service2进程", ""+info.processName);
                isRunning = true;
            }
        }

        return isRunning;
    }
}
