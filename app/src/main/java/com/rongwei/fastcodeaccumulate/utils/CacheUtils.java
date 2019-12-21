package com.rongwei.fastcodeaccumulate.utils;

import com.google.gson.Gson;
import com.rongwei.fastcodeaccumulate.AndroidApplication;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by maoqi on 2018/7/3.
 */
public class CacheUtils {
    public static ACache mAcache;
    public static Gson mGson;
    public static final int TIME_MINUTE = 60;
    public static final int TIME_HOUR = TIME_MINUTE * 60;
    public static final int TIME_DAY = TIME_HOUR * 24;

    static {
        mAcache = ACache.get(new File(StorageUtils.getCacheDirPath(AndroidApplication.getInstance().getApplicationContext())));
        mGson = new Gson();
    }

    public static void putString(String key, String value) {
        mAcache.put(key, value);
    }

    public static void putString(String key, String value, int saveTime) {
        mAcache.put(key, value, saveTime);
    }

    public static String getString(String key) {
        return mAcache.getAsString(key);
    }

    public static void putBean(String key, Object bean) {
        if (bean == null)
            return;
        String json = mGson.toJson(bean);
        putString(key, json);
    }

    public static void putBean(String key, Object bean, int saveTime) {
        if (bean == null)
            return;
        String json = mGson.toJson(bean);
        putString(key, json, saveTime);
    }

    public static Object getBean(String key, Class clazz) {
        String json = getString(key);
        if (json == null || json.isEmpty()) {
            return null;
        }
        return mGson.fromJson(json, clazz);
    }

    public static Object getBeanAndDel(String key, Class clazz) {
        String json = getString(key);
        if (json == null || json.isEmpty()) {
            return null;
        }
        mAcache.remove(key);
        return mGson.fromJson(json, clazz);
    }

    public static void putBeanList(String key, Object object) {
        putBean(key, object);
    }

    public static void putBeanList(String key, Object object, int saveTime) {
        putBean(key, object, saveTime);
    }

    public static List getBeanList(String key, Type type) {
        String json = getString(key);
        if (json == null || json.isEmpty()) {
            return null;
        }
        return mGson.fromJson(json, type);
    }

    public static boolean isExist(String key) {
        String str = mAcache.getAsString(key);
        return str != null && !str.isEmpty();
    }

    public static boolean remove(String key) {
        return mAcache.remove(key);
    }

}
