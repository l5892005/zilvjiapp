package com.rongwei.fastcodeaccumulate;

import android.app.Application;
import android.content.Context;

import com.rongwei.fastcodeaccumulate.cons.CacheKey;
import com.rongwei.fastcodeaccumulate.cons.Channel;
import com.rongwei.fastcodeaccumulate.cons.SpKey;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.source.Repository;
import com.rongwei.fastcodeaccumulate.injector.components.ApplicationComponent;
import com.rongwei.fastcodeaccumulate.injector.components.DaggerApplicationComponent;
import com.rongwei.fastcodeaccumulate.injector.modules.ApplicationModule;
import com.rongwei.fastcodeaccumulate.rxjava.function.SimpleWrapperFunction;
import com.rongwei.fastcodeaccumulate.utils.ActivityListManager;
import com.rongwei.fastcodeaccumulate.utils.CacheUtils;
import com.rongwei.fastcodeaccumulate.utils.SpUtils;
import com.rongwei.fastcodeaccumulate.utils.toast.ToastUtils;
import com.umeng.commonsdk.UMConfigure;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observable;


/**
 * Aplication
 */

public class AndroidApplication extends Application {
    private static AndroidApplication sApplication;
    private ApplicationComponent mAppComponent;
    public final boolean isDebug = BuildConfig.isDebug;
    private ActivityListManager mActivityManager;
    //private PublicParamBean mPublicParamBean;
    private UserBean mUser;
    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        sApplication = this;
        mActivityManager = new ActivityListManager();
        //初始化toast
        ToastUtils.init(this);

        //初始化渠道
       // int channelId = Channel.getChannelId(this);

        initUMeng();
       // mPublicParamBean = new PublicParamBean();
        handleSSLHandshake();
    }

    public void setUser(UserBean user) {
        mUser = user;
        CacheUtils.putBean(CacheKey.USER, mUser);
    }

    public UserBean getUser() {
        if (mUser == null) {
            mUser = (UserBean) CacheUtils.getBean(CacheKey.USER, UserBean.class);
        }
        return mUser;
    }

    public void loginout() {
        mUser = null;
        CacheUtils.remove(CacheKey.USER);
    }

    public boolean isLogin() {
        if (mUser == null) {
            mUser = (UserBean) CacheUtils.getBean(CacheKey.USER, UserBean.class);
        }
        return mUser != null;
    }
    private final static String UM="5e05a5b40cafb2b48f00081d";
    private void initUMeng() {
        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(BuildConfig.isDebug);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //多dex
//        MultiDex.install(base);
    }

    /**
     * 是否debug
     *
     * @return
     */
    public static boolean isDebug() {
        return BuildConfig.isDebug;
    }

    private void initInjector() {
        mAppComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    public static AndroidApplication getInstance() {
        return sApplication;
    }

    public ActivityListManager getmActivityManager() {
        return mActivityManager;
    }

    public Repository getRepository() {
        return mAppComponent.getRepository();
    }
    /**
     * 忽略https的证书校验
     * 避免Glide加载https图片报错：
     * javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
     */
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
    public String getDeviceId() {
        return SpUtils.getInstance().getString(SpKey.DEVICE);
    }

}
