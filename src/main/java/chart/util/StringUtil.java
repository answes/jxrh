package chart.util;

import bean.Goods;

import java.text.DecimalFormat;

/**
 * Created by 京美电子 on 2018/5/21.
 */
public class StringUtil {

    public static String keepDecimal(Object f,String pattern){
        DecimalFormat df = new DecimalFormat(pattern == null ? "#0.00" : pattern);
        return df.format(f);
    }

    public static boolean isEmpty(String s){

        return  s == null || s.length() == 0;
    }

    public static double calcChangeRadio(Goods goods){
        if((goods.getNewPrice() - goods.getYestedayPrice()) == 0){
            return 0;
        }
        return  (goods.getNewPrice() - goods.getYestedayPrice()) / goods.getYestedayPrice() * 100;
    }
    public static double calcChangeValue(Goods goods){
        return  goods.getNewPrice() - goods.getYestedayPrice();
    }
    public static double calcChange(Goods goods){
        if(goods.getMaxPrice() - goods.getMixPrice() == 0){
            return 0;
        }
        return (goods.getMaxPrice() - goods.getMixPrice()) / goods.getYestedayPrice() * 100;
    }

}
