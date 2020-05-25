package com.rongwei.fastcodeaccumulate.module.main.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.BuildConfig;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.annotation.ContentView;
import com.rongwei.fastcodeaccumulate.data.bean.VersionBean;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerMainHomeComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.MainHomeModule;
import com.rongwei.fastcodeaccumulate.listener.AntiShakeClickListener;
import com.rongwei.fastcodeaccumulate.listener.IDownloadProgressListener;
import com.rongwei.fastcodeaccumulate.module.InstallActivity;
import com.rongwei.fastcodeaccumulate.module.base.BaseActivity;
import com.rongwei.fastcodeaccumulate.module.base.BaseFragment;
import com.rongwei.fastcodeaccumulate.module.dialog.VersionUpdateDialogFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.MyHabit.MyHabitFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.home.HomeFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.tool.MyToolFragment;
import com.rongwei.fastcodeaccumulate.module.fragment.main.very.VeryDayFragment;
import com.rongwei.fastcodeaccumulate.utils.EncryptUtils;
import com.rongwei.fastcodeaccumulate.utils.FileUtils;
import com.rongwei.fastcodeaccumulate.utils.HttpUtils;
import com.rongwei.fastcodeaccumulate.utils.StorageUtils;
import com.rongwei.fastcodeaccumulate.utils.ToastUtil;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
@ContentView(R.layout.activity_main_home)
public class MainHomeActivity extends BaseActivity implements MainHomeContract.View {

    @Inject
    MainHomeContract.Presenter mPresenter;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    private ProgressDialog progressDialog;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainHomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initInjector() {
        DaggerMainHomeComponent
                .builder()
                .applicationComponent(AndroidApplication.getInstance().getAppComponent())
                .mainHomeModule(new MainHomeModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void initData() {

    }

    private BaseFragment[] mFragments;
    public static final int TAB_ONE = 0;
    public static final int TAB_TWO = 1;
    public static final int TAB_THREE = 2;
//    public static final int TAB_FOUR = 3;

    @Override
    protected void initView() {
        mFragments = new BaseFragment[3];
        HomeFragment homeFragment = HomeFragment.newInstance();
        MyHabitFragment ployFragment = MyHabitFragment.newInstance();
//        VeryDayFragment mVeryDayFragment = VeryDayFragment.newInstance();
        MyToolFragment myToolFragment = MyToolFragment.newInstance();
        mFragments[0] = homeFragment;
        mFragments[1] = ployFragment;
//        mFragments[2] =mVeryDayFragment;
        mFragments[2] = myToolFragment;
        bottomBar.setDefaultTabPosition(TAB_ONE);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_index:
                        selectedFragment(TAB_ONE);
                        break;
                    case R.id.tab_thow:
                        selectedFragment(TAB_TWO);
                        break;
                    case R.id.tab_three:
                        selectedFragment(TAB_THREE);
                        break;
//                    case R.id.tab_four:
//                        selectedFragment(TAB_FOUR);
//                        break;
                }
            }
        });
    }

    public int mLastPosition = -1;

    private void selectedFragment(int index) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment fragment = mFragments[index];
        if (mLastPosition != -1) {
            ft.hide(mFragments[mLastPosition]);
        }
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            ft.add(R.id.fl_content, fragment).show(fragment);
        }
        ft.commitAllowingStateLoss();

        mLastPosition = index;
    }

    @Override
    protected void loadData() {
        mPresenter.getVersionCode();
    }

    @Override
    public void getVersionCodeSucess(VersionBean bean) {
        if (BuildConfig.VERSION_CODE < bean.getVersioncode()) {
            if (!TextUtils.isEmpty(bean.getUrl())) {
                final VersionUpdateDialogFragment fragment = VersionUpdateDialogFragment.newInstance(bean.getIsmust() == 1 ? VersionUpdateDialogFragment.TYPE_FORCE : VersionUpdateDialogFragment.TYPE_SIMPLE, bean.getUrl(), bean.getSubdesc(), bean.getVersioncode() + "");
                fragment.setSubmitClickListener(new AntiShakeClickListener() {
                    @Override
                    public void click(View v) {
                        try {
                            removeFragment(fragment);
                            //URL url = new URL(bean.getUrl());
                            showDownloadProgressDialog(bean.getUrl());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                addFragment(fragment);
            }

        }
    }
    static {
        System.loadLibrary("native-lib");

    }

    @SuppressLint("StaticFieldLeak")
    public  void update(View view){
        AsyncTask<Void, Void, File> pach = new AsyncTask<Void, Void, File>() {
            @Override
            protected File doInBackground(Void... voids) {
                File pach = new File(Environment.getExternalStorageDirectory(), "pach");
//                File oldApk = new File(Environment.getExternalStorageDirectory(), "old.apk");
                String newApk = createNewApk().getAbsolutePath();
                File oldApk = new File(getApplicationInfo().sourceDir);
                try {
                    readfile(getApplicationInfo().sourceDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bsdiff(oldApk.getAbsolutePath(), pach.getAbsolutePath(), newApk);
                return new File(newApk);
            }

            @Override
            protected void onPostExecute(File file) {
                super.onPostExecute(file);
                installApk(file);
            }
        };
        pach.execute();
    }
    /**
     * 读取某个文件夹下的所有文件
     */
    public boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());

            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());

                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }


    private File createNewApk() {
        File newApk =new File(Environment.getExternalStorageDirectory(),"new1.apk");
        if (!newApk.exists()){
            try {
                newApk.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return newApk;
    }

    /**
     * 增量更新
     */
    public native void bsdiff(String oldPath,String pach,String newFile);

    private void showDownloadProgressDialog(final String fileUrl) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        if (!fileUrl.endsWith(".apk")) {
            toastFailed("更新包有问题!");
            progressDialog.dismiss();
            return;
        }
        final File destFile = FileUtils.createFile(StorageUtils.getFileDirPath(mContext), EncryptUtils.encryptMD5ToString(fileUrl) + ".apk");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> emitter) throws Exception {
                HttpUtils.download(fileUrl, destFile, new IDownloadProgressListener() {
                    @Override
                    public void onProgress(int progress) {
                        emitter.onNext(progress);

                    }

                    @Override
                    public void onError(Exception e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onSuccess() {
                        emitter.onComplete();
                    }
                });

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer progress) {
                progressDialog.setProgress(progress);
            }

            @Override
            public void onError(Throwable e) {
                ToastUtil.toastBackgoround("网络错误");
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onComplete() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                installApk(destFile);
            }
        });

    }

    private void installApk(final File file) {
        InstallActivity.start(this, file);
    }
}
