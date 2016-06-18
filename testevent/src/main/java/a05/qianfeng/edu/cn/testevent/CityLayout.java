package a05.qianfeng.edu.cn.testevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/6/17.
 */
public class CityLayout extends LinearLayout {
    public CityLayout(Context context) {
        super(context);
    }

    public CityLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
/*
*
* 分发事件
* */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("event","CityLayout-------"+":diaptchTouchEvent:"+EventUtil.parseEvent(ev));

        return super.dispatchTouchEvent(ev);
    }
    /*
    * 拦截事件
    * */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("event","CityLayout-------"+"onInterceptTouchEvent"+EventUtil.parseEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }
/*
*   执行事件
*
* */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("event","CityLayout-------"+"onTouchEvent"+EventUtil.parseEvent(event));

        return super.onTouchEvent(event);
    }
}
