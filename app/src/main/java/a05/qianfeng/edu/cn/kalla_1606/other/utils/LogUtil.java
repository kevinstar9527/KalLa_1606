package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/6/7.
 */
public  class LogUtil {

    public static  boolean isDebug = true;
    public static final String TAG = LogUtil.class.getSimpleName();
    /*
    * 错误级别的日志
    *
    * */

    public static void e(String msg){
        if(isDebug){
            Log.e(TAG,msg);
        }
    }

    /*
    * 警告级别的日志
    * */
    public static void w(String msg){
        if(isDebug){
            Log.w(TAG,msg);
        }
    }
    /*
    * 普通级别的日志
    *
    * */
    public static void v(String msg){
        if(isDebug){
            Log.v(TAG,msg);
        }
    }

}
