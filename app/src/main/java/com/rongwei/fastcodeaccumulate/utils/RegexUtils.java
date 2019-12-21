package com.rongwei.fastcodeaccumulate.utils;

/**
 * Created by maoqi on 2018/8/4.
 */
public class RegexUtils {

    public static boolean isPhoneNumLegal(String phoneNum) {
        String telRegex = "[1]\\d{10}";

        return phoneNum.matches(telRegex);
    }

    public static boolean isAlipayAccountLegal(String phoneNum) {

        return isPhoneNumLegal(phoneNum) || isEmailLegal(phoneNum);
    }

    public static boolean isEmailLegal(String email) {
        String emailRegex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(emailRegex);
    }
}
