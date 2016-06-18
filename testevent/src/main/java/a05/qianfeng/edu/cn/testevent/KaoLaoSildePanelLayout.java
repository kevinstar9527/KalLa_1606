package a05.qianfeng.edu.cn.testevent;

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
           return super.onInterceptTouchEvent(ev);
        }
        if(intercept.needIntercepet()){
            //或者return true?

            return super.onInterceptTouchEvent(ev);
        }else{
            return false;//不拦截
        }
    }

    public void setIntercept(IIntercept iIntercept){
        this.intercept = intercept;
    }

    public interface IIntercept
    {
        boolean needIntercepet();
    }
}
