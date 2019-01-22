package com.ldy.accountingapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ldy on 19/1/5.
 */

public class DateUtil {


    //01:05
    public static String getFormattedTime(long timeStamp){

        //yyyy-MM-dd  HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(timeStamp));
    }

    //2019-01-05
    public static String getFormattedDate(){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());

    }


}
