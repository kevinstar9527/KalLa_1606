package a05.qianfeng.edu.cn.testevent;

import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/6/17.
 */
public class EventUtil {
    public static String parseEvent(MotionEvent event){

        //获取事件的动作
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
           default:
                return "ACTION_CANCEL";

        }
    }
}
