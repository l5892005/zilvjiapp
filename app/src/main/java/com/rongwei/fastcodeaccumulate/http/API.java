package com.rongwei.fastcodeaccumulate.http;


import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.data.bean.CardBean;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserCardsToDayBean;
import com.rongwei.fastcodeaccumulate.data.bean.VersionBean;

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
        Observable<BaseResultWrapper<CardBean>> getCardData(@Query("userId") String userId);

        @GET("v1/get_cards")
        Observable<BaseResultWrapper<UserCardsToDayBean>> getCardDataToDay(@Query("userId") String userId);

        @GET("v1/set_cards_data")
        Observable<BaseResultWrapper<String>> setCardTodayData(@Query("userId")String userId, @Query("order") int order, @Query("isCard") int isCard);

        @GET("v1/get_note_catalog")
        Observable<BaseResultWrapper<NoteCatalogBean>> getNoteCatalog(@Query("userId")int uid);

        @GET("v1/get_note_list")
        Observable<BaseResultWrapper<PersionNoteListBean>> getNoteListCatalog(@Query("userId")int userId, @Query("nid")int nid);

        @GET("v1/login")
        Observable<BaseResultWrapper<UserBean>> setLogin(@Query("userId")String userId,@Query("pwd") String pwd);

        @GET("v1/register_user")
        Observable<BaseResultWrapper<UserBean>> setRegister(@Query("userId")String account, @Query("pwd") String pwd);

        @GET("v1/set_memo")
        Observable<BaseResultWrapper<MemoBean>> setMemoInfo(@Query("userId")int userId, @Query("info") String info);

        @GET("v1/set_note_catalog")
        Observable<BaseResultWrapper<NoteCatalogBean>> setNoteType(@Query("userId")int uid,@Query("info") String name,@Query("isPri") int isPri);

        @GET("v1/set_note_list")
        Observable<BaseResultWrapper<PersionNoteListBean>> getSetListCatalog(@Query("userId")int uid, @Query("nid")int nid,@Query("title") String title, @Query("content") String content);

        @GET("v1/add_cards_type")
        Observable<BaseResultWrapper<CardBean>> AddCardType(@Query("userId")String userId, @Query("name")String name,@Query("imageName") String imageName, @Query("colorBg")String colorBg);

        @GET("v1/update_cards_type")
        Observable<BaseResultWrapper<CardBean>> setReModeCard(@Query("userId")String userId,@Query("cid")String cid, @Query("name")String name,@Query("imageName") String imageName, @Query("colorBg")String colorBg);

        @GET("v1/get_version")
        Observable<BaseResultWrapper<VersionBean>> getVersionCode();
    }

    interface UserApi {
      /*  @Headers("atype:login")
        @GET("/v1/wx/auth")
        Observable<BaseResultWrapper<WxLoginBean>> loginWx(@Query("code") String code);

        @GET("/v1/wx/bind")
        Observable<BaseResultWrapper<WxBindBean>> bindWX(@Query("code") String code);*/


    }


}
