package com.rongwei.fastcodeaccumulate.http;


import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface API {

    interface BaseApi {
        @GET("/v1/get-all-book")
        Observable<BaseResultWrapper<FastCodeBean>> getAllData();

        @GET("v1/set-book")
        Observable<BaseResultWrapper<String>> inserFastCode(@Query("name") String name,@Query("type") int type,@Query("typedesc") int typedesc,@Query("content") String content,@Query("importd") int importd);

        @GET("v1/get_memo")
        Observable<BaseResultWrapper<MemoBean>> getMemoData(@Query("userId") String userId);

        @GET("v1/get_cards_status")
        Observable<BaseResultWrapper<UserCardsBean>> getCardData(@Query("userId") String userId);

        @GET("v1/get_cards")
        Observable<BaseResultWrapper<UserCardsToDayBean>> getCardDataToDay(@Query("userId") String userId);

        @GET("v1/set_cards_data")
        Observable<BaseResultWrapper<String>> setCardTodayData(@Query("userId")String userId, @Query("order") int order, @Query("isCard") int isCard);
    }

    interface UserApi {
      /*  @Headers("atype:login")
        @GET("/v1/wx/auth")
        Observable<BaseResultWrapper<WxLoginBean>> loginWx(@Query("code") String code);

        @GET("/v1/wx/bind")
        Observable<BaseResultWrapper<WxBindBean>> bindWX(@Query("code") String code);*/


    }


}
