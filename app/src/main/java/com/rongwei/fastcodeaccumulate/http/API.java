package com.rongwei.fastcodeaccumulate.http;


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
import com.rongwei.fastcodeaccumulate.data.bean.VideoBean;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import io.reactivex.Observable;
import kotlin.Unit;
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

        @GET("v1/get_stock_note")
        Observable<BaseResultWrapper<StockNoteBean>> getStockMoney(@Query("userId") int uid);

        @GET("v1/set_stock_list")
        Observable<BaseResultWrapper<String>> putStockMoney(@Query("uid")int uid,@Query("take_out") int take_out,@Query("money") int money,@Query("put_in") int put_in,@Query("remark_money") String remark_money,@Query("stock_code") String stock_code);

        @GET("v1/put_lend_debt")
        Observable<BaseResultWrapper<String>> putLendRebt(@Query("uid")int uid, @Query("money")int money, @Query("mstate")int mstate,@Query("mremark") String mremark, @Query("mname")String mname, @Query("nowstatu")int nowstatu);

        @GET("v1/get_lend")
        Observable<BaseResultWrapper<LeadDebotBean>> getLendRebt(@Query("uid")int uid);

        @GET("v1/set_lend_status")
        Observable<BaseResultWrapper<String>> getLendRebtStauts(@Query("mid")int mid);


        @GET("v1/get_experience_info")
        Observable<BaseResultWrapper<ExperienceBean>> getExperienceInfo(@Query("userId")int uid);

        @Headers("domain:other")
        @GET("/api/v2/feed?")
        Observable<VideoBean> getRequestHomeData(@Query("num")int uid);


        @Headers("domain:other")
        @GET("/api/v2/feed?")
        Observable<VideoBean> loadMoreData(@Query("date")String date , @Query("num")String num);
    }

}
