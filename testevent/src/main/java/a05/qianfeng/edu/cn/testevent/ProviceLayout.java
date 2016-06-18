package a05.qianfeng.edu.cn.testevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ProviceLayout extends LinearLayout {
    public ProviceLayout(Context context) {
        super(context);
    }

    public ProviceLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
/*
*
* 分发事件
* */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("event","ProviceLayout------"+"diaptchTouchEvent:"+EventUtil.parseEvent(ev));
        //return true表示事件结束了，执行下一次事件
        //return false//表示拒绝接受事件
        return super.dispatchTouchEvent(ev);//继续分发
    }
    /*
    * 拦截事件
    * */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("event","ProviceLayout------"+"onInterceptTouchEvent"+EventUtil.parseEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }
/*
*   执行事件
*
* */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("event","ProviceLayout------"+"onTouchEvent"+EventUtil.parseEvent(event));

        return super.onTouchEvent(event);
    }
}
