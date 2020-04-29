package com.rongwei.fastcodeaccumulate.data.source.remote;


import com.rongwei.fastcodeaccumulate.AndroidApplication;
import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.data.bean.CardBean;
import com.rongwei.fastcodeaccumulate.data.bean.ExperienceBean;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.bean.VersionBean;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.data.source.DataSource;
import com.rongwei.fastcodeaccumulate.http.API;
import com.rongwei.fastcodeaccumulate.http.config.URLConfig;
import com.rongwei.fastcodeaccumulate.http.converter.CustomGsonConverterFactory;
import com.rongwei.fastcodeaccumulate.http.intercept.HeaderParamIntercept;
import com.rongwei.fastcodeaccumulate.utils.StringUtils;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.function.DoubleUnaryOperator;

import io.reactivex.Observable;
import kotlin.Unit;
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

    @Override
    public Observable<BaseResultWrapper<CardBean>> getCardData(String uid) {
        return retrofit.create(API.BaseApi.class).getCardData(uid);
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

    @Override
    public Observable<BaseResultWrapper<UserBean>> setLogin(String account, String pwd) {
        return retrofit.create(API.BaseApi.class).setLogin(account,pwd);
    }

    public Observable<BaseResultWrapper<UserBean>> setRegister(String account, String pwd) {
        return retrofit.create(API.BaseApi.class).setRegister(account,pwd);
    }

    @Override
    public Observable<BaseResultWrapper<MemoBean>> setMemoInfo(int userId, String info) {
        return retrofit.create(API.BaseApi.class).setMemoInfo(userId,info);
    }

    @Override
    public Observable<BaseResultWrapper<NoteCatalogBean>> setNoteType(int uid, String name,int ispri) {
        return retrofit.create(API.BaseApi.class).setNoteType(uid,name,ispri);
    }

    @Override
    public Observable<BaseResultWrapper<PersionNoteListBean>> getSetListCatalog(int uid, int nid, String title, String info) {
        return retrofit.create(API.BaseApi.class).getSetListCatalog(uid,nid,title,info);
    }

    @Override
    public Observable<BaseResultWrapper<CardBean>> AddCardType(String userId, String name, String imageName, String colorBg) {
        return retrofit.create(API.BaseApi.class).AddCardType(userId,name,imageName,colorBg);
    }

    @Override
    public Observable<BaseResultWrapper<CardBean>> setReModeCard(String userId, String cid, String name, String imageName, String colorBg) {
        return retrofit.create(API.BaseApi.class).setReModeCard(userId,cid,name,imageName,colorBg);
    }

    @Override
    public Observable<BaseResultWrapper<VersionBean>> getVersionCode() {
        return retrofit.create(API.BaseApi.class).getVersionCode();
    }

    @Override
    public Observable<BaseResultWrapper<StockNoteBean>> getStockMoney(int uid) {
        return retrofit.create(API.BaseApi.class).getStockMoney(uid);
    }

    @Override
    public Observable<BaseResultWrapper<String>> putStockMoney(int uid, int take_out, int money, int put_in, String remark_money, String stock_code) {
        return retrofit.create(API.BaseApi.class).putStockMoney(uid,take_out,money,put_in,remark_money,stock_code);
    }

    @Override
    public Observable<BaseResultWrapper<String>> putLendRebt(int uid, int money,  int mstate, String mremark, String mname, int nowstatu) {
        return retrofit.create(API.BaseApi.class).putLendRebt(uid,money,mstate,mremark,mname,nowstatu);
    }

    @Override
    public Observable<BaseResultWrapper<LeadDebotBean>> getLendRebt(int uid) {
        return retrofit.create(API.BaseApi.class).getLendRebt(uid);
    }

    @Override
    public Observable<BaseResultWrapper<String>> getLendRebtStauts(int mid) {
        return retrofit.create(API.BaseApi.class).getLendRebtStauts(mid);
    }

    @Override
    public Observable<BaseResultWrapper<ExperienceBean>> getExperienceInfo(@NotNull int uid) {
        return retrofit.create(API.BaseApi.class).getExperienceInfo(uid);
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
