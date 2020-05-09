package com.rongwei.fastcodeaccumulate.utils;

import android.content.Context;
import android.content.res.TypedArray;

import com.rongwei.fastcodeaccumulate.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取图片类
 */
public class ImgPngUtils {
    private Map<String,Integer> png=new HashMap<>();

    private List<String> pngNameList=new ArrayList<>();

    private List<Integer> pngResList=new ArrayList<>();

    private static ImgPngUtils instance;

    private ImgPngUtils() {
    }

    /**
     * 获取png的名字列表
     * @return
     */
    public List<String> getPngNameList() {
        return pngNameList;
    }
    /**
     * 获取png的名字资源列表
     * @return
     */
    public List<Integer> getPngResList() {
        return pngResList;
    }

    private ImgPngUtils(Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array.img_list);
        TypedArray array = context.getResources().obtainTypedArray(R.array.img_list_ids);
        for (int i = 0; i < stringArray.length; i++) {
            png.put(stringArray[i],array.getResourceId(i, 0));
            pngNameList.add(stringArray[i]);
            pngResList.add(array.getResourceId(i, 0));

        }
    }

    public static ImgPngUtils getInstance(Context context) {
        if (instance==null){
            synchronized (ImgPngUtils.class){
                if (instance==null){
                    instance=new ImgPngUtils(context);
                }
            }
        }
        return instance;
    }

    public int getPngName(String name) {
       if (png.size()==0){
           return 0;
       }
       if (name==null){
           return 0;
       }
       return png.get(name);
    }


}
