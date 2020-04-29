package com.rongwei.fastcodeaccumulate.module.me.experience

import com.rongwei.fastcodeaccumulate.data.bean.BaseResultWrapper
import com.rongwei.fastcodeaccumulate.data.bean.ExperienceBean
import com.rongwei.fastcodeaccumulate.data.bean.LeadDebotBean
import com.rongwei.fastcodeaccumulate.data.bean.StockNoteBean
import com.rongwei.fastcodeaccumulate.data.source.Repository
import com.rongwei.fastcodeaccumulate.rxjava.observer.SimpleWrapperObserver
import com.rongwei.fastcodeaccumulate.utils.NullUtils
import java.util.ArrayList


class ExperiencePresenter(view: ExperienceContract.View, mRepository: Repository) : ExperienceContract.Presenter {

    private val mExperienceView: ExperienceContract.View
    private val mRepository: Repository

    init {
        this.mExperienceView = NullUtils.checkNotNull<ExperienceContract.View>(view)
        this.mRepository = NullUtils.checkNotNull(mRepository)
    }



    override fun getExperienceInfo(uid: Int){
        mRepository.getExperienceInfo(uid).compose<BaseResultWrapper<ExperienceBean>>(mExperienceView.initNetLifecycler())
                .subscribe(object : SimpleWrapperObserver<ExperienceBean>(mExperienceView) {
            override fun onSuccess(data: ExperienceBean?) {
                if (data != null) {
                    data.data?.let { mExperienceView.getExperienceInfoSucess(it as ArrayList<ExperienceBean.DataBean>) }
                };
            }
        });
    }



    override fun setExperienceInfo(uid: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}
