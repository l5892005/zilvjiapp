package com.rongwei.fastcodeaccumulate.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.rongwei.fastcodeaccumulate.utils.IntentUtils;
import com.rongwei.fastcodeaccumulate.utils.ToastUtil;

import java.io.File;

import io.reactivex.annotations.Nullable;

/**
 * Created by maoqi on 2019/1/4.
 */
public class InstallActivity extends Activity {
    private static final int INSTALL_REQUEST_CODE = 10086;
    private File mApkFile;

    public static void start(Context context, File file) {
        Intent starter = new Intent(context, InstallActivity.class);
        starter.putExtra("file", file);
        if (!(context instanceof Activity)) {
            starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(starter);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            mApkFile = (File) getIntent().getSerializableExtra("file");
            installApk();
        } else {
            finish();
        }
    }

    private void installApk() {
        if (mApkFile != null && mApkFile.exists()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!getPackageManager().canRequestPackageInstalls()) {
                    Uri packageURI = Uri.parse("package:" + getPackageName());
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                    startActivityForResult(intent, INSTALL_REQUEST_CODE);
                    ToastUtil.toastBackgoround("请打开'安装未知来源应用'的权限");
                    return;
                }
            }
            startActivity(IntentUtils.buildInstallApkIntent(this, mApkFile));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INSTALL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (mApkFile != null && mApkFile.exists()) {
                    installApk();
                }
            } else {
                finish();
            }
        }
    }
}
