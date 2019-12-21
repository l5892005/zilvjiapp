package com.rongwei.fastcodeaccumulate.utils;

import java.text.DecimalFormat;

/**
 * author:ld
 * Date:2019/3/28
 * Description:
 */
public class StringUtils {
    public static String formatString(String str, int num ){
        if (num<1){
            return "";
        }
        Double d= Double.parseDouble(str);
        DecimalFormat df = new DecimalFormat("0.00");
        String format = df.format(d).replace(".00", "");
        if (format.length()>num){
            format=format.substring(0,num);
            if (format.endsWith(".")){
                format.substring(0,num);
            }
        }
        return format;
    }
}
