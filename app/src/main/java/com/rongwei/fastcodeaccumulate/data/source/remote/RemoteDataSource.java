package com.rongwei.fastcodeaccumulate.data.source.remote;


import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.data.source.DataSource;
import com.rongwei.fastcodeaccumulate.http.API;
import com.rongwei.fastcodeaccumulate.http.config.URLConfig;
import com.rongwei.fastcodeaccumulate.http.converter.CustomGsonConverterFactory;
import com.rongwei.fastcodeaccumulate.http.intercept.HeaderParamIntercept;

import java.util.concurrent.TimeUnit;
import java.util.function.DoubleUnaryOperator;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * 远程数据
 */

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;
    private Retrofit retrofit;

    private RemoteDataSource() {
        if (retrofit == null) {
            //initOkHttpClient
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    //.addInterceptor(new HeaderParamIntercept());

            if (AndroidApplication.isDebug()) {
                builder.addInterceptor(loggingInterceptor)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS);
            } else {
                builder.readTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .connectTimeout(15, TimeUnit.SECONDS);
            }


            //initRetrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLConfig.BaseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .client(builder.build())
                    .build();
        }
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<BaseResultWrapper<FastCodeBean>> getAllData() {
        return retrofit.create(API.BaseApi.class).getAllData();
    }

    @Override
    public Observable<BaseResultWrapper<String>> inserFastCode(InserFastCodeBean inserFastCodeBean) {
        return  retrofit.create(API.BaseApi.class).inserFastCode(inserFastCodeBean.getName(),inserFastCodeBean.getType(),inserFastCodeBean.getTypedesc(),inserFastCodeBean.getContent(),inserFastCodeBean.getImportd());
    }

    @Override
    public Observable<BaseResultWrapper<MemoBean>> getMemoData(String userId) {
        return  retrofit.create(API.BaseApi.class).getMemoData(userId);
    }

    @Override
    public Observable<BaseResultWrapper<NoteCatalogBean>> getNoteCatalog(int uid) {
        return retrofit.create(API.BaseApi.class).getNoteCatalog(uid);
    }

    public Observable<BaseResultWrapper<UserCardsBean>> getCardData(String userId) {
        return retrofit.create(API.BaseApi.class).getCardData(userId);
    }

    public Observable<BaseResultWrapper<UserCardsToDayBean>> getCardDataToDay(String userId) {
        return retrofit.create(API.BaseApi.class).getCardDataToDay(userId);
    }

    public Observable<BaseResultWrapper<String>> setCardTodayData(String userId, int order, int isCard) {
        return retrofit.create(API.BaseApi.class).setCardTodayData(userId,order,isCard);
    }

    public Observable<BaseResultWrapper<PersionNoteListBean>>  getNoteListCatalog(int uid, int nid) {
        return retrofit.create(API.BaseApi.class).getNoteListCatalog(uid,nid);
    }

  /*  @Override
    public Observable<BaseResultWrapper<DeviceBean>> getDeviceId() {
        return retrofit.create(API.BaseApi.class).getDeviceId();
    }

    @Override
    public Observable<BaseResultWrapper<H5PagerBean>> getH5Pagers() {
        return retrofit.create(API.BaseApi.class).getH5Pagers();
    }

    @Override
    public Observable<BaseResultWrapper<UploadBean>> upload(File file) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        return retrofit.create(API.BaseApi.class).upload(body);
    }

    @Override
    public Observable<BaseResultWrapper> sendVerificationCode(VerificationCodeParam param) {
        return retrofit.create(API.BaseApi.class).sendVerificationCode(param);
    }
*/

}
