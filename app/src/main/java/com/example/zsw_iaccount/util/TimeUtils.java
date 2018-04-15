package com.example.zsw_iaccount.util;

import java.util.Calendar;

/**
 * Created by 赵舒文 on 2018-3-26.
 */

public class TimeUtils {
    //获取系统时间 年
    public static String getYear(){
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        // 年
        int year = calendar.get(Calendar.YEAR);
        String mYear=String.valueOf(year);
        return mYear;
    }

    //获取系统时间 月
    public static String getMonth(){
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        // 年
        int month = calendar.get(Calendar.MONTH);
        String mMonth=String.valueOf(month);
        return mMonth;
    }
    //获取系统时间 年
    public static String getDay(){
        Calendar calendar = Calendar.getInstance();
        //获取系统的日期
        // 年
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String mDay=String.valueOf(day);
        return mDay;
    }

}
