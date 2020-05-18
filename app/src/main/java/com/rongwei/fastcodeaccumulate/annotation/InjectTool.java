package com.rongwei.fastcodeaccumulate.annotation;

import android.util.Log;

import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class InjectTool {
    private static final String TAG = InjectTool.class.getSimpleName();

    public static void inject(Object object) {
        injectSetContentView(object);

    }

    private static void injectSetContentView(Object object) {
        Class aMainActivity = object.getClass();
        ContentView annotation = (ContentView) aMainActivity.getAnnotation(ContentView.class);
        if (annotation == null) {
            Log.d(TAG, "setContentView is null");
            return;
        }
        int value = annotation.value();
        try {
            Method setContentView = aMainActivity.getMethod("setContentView", int.class);
            setContentView.invoke(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
