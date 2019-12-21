package com.rongwei.fastcodeaccumulate.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;

/**
 * Created by maoqi on 2018/6/14.
 */
public class PermissionUtils {
    public static final String READ_CALENDAR = Manifest.permission.READ_CALENDAR;
    public static final String WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;

    public static final String CAMERA = Manifest.permission.CAMERA;

    public static final String READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    public static final String WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    public static final String GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;

    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    public static final String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;

    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;
    public static final String WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;
    public static final String ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL;
    public static final String USE_SIP = Manifest.permission.USE_SIP;
    public static final String PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;

    public static final String BODY_SENSORS = Manifest.permission.BODY_SENSORS;

    public static final String SEND_SMS = Manifest.permission.SEND_SMS;
    public static final String RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;
    public static final String READ_SMS = Manifest.permission.READ_SMS;
    public static final String RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;
    public static final String RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;

    public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public static final class Group {
        public static final String[] CALENDAR = new String[]{
                PermissionUtils.READ_CALENDAR,
                PermissionUtils.WRITE_CALENDAR};

        public static final String[] CAMERA = new String[]{PermissionUtils.CAMERA};

        public static final String[] CONTACTS = new String[]{
                PermissionUtils.READ_CONTACTS,
                PermissionUtils.WRITE_CONTACTS,
                PermissionUtils.GET_ACCOUNTS};

        public static final String[] LOCATION = new String[]{
                PermissionUtils.ACCESS_FINE_LOCATION,
                PermissionUtils.ACCESS_COARSE_LOCATION};

        public static final String[] MICROPHONE = new String[]{PermissionUtils.RECORD_AUDIO};

        public static final String[] PHONE = new String[]{
                PermissionUtils.READ_PHONE_STATE,
                PermissionUtils.CALL_PHONE,
                PermissionUtils.READ_CALL_LOG,
                PermissionUtils.WRITE_CALL_LOG,
                PermissionUtils.ADD_VOICEMAIL,
                PermissionUtils.USE_SIP,
                PermissionUtils.PROCESS_OUTGOING_CALLS};

        public static final String[] SENSORS = new String[]{PermissionUtils.BODY_SENSORS};

        public static final String[] SMS = new String[]{
                PermissionUtils.SEND_SMS,
                PermissionUtils.RECEIVE_SMS,
                PermissionUtils.READ_SMS,
                PermissionUtils.RECEIVE_WAP_PUSH,
                PermissionUtils.RECEIVE_MMS};

        public static final String[] STORAGE = new String[]{
                PermissionUtils.READ_EXTERNAL_STORAGE,
                PermissionUtils.WRITE_EXTERNAL_STORAGE};
    }

    public static Observable<Permission> takePermissionArray(FragmentActivity activity, String... permissions) {
        return new RxPermissions(activity)
                .requestEach(permissions);
    }

    public static Observable<Boolean> takePermission(FragmentActivity activity, String... permissions) {
        return new RxPermissions(activity)
                .request(permissions);
    }

    public static Observable<Boolean> takeStoragePermission(FragmentActivity activity) {
        return new RxPermissions(activity)
                .request(Group.STORAGE);
    }

    public static Observable<Boolean> takeStoragePermission(Fragment fragment) {
        return new RxPermissions(fragment)
                .request(Group.STORAGE);
    }

    public static boolean isStoragePermissionEnabel(Context context) {
        return isPermissionEnabel(context, READ_EXTERNAL_STORAGE);
    }

    public static boolean isPermissionEnabel(Context context, String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

}
