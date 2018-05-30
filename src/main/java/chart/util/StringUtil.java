package chart.util;

import java.text.DecimalFormat;

/**
 * Created by 京美电子 on 2018/5/21.
 */
public class StringUtil {

    public static String keepDecimal(Object f,String pattern){
        DecimalFormat df = new DecimalFormat(pattern == null ? "#0.00" : pattern);
        return df.format(f);
    }


}
