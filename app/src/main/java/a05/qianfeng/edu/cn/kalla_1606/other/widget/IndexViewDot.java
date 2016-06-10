package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * ViewPager的索引
 * Created by Administrator on 2016/6/6.
 */
public class IndexViewDot extends View{

    /*
    * 几个圆？
    * */
    private int count = 4;
    /*
    * 圆点的半径
    * */
    private float radius = 10 ;
    /*默认的颜色*/
    private int defaultColor = Color.GRAY;
    /*选中的颜色*/
    private int selecterColor = Color.RED;
    /*圆点的间距*/
    private float circlePadding = 20;
    /*画笔*/
    private Paint paint = new Paint();

    /*第一个圆点
    * 最左侧的的坐标
    * */
    private float fromX,fromY;
    /*
    *
    *    当前选中的索引
    * */
    private int currIndex;

    public IndexViewDot(Context context) {
        super(context);
    }

    public IndexViewDot(Context context, AttributeSet attrs) {
        super(context, attrs);
        //解析自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.index_view);
        count = typedArray.getInt(R.styleable.index_view_count,4);
        radius = typedArray.getFloat(R.styleable.index_view_radius,40);
        defaultColor = typedArray.getColor(R.styleable.index_view_defaultColor,Color.GRAY);
        selecterColor =typedArray.getColor(R.styleable.index_view_selecterColor,Color.RED);
        circlePadding = typedArray.getFloat(R.styleable.index_view_circlePadding,20);
        typedArray.recycle();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        fromX = (getWidth() - radius * 2 * count - circlePadding * (count - 1)) / 2;
        fromY = getHeight() / 2;
        //更好看点
        paint.setAntiAlias(true);
        //  paint.setColor(defaultColor);
        for (int i = 0; i < count; i++) {
            if (i == currIndex) {
                paint.setColor(selecterColor);
                paint.setStyle(Paint.Style.FILL);
            } else {
                paint.setColor(defaultColor);
                paint.setStyle(Paint.Style.STROKE);
            }
            canvas.drawCircle(fromX + radius + (radius * 2 + circlePadding) * i, fromY, radius, paint);

        }
    }


    public void setCurrIndex(int index){
        //改变当前选中值
        currIndex = index;
        //重新绘制一次
        invalidate();

    }
}
