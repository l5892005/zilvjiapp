package com.rongwei.fastcodeaccumulate.http.converter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by maoqi on 2017/8/1.
 */

public final class CustomStringConverter<T> implements Converter<ResponseBody, T> {

    CustomStringConverter() {

    }

    @Override
    public T convert(ResponseBody value) throws IOException {
//        JsonReader jsonReader = gson.newJsonReader(value.charStream());
//        try {
//            return adapter.read(jsonReader);
//        } finally {
//            value.close();
//        }

        try {
            String body = value.string();


            return (T) body;
//
//            JSONObject json = new JSONObject(body);
//
//            int code = json.optInt("code");
//            String info = json.optString("info", "");
//
//            if (code == 10000) {
////                if (json.has("data")) {
////                    Object data = json.get("data");
////
////                    body = data.toString();
////
////                } else {
////                    return (T) info;
////                }
//                return adapter.fromJson(body);
//            } else {
//                String result = "{\"code\":"+code+",\"info\":\""+info+"\"}";
//                return adapter.fromJson(result);
////                throw new RuntimeException(info);
//            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}
