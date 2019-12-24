package com.rongwei.fastcodeaccumulate.data.source;


import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper;
import com.rongwei.fastcodeaccumulate.data.bean.FastCodeBean;
import com.rongwei.fastcodeaccumulate.data.bean.MemoBean;
import com.rongwei.fastcodeaccumulate.data.bean.WxLoginBean;
import com.rongwei.fastcodeaccumulate.data.param.InserFastCodeBean;
import com.rongwei.fastcodeaccumulate.data.param.LoginParam;

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
 /*   Observable<BaseResultWrapper<UploadBean>> upload(File file);

    Observable<BaseResultWrapper> sendVerificationCode(VerificationCodeParam param);*/



}
