package com.edu.joyread.activity.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by v2377 on 2017/9/4.
 */

public class DateOperator {

    /**
     *计算两个日期的相差天数
     * @param date1 两个日期中较小的天数
     * @param date2 两个日期中较大的天数
     * @return 相差天数
     */
    public static int daysBetween(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date sDate = sdf.parse(date1);
        Date bDate = sdf.parse(date2);
        int days = (int)(bDate.getTime() - sDate.getTime()) / (24 * 3600 * 1000);
        return days;
    }


}
