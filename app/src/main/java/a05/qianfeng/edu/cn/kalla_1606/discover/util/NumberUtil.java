package a05.qianfeng.edu.cn.kalla_1606.discover.util;

import android.util.Log;

/**
 * Created by Administrator on 2016/6/19.
 */
public class NumberUtil {

    public  static String transform(long number){

        if (number>999&&number<9999){
            double target = number/1000.0;
            Log.e("target"," = "+target);
            //并保留一位小数
            target = format(target);
            return String.valueOf(target)+"K+";
        }

        return null;

    }

    private static double format(double number) {

        long b = (long)Math.round(number*10);//小数点后两位前移，并四舍五入
        double c = ((double) b/10.0);//还原小数点后两位
//        if((c*10%5)!=0){
//            int d = (int) Math.round(c);//小数点前移并四舍五入
//            c = ((double)d);//还原小数点
//            return c;
//        }
        return c;

    }
}
