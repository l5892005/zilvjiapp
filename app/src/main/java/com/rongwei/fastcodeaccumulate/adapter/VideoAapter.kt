package com.rongwei.fastcodeaccumulate.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.rongwei.fastcodeaccumulate.data.bean.VideoBean

class VideoAapter(layoutResId: Int, data: List<VideoBean>?) : BaseQuickAdapter<VideoBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: VideoBean) {

    }
}
