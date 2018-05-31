package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author : bigshark
 * @Descripstion :
 * @Date : Create in 17:09 2018/05/24
 * @Modificd :
 */
public class DateUtil {

    /**
     * 获取当时的时间
     * @return
     */
    public static String getNowTime(){
        Date date = new Date();
        String dd = "HH:mm:ss";
        SimpleDateFormat timeDF = new SimpleDateFormat(dd);
        return timeDF.format(date);
    }

    public static String date2String(Date date){
        String dd = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat timeDF = new SimpleDateFormat(dd);
        return timeDF.format(date);
    }

}
