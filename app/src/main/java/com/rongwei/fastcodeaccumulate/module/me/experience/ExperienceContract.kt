package com.rongwei.fastcodeaccumulate.module.me.experience

import com.rongwei.fastcodeaccumulate.data.bean.ExperienceBean
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter
import com.rongwei.fastcodeaccumulate.module.base.IBaseView
import java.util.ArrayList

interface ExperienceContract {


    interface View : IBaseView {
        fun getExperienceInfoSucess(beans: ArrayList<ExperienceBean.DataBean>);
    }




    interface Presenter : IBasePresenter{

        fun getExperienceInfo(uid:Int);

        fun setExperienceInfo(uid:Int);

    }



}
