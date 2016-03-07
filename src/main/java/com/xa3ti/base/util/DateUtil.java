package com.xa3ti.base.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    public static Date stringToDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null ;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
    public static long isNew(Date date1,Date date2){
        return (date1.getTime()-date2.getTime())/(24*60*60*1000);
    }

    public static long isNew(Date date){
        return (date.getTime()-new Date().getTime());
    }
    public static void main(String[] args) {
        Date date = stringToDate("2014-4-10 20:49:00");
//        Date now = new Date();
//        long days = (now.getTime()-date.getTime());
        long days = isNew(date);
        System.out.println(days);
    }
}
