package com.rongwei.fastcodeaccumulate.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rongwei.fastcodeaccumulate.Cons;
import com.rongwei.fastcodeaccumulate.utils.GsonUtils;
import com.rongwei.fastcodeaccumulate.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


//标题
//JPushInterface.EXTRA_NOTIFICATION_TITLE

//通知内容
//JPushInterface.EXTRA_ALERT

//通知栏的Notification ID
//JPushInterface.EXTRA_NOTIFICATION_ID

//接受到的数据
//JPushInterface.EXTRA_EXTRA

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
//public class JPushReceiver extends BroadcastReceiver {
public class JPushReceiver  {
   /* private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            LogUtils.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " +   printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                //拿到registrationId
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                LogUtils.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...
            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                //自定义消息,这里的不会显示到通知栏
                String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                LogUtils.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + message);

            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                //接受到推送下来的通知
                LogUtils.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                LogUtils.d(TAG, "[MyReceiver] 用户点击打开了通知");
                // TODO: 2019/3/21 这里处理打开逻辑
                processOpenAction(context, bundle);
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                LogUtils.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                LogUtils.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                LogUtils.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.d("JPush", "异常：" + e.getMessage());
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    LogUtils.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    LogUtils.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.get(key));
            }
        }
        return sb.toString();
    }

    *//**
     * 处理打开相应Activity逻辑
     *
     * @param context
     * @param bundle
     *//*
    private void processOpenAction(Context context, Bundle bundle) {
        String bundleString = bundle.getString(JPushInterface.EXTRA_EXTRA);
        JPushMessageBean jPushMessageBean = GsonUtils.fromJson(bundleString, JPushMessageBean.class);
        String data = jPushMessageBean.getData();
        JsonObject jsonObject;
        switch (jPushMessageBean.getType()) {
            case Cons.MESSAGE_SYS:
                jsonObject = (JsonObject) new JsonParser().parse(data);
                String url = jsonObject.get("url").getAsString();
                NewsDetailActivity.start(context, url, Cons.H5_TYPE_SYSTEM_MSG);
                break;
            case Cons.MESSAGE_LOGISTICS:
                jsonObject = (JsonObject) new JsonParser().parse(data);
                int id = jsonObject.get("id").getAsInt();
                LogisticsDetailsActivity.start(context, id);
                break;
            case Cons.MESSAGE_REWARD:
                MyCouponActivity.start(context);
                break;
        }

    }*/
}
