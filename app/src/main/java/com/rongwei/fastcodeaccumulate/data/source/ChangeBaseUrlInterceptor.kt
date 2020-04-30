package com.rongwei.fastcodeaccumulate.data.source

import com.rongwei.fastcodeaccumulate.Cons
import com.rongwei.fastcodeaccumulate.http.config.URLConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

object  URLConstant{
    var BASE_URL = URLConfig.BaseUrl;
    const val BASE_LOGIN_OTHER_URL = Cons.URl_VIDEO;
    const val OTHER = "other"
    const val DOMAIN = "domain"
}

class ChangeBaseUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //获取request
        var request=chain.request()
        //从request中获取原有的HttpUrl实列 oldHttpUrl
        var oldHttpUrl=request.url()
        //获取request的创建者
        var builder=request.newBuilder()
        //从request中获取headers 通过给定的建的url_name
        var headerValues=request.headers(URLConstant.DOMAIN)

        if(headerValues!=null&&headerValues.size>0) {
            builder.removeHeader(URLConstant.DOMAIN)
            var headValue = headerValues[0]
            var newBaseUrl: HttpUrl?
            newBaseUrl = when (headValue) {
                URLConstant.OTHER -> {
                    HttpUrl.parse(URLConstant.BASE_LOGIN_OTHER_URL)
                }else -> {
                    HttpUrl.parse(URLConstant.BASE_URL)
                }
            }
            newBaseUrl?.run {
                var newHttpUrl =
                        oldHttpUrl.newBuilder().scheme(scheme()).host(host())
                                .port(port()).build()
                return chain.proceed(builder.url(newHttpUrl).build())
            }
        }
        return chain.proceed(request)
    }

}

