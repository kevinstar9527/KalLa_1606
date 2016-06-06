package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 *
 * 全屏播放的VideoView
 *
 * 自定义控件一共三种方式，
 *
 * 1.集成原生控件，重写某些方式或者添加一些功能方法
 *
 * 2.把多个控件组合在一起，形成一个控件，这样的控件一般是继承ViewGroup或者是ViewGroup的子类
 *
 * 3.集成View，重写onDraw方法把想要显示的内容画出来
 *
 * Created by Administrator on 2016/6/6.
 */
public class FullScreenVideoView extends VideoView {

    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
       //重新设置一下宽高
        setMeasuredDimension(width,height);
    }
}
