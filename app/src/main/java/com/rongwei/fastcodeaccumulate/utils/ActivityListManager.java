package com.rongwei.fastcodeaccumulate.utils;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maoqi on 2018/12/28.
 */
public class ActivityListManager {
    private List<Activity> mActivityList;

    public ActivityListManager() {
        mActivityList = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        mActivityList.add(activity);
        Log.d("test", "ActivityListManager-addActivity:"+mActivityList.size());
    }

    public void removeActivity(Activity activity) {
        if (mActivityList.contains(activity)) {
            mActivityList.remove(activity);
            Log.d("test", "ActivityListManager-addActivity:"+mActivityList.size());
        }
    }

    public void removeAll() {
        for (Activity activity : mActivityList) {
            activity.finish();
        }
        mActivityList.clear();
    }





    public int getListSize() {
        return mActivityList == null ? 0 : mActivityList.size();
    }
}
