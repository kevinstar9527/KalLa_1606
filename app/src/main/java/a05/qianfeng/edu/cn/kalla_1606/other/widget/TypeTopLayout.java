package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * Created by Administrator on 2016/6/12.
 */
public class TypeTopLayout extends View {

    private int width;
    private int height;
    private Bitmap first,second,third,four,five,six,seven;
    private Paint paint = new Paint();
    private List<Bitmap> bitmaps;
    public TypeTopLayout(Context context) {
        super(context);
     //   this.bitmaps = bitmaps;
    }

    public void setBitmaps(List<Bitmap>bitmaps){

       List<Bitmap> defaultBitmaps = new ArrayList<>();
        Resources resources = getResources();
        defaultBitmaps.add(BitmapFactory.decodeResource(resources,R.drawable.ic_auchor_ok));

        this.bitmaps = bitmaps==null?defaultBitmaps:bitmaps;
    }

    public TypeTopLayout(Context context, AttributeSet attrs) {super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*正在画图*/
        Log.e("TAG" , "onDraw: 正在画图");
        paint.setStrokeWidth(20);
        height = getHeight();
        width = getWidth();
        /* y + height must be <= bitmap.height()*/
        first = Bitmap.createBitmap(bitmaps.get(0),0,0,getWidth()/2,getHeight()*2/3);
        //first.setWidth(getWidth()/2);
        canvas.drawBitmap(first,0,0,paint);
        int i=first.getHeight();
        second =Bitmap.createBitmap(bitmaps.get(1),0,20,getWidth()/2,i/2);
        canvas.drawBitmap(second,getWidth()/2,0,paint);
        third = Bitmap.createBitmap(bitmaps.get(2),0,20,getWidth()/2,i/2);
        canvas.drawBitmap(third,getWidth()/2,first.getHeight()/2,paint);
       /*第四张图*/
        four = Bitmap.createBitmap(bitmaps.get(3));
        /*使用这个会自动缩放*/
        RectF rectF = new RectF(0,i,width/4,i+i/2);
        canvas.drawBitmap(four,null,rectF,paint);
//****************************************************


        five =  Bitmap.createBitmap(bitmaps.get(4));
        RectF rectF1 = new RectF(width/4,i,width/2,i+i/2);
        canvas.drawBitmap(five,null,rectF1,paint);
//*****************************************************
        six =  Bitmap.createBitmap(bitmaps.get(5));
        RectF rectF2 = new RectF(width/2,i,width*3/4,i+i/2);
        canvas.drawBitmap(six,null,rectF2,paint);
//****************************************************
        seven =  Bitmap.createBitmap(bitmaps.get(6));
        RectF rectF3 = new RectF(width*3/4,i,width,i+i/2);
        canvas.drawBitmap(seven,null,rectF3,paint);


    }

    /*如何确定点击到哪里*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*处理点击事件
        * 解决如何在同一个控件中的不同的区域响应点击事件
        * */
        event.getX();
        event.getY();
        return super.onTouchEvent(event);
    }
}
