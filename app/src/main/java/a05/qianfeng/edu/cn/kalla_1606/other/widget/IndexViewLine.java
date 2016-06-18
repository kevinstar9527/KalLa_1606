package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/10.
 */
public class IndexViewLine extends View {

    private int selectorColor;
    private int defaultColor;
    private float height;
    private int count;
    /*矩形对象*/
    private Rect rect;

    /*线的宽度*/

    private float width ;

    /*画笔*/
    Paint paint = new Paint();
    /*当前选中的索引*/
    private int currIndex;
    /*左上角和右上角的坐标*/
    private float formX,fromY,stopX,stopY;


    public IndexViewLine(Context context) {
        super(context);
        setColor();
        count=5;

    }

    public IndexViewLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*解析自定义属性*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.index_view_line);
        /*设置滑动小横线的个数，默认是5个*/
        count = typedArray.getInt(R.styleable.index_view_line_countLine, 5);
        /*设置每一条横线的高度*/
       // height = typedArray.getFloat(R.styleable.index_view_line_height, 1);
        /*设置横线的默认颜色及被选中时的颜色*/
        defaultColor = typedArray.getColor(R.styleable.index_view_line_defaultColorLine, Color.GRAY);

        selectorColor = typedArray.getColor(R.styleable.index_view_line_selectorColorLine, Color.RED);

    }

    private void getAutoWidth(){
        width=getWidth()/count;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*测试*/
        LogUtil.e("正在画线");
        Log.e("index:",""+currIndex+":"+count);
        //设置每一条线的宽度
        getAutoWidth();
        formX = 0;
        fromY = 0;
        stopX =width;
        stopY = 0;
        /*消除锯齿？*/
        paint.setAntiAlias(true);
     //   paint.setStrokeWidth(10);
        paint.setStrokeWidth(getHeight());
        for(int i=0;i<count;i++){

            if(i==currIndex){
                paint.setColor(selectorColor);
                /*填充满，不然会空心*/
                paint.setStyle(Paint.Style.FILL);

            }else{
                paint.setColor(defaultColor);
                /*设置为空心*/
                paint.setStyle(Paint.Style.STROKE);
            }

            canvas.drawLine(formX+(width*i),fromY,stopX+width*i,stopY,paint);
            Log.e("index:",""+currIndex+"坐标:"+formX+"坐标:"+fromY+"坐标:"+stopX+"坐标:"+stopY);
        }

    }


    public void setCount(int count){

        this.count = count;
    }

    public void setCurrIndex(int index){

        currIndex = index;

        invalidate();
    }

    public void setColor(){
        selectorColor = Color.RED;
        defaultColor = Color.GRAY;
    }

}
