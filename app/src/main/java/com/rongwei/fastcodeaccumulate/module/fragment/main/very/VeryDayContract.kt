package com.rongwei.fastcodeaccumulate.module.fragment.main.very

import com.rongwei.fastcodeaccumulate.data.bean.VideoBean
import com.rongwei.fastcodeaccumulate.module.base.IBasePresenter
import com.rongwei.fastcodeaccumulate.module.base.IBaseView

interface VeryDayContract {

    interface View : IBaseView{
        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: VideoBean)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(homeBean:VideoBean )
    }

    interface Presenter : IBasePresenter{

        fun requestHomeData(num:Int)
        /**
         * 加载更多数据
         */
        fun loadMoreData()

    }

}
