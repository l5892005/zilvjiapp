package com.rongwei.fastcodeaccumulate.module.fragment.main.very

import com.rongwei.fastcodeaccumulate.Cons
import com.rongwei.fastcodeaccumulate.data.bean.ExperienceBean
import com.rongwei.fastcodeaccumulate.data.bean.VideoBean
import com.rongwei.fastcodeaccumulate.data.source.Repository
import com.rongwei.fastcodeaccumulate.module.fragment.main.very.VeryDayContract
import com.rongwei.fastcodeaccumulate.rxjava.observer.ProgressObserver
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleObserver
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver
import com.rongwei.fastcodeaccumulate.utils.LogUtils
import com.rongwei.fastcodeaccumulate.utils.NullUtils
import java.util.*


class VeryDayPresenter(view:VeryDayContract.View,mRepository:Repository) : VeryDayContract.Presenter {
    private val mVeryDayView: VeryDayContract.View
    private val mRepository: Repository

    init {
        this.mVeryDayView = NullUtils.checkNotNull(view)
        this.mRepository = NullUtils.checkNotNull(mRepository)
    }
    override fun requestHomeData(num: Int) {
        mRepository.getRequestHomeData(num)
                .compose<VideoBean>(mVeryDayView.initNetLifecycler())
                .subscribe(object : SimpleObserver<VideoBean>(){
                    override fun onNext(t: VideoBean) {
                        LogUtils.d(t.toString())
                        nextPageUrl = t?.nextPageUrl
                        mVeryDayView.setHomeData(t);
                  //  loadMoreData();
                    }
                });
    }
    private var nextPageUrl:String?=null     //加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add


    override fun loadMoreData() {
        nextPageUrl?.let {
            val split = it.split("=")
            mRepository.loadMoreData(split[1]?.split("&")[0],split[2])
                    .compose<VideoBean>(mVeryDayView.initNetLifecycler())
                    .subscribe(object : SimpleObserver<VideoBean>(){
                        override fun onNext(t1: VideoBean) {
                            LogUtils.d(t1.toString())
                            nextPageUrl = t1?.nextPageUrl
                            mVeryDayView.setMoreData(t1);
                            //  loadMoreData();
                        }
                    });
        }
    }

}
