package com.rongwei.fastcodeaccumulate.http.intercept;

import com.rongwei.fastcodeaccumulate.cons.CacheKey;
import com.rongwei.fastcodeaccumulate.cons.SpKey;
import com.rongwei.fastcodeaccumulate.utils.CacheUtils;
import com.rongwei.fastcodeaccumulate.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by maoqi on 2018/7/12.
 */
public class HeaderParamIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final String deviceid = SpUtils.getInstance().getString(SpKey.DEVICE, null);
        final String token = CacheUtils.getString(CacheKey.TOKEN);
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        String url = original.url().toString();

        //deviceId
     /*   if (deviceid != null) {
            builder.header("device", deviceid);
        }

        //token
        if (token != null) {
            builder.header("Authorization", "Bearer " + token);
        }
        builder.header("User-Agent", "tdzyy");*/
        return chain.proceed(builder.build());
    }
}
