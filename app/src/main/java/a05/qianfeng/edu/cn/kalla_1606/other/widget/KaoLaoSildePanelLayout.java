package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/6/17.
 */
public class KaoLaoSildePanelLayout extends SlidingPaneLayout {

    private IIntercept intercept;
    public KaoLaoSildePanelLayout(Context context) {
        super(context);
    }

    public KaoLaoSildePanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (intercept==null){
            //
            return super.onInterceptTouchEvent(ev);

        }
        //如果需要拦截的话执行原来的方法
        if(intercept.needIntercepet()){
            //return true？
            Log.e("msg", "KaoLaSilde  -- onInterceptTouchEvent: ture "+ev.getAction());
            return super.onInterceptTouchEvent(ev);
        }else{
            //不需要拦截的话
            Log.e("msg", "KaoLaSilde  -- onInterceptTouchEvent: false"+ev.getAction());
            return false;//不拦截
        }
    }

    public void setIntercept(IIntercept iIntercept){
        this.intercept = iIntercept;
    }

    public interface IIntercept
    {
        boolean needIntercepet();
    }
}
