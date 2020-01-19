package com.rongwei.fastcodeaccumulate.data.source;


import android.icu.util.VersionInfo;

import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.data.bean.CardBean;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.NoteCatalogBean;
import com.rongwei.fastcodeaccumulate.data.bean.PersionNoteListBean;
import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean;
import com.rongwei.fastcodeaccumulate.data.bean.UserBean;
import com.rongwei.fastcodeaccumulate.data.bean.VersionBean;
import com.rongwei.fastcodeaccumulate.data.bean.WxLoginBean;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.data.param.LoginParam;
import com.rongwei.fastcodeaccumulate.utils.StringUtils;

import java.io.File;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;

import io.reactivex.Observable;

/**
 * M层接口
 */
public interface DataSource {
    Observable<BaseResultWrapper<FastCodeBean>> getAllData();

    Observable<BaseResultWrapper<String>> inserFastCode(InserFastCodeBean inserFastCodeBean);

    Observable<BaseResultWrapper<MemoBean>> getMemoData(String userId);

    Observable<BaseResultWrapper<NoteCatalogBean>> getNoteCatalog(int uid);

    Observable<BaseResultWrapper<CardBean>> getCardData(String uid);

    Observable<BaseResultWrapper<PersionNoteListBean>> getNoteListCatalog(int userId, int uid);

    Observable<BaseResultWrapper<UserBean>> setLogin(String account, String pwd);


    Observable<BaseResultWrapper<UserBean>> setRegister(String account, String pwd);

    Observable<BaseResultWrapper<MemoBean>> setMemoInfo(int userId, String info);

    Observable<BaseResultWrapper<NoteCatalogBean>> setNoteType(int uid, String name,int ispri);

    Observable<BaseResultWrapper<PersionNoteListBean>> getSetListCatalog(int uid, int nid, String title, String info);

    Observable<BaseResultWrapper<CardBean>> AddCardType(String userId, String name, String imageName, String colorBg);

    Observable<BaseResultWrapper<CardBean>> setReModeCard(String userId, String cid, String name, String imageName, String colorBg);

    Observable<BaseResultWrapper<VersionBean>> getVersionCode();

    Observable<BaseResultWrapper<StockNoteBean>> getStockMoney(int uid);

    Observable<BaseResultWrapper<String>> putStockMoney(int uid, int take_out, int money, int put_in, String remark_money, String stock_code);




 /*   Observable<BaseResultWrapper<UploadBean>> upload(File file);

    Observable<BaseResultWrapper> sendVerificationCode(VerificationCodeParam param);*/



}
