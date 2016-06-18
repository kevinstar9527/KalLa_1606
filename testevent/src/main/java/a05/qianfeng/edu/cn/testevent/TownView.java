package a05.qianfeng.edu.cn.testevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/6/17.
 */
public class TownView extends View {
    public TownView(Context context) {
        super(context);
    }

    public TownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /*
*
* 分发事件
* */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("event","View------ diaptchTouchEvent:"+EventUtil.parseEvent(ev));

        return super.dispatchTouchEvent(ev);
    }

    /*
    *   执行事件
    *
    * */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("event","View------  onTouchEvent"+EventUtil.parseEvent(event));

        return super.onTouchEvent(event);
    }
}
