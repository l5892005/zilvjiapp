package com.rongwei.fastcodeaccumulate.cons;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by maoqi on 2018/8/3.
 */
public class Channel {

    //渠道id 默认alpha

    public static final int CHANNEL_YINGYONGBAO_ID = 2;
    public static final int CHANNEL_SHOUJI360_ID = 3;
    public static final int CHANNEL_BAIDU_ID = 4;
    public static final int CHANNEL_OPPO_ID = 5;
    public static final int CHANNEL_HUAWEI_ID = 6;
    public static final int CHANNEL_XIAOMI_ID = 7;
    public static final int CHANNEL_VIVO_ID = 8;
    public static final int CHANNEL_ALPHA_ID = 9;
    public static final int CHANNEL_WIFIMASTER_ID = 10;
    public static final int CHANNEL_KUAISHOU_ID = 11;
    public static final int CHANNEL_MEIZU_ID = 12;
    public static final int CHANNEL_YINGYONGBAO_TG_ID = 13;
    public static final int CHANNEL_QUANMAMA_ID = 14;
    public static final int CHANNEL_XIAOZHUO_ID = 15;

    public static String getChannelStr(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String channel = appInfo.metaData.getString("UMENG_CHANNEL");
            Log.d("Channel", "当前渠道：" + channel);
            return channel;
        } catch (PackageManager.NameNotFoundException ignored) {
            Log.d("Channel", "获取渠道失败");
        }
        return "";
    }

    public static int getChannelId(Context context) {
        int channelId = CHANNEL_ALPHA_ID;
        String channelStr = getChannelStr(context);
        try {
            if (!channelStr.isEmpty()) {
                Properties props = new Properties();
                InputStream open = context.getAssets().open("channel.properties");
                props.load(open);
                channelId = Integer.parseInt(props.getProperty(channelStr));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Channel", "当前渠道id：" + channelId);
        return channelId;
    }
}
