package com.rongwei.fastcodeaccumulate.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.rongwei.fastcodeaccumulate.R;
import com.rongwei.fastcodeaccumulate.glide.GlideRoundTransform;

/**
 * Created by maoqi on 2017/12/18.
 */

public class GlideUtils {

    public static void display(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }


    public static void displayRound(Context context, String url, ImageView imageView, int roundDp) {
        Glide.with(context)
                .load(url)
                .transform(new CenterCrop(context), new GlideRoundTransform(context, roundDp))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public static void displayLow(Context context, String url, ImageView imageView, int w, int h) {
        Glide.with(context)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .override(w * 2, h * 2)
                .into(imageView);
    }
    public static void displayImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
