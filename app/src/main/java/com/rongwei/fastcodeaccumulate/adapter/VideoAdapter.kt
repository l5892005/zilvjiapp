package com.rongwei.fastcodeaccumulate.adapter

import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.core.graphics.convertTo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.rongwei.fastcodeaccumulate.R
import com.rongwei.fastcodeaccumulate.data.bean.ExperienceBean
import com.rongwei.fastcodeaccumulate.data.bean.VideoBean
import com.rongwei.fastcodeaccumulate.utils.GlideUtils

class VideoAdapter (data:List<VideoBean.IssueListBean.ItemListBean>? ): BaseQuickAdapter<VideoBean.IssueListBean.ItemListBean, BaseViewHolder>(R.layout.video,data) {

    override fun convert(helper: BaseViewHolder?, item: VideoBean.IssueListBean.ItemListBean?) {

        // 加载封页图
        GlideUtils.display(mContext,item?.data?.cover?.detail,helper?.getView(R.id.iv_cover_feed))
        GlideUtils.display(mContext,item?.data?.author?.icon,helper?.getView(R.id.iv_avatar))
        helper?.setText(R.id.tv_title,item?.data?.author?.name);
        var tagText:String="";
        //遍历标签
        item?.data?.tags?.take(4)?.forEach {
            tagText += (it.name + "/")
        }
        helper?.setText(R.id.tv_tag,tagText);
        helper?.setText(R.id.tv_category, "#" + item?.data?.category)
        helper?.addOnClickListener(R.id.iv_cover_feed)
    }



}