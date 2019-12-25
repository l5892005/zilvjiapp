package com.rongwei.fastcodeaccumulate.utils;

import android.text.format.Time;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maoqi on 2018/6/28.
 */
public class DateUtils {
    public static final long NO_TIME = -1;

    /**
     * 是否在同一天
     *
     * @param time1 时间(毫秒)
     * @param time2 时间(毫秒)
     * @return
     */
    public static boolean isSameDay(long time1, long time2) {
        Time time = new Time();
        time.set(time1);

        int timeYear = time.year;
        int timeMonth = time.month;
        int timeMonthDay = time.monthDay;

        time.set(time2);

        return (timeYear == time.year)
                && (timeMonth == time.month)
                && (timeMonthDay == time.monthDay);
    }

    /**
     * 是否在同一月
     *
     * @param time1 时间(毫秒)
     * @param time2 时间(毫秒)
     * @return
     */
    public static boolean isSameMonth(long time1, long time2) {
        Time time = new Time();
        time.set(time1);

        int timeYear = time.year;
        int timeMonth = time.month;

        time.set(time2);

        return (timeYear == time.year)
                && (timeMonth == time.month);
    }

    /**
     * 是否同一年
     *
     * @param time1 时间(毫秒)
     * @param time2 时间(毫秒)
     * @return
     */
    public static boolean isSameYear(long time1, long time2) {
        Time time = new Time();
        time.set(time1);

        int timeYear = time.year;

        time.set(time2);

        return (timeYear == time.year);
    }

    /**
     * 是否是昨天
     *
     * @param when 时间(毫秒)
     * @return
     */
    private static boolean isYesterday(long when) {
        Time time = new Time();
        time.set(when);

        int thenYear = time.year;
        int thenMonth = time.month;
        int thenMonthDay = time.monthDay;

        time.set(getCurrentTimeMs() - (1000 * 3600 * 24));

        return (thenYear == time.year)
                && (thenMonth == time.month)
                && (thenMonthDay == time.monthDay);
    }

    //根据秒数转化为时分秒   00:00:00
    public static String getTime(int second) {
        if (second < 10) {
            return "00:0" + second;
        }
        if (second < 60) {
            return "00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute + ":0" + second;
                }
                return "0" + minute + ":" + second;
            }
            if (second < 10) {
                return "" + minute + ":0" + second;
            }
            return minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + ":0" + minute + ":0" + second;
                }
                return "0" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "0" + hour + minute + ":0" + second;
            }
            return "0" + hour + minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":0" + minute + ":0" + second;
            }
            return hour + ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + minute + ":0" + second;
        }
        return hour + minute + ":" + second;
    }

    /**
     * 聊天时间显示规则
     * <p>
     * 涉及页面：在线问诊、导诊台、意见反馈、系统消息、消息详情、物流消息、奖励消息
     *
     * @param time 时间戳(毫秒)
     * @return
     */
    public static String formatChatTime(long time) {
        Long currentTime = getCurrentTimeMs();
        if (isSameDay(currentTime, time)) {
            //同一天
            return formatTime(time, "今天 HH:mm");
        } else if (isYesterday(time)) {
            //昨天
            return formatTime(time, "昨天 HH:mm");
        } else if (!isSameYear(currentTime, time)) {
            return formatTime(time, "yyyy-MM-dd HH:mm");
        } else {
            return formatTime(time, "MM-dd HH:mm");
        }
    }

    public static int formatCouponTime(long endTime) {
        Long currentTime = getCurrentTimeMs();
        long interval = endTime - currentTime;
        float txfloat = Float.parseFloat(txfloat(interval, (60 * 60 * 24 * 1000)));
        if (txfloat < 1) {
            return 1;
        } else if (txfloat < 2) {
            return 2;
        } else if (txfloat < 3) {
            return 3;
        } else if (txfloat < 4) {
            return 4;
        } else {
            return (int) txfloat;
        }
    }

    /**
     * TODO 除法运算，保留小数
     *
     * @param a 被除数
     * @param b 除数
     * @return 商
     * @author 袁忠明
     * @date 2018-4-17下午2:24:48
     */
    public static String txfloat(long a, long b) {
        // TODO 自动生成的方法存根

        DecimalFormat df = new DecimalFormat("0.00");//设置保留位数

        return df.format((float) a / b);

    }

    /**
     * 格式化时间
     *
     * @param time 时间(毫秒)
     * @param rule "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String formatTime(long time, String rule) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(rule);
        return format.format(date);
    }

    /**
     * 订单时间显示规则
     * <p>
     * 涉及页面：订单详情、物流详情
     *
     * @param time
     * @return
     */
    public static String formatOrderTime(long time) {
        return formatTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static Long getCurrentTimeS() {
        return getCurrentTimeMs() / 1000;
    }

    public static Long getCurrentTimeMs() {
        return System.currentTimeMillis();
    }

    public static String formatVideoTime(int len_time) {
        if (len_time >= 60 * 60) {
            return formatTime(len_time * 1000, "HH:mm:ss");
        }
        return formatTime(len_time * 1000, "mm:ss");
    }

    public static String formatTime2Minute(float len_time) {
        String time = String.valueOf(len_time / 60f);
        return time.substring(0, time.indexOf(".") + 2);
    }

    public static long dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        return date.getTime() / 1000;
    }

    public static boolean isBelong() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date now = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse("06:00");
            endTime = df.parse("24:00");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return belongCalendar(now, beginTime, endTime);
    }


    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }
}
