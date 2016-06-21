package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.HttpUtils;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class TypeTopLayout extends View {

    private int width;
    private int height;
    private Bitmap first,second,third,four,five,six,seven;
    private Paint paint = new Paint();
    private List<Bitmap> bitmaps = null;
    private int size;
    private int lineWidth = 5;//图片的间距

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
        if (typeRects==null) {
            return;
        }
        for (int i = 0; i < typeRects.length; i++) {
            Bitmap bitmap = typeRects[i].bitmap;
            if (bitmap!=null) {
                canvas.drawBitmap(bitmap,typeRects[i].fromX,typeRects[i].fromY,paint);
            }
            
        }



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

    //下面这个方法说明不仅仅是网络操作，多数文件类的操作也需要开启异步线程

    public void setImageUrlList(final List<String> list){

        computeRects();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    String url = list.get(i);
                    final int index = i;
                    HttpUtils.downLoadEverything(url, FileUtil.dir_image, FileUtil.getFileNameByHashCode(url), new KaoLaTask.IDownlaodListener() {
                        @Override
                        public void upgradeProgress(float progress) {

                        }

                        @Override
                        public void onCompleted(File file) {
                           Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                           bitmap =  transform(bitmap, index);
                           if (typeRects.length==0){
                                return;
                            }
                           typeRects[index].bitmap = bitmap;
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    invalidate();
                                }
                            });

                        }

                        @Override
                        public void error(String msg) {
                        }

                        @Override
                        public void start() {

                        }
                    });

                }
            }
        }.start();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        typeRects = null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float scale = getContext().getResources().getDisplayMetrics().density;//得到像素无关密度及1dp等于多少像素
        //下面这句表示的宽度是屏幕宽度减去margin
        //Math.ceil返回的是与之最接近的整数
        int width = getResources().getDisplayMetrics().widthPixels-(int)(scale*40+0.5f);//等到控件的宽度包括中间的间隔
        //先得到底部的宽度(注意单位都是像素)
        //其中5是每个小间隔的宽度，同时size也是底部的边长
        size = (width-3*5)/4;
        int height = size *3+5;//设置控件的高度
        //设置控件的宽高
        setMeasuredDimension(width,height);
    }

    /*图片裁剪比例*/

    public Bitmap transform(Bitmap src,int i){

        Bitmap target = null;
        int swidth = src.getWidth();
        int sheight = src.getHeight();
        TypeRect rect = null;
        if (typeRects==null){
            return null;
        }
        if (typeRects[i]!=null) {
            rect = typeRects[i];
        }else{

            return null;
        }
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
            float sx, sy = 1.0f;//缩放比例
            Matrix materix = new Matrix();
            sx = 1.0f * rect.width / swidth;
            sy =  1.0f*rect.height/swidth;
            materix.setScale(sx,sy);
            return target = Bitmap.createBitmap(src,0,0,swidth,sheight,materix,true);
            case 1:
            case 2:

                //对于需要裁剪的图片一般先裁剪后设置缩放比例这个过程分两步来进行
                Bitmap bitmap = Bitmap.createBitmap(src,0,sheight/4,swidth,sheight/2);
                int tWidth = bitmap.getWidth();
                int tHeight = bitmap.getHeight();
                sx = 1.0f * rect.width / tWidth;
                sy =  1.0f*rect.height/tHeight;
                Matrix matrix = new Matrix();
                matrix.setScale(sx,sy);
                target = Bitmap.createBitmap(bitmap,0,0,tWidth,tHeight,matrix,true);
                break;
        }
        return target;

    }
    /**
     * 设置每个方块的大小和位置
     */
    private void computeRects()
    {

        /*设置方块的大小和位置*/
        LogUtil.w("width = " + getWidth() + " , size = " + size);

        typeRects = new TypeRect[]{
                new TypeRect(size * 0 + lineWidth * 0, size * 0 + lineWidth * 0,
                        size * 2 + lineWidth * 1, size * 2 + lineWidth * 1),

                new TypeRect(size * 2 + lineWidth * 2, size * 0 + lineWidth * 0,
                        size * 2 + lineWidth * 1, size * 1 + lineWidth * 0),

                new TypeRect(size * 2 + lineWidth * 2,
                        size * 1 + lineWidth * 1, size * 2 + lineWidth * 1,
                        size * 1 + lineWidth * 0),

                new TypeRect(size * 0 + lineWidth * 0,
                        size * 2 + lineWidth * 2, size * 1 + lineWidth * 0,
                        size * 1 + lineWidth * 0),

                new TypeRect(size * 1 + lineWidth * 1,
                        size * 2 + lineWidth * 2, size * 1 + lineWidth * 0,
                        size * 1 + lineWidth * 0),

                new TypeRect(size * 2 + lineWidth * 2,
                        size * 2 + lineWidth * 2, size * 1 + lineWidth * 0,
                        size * 1 + lineWidth * 0),

                new TypeRect(size * 3 + lineWidth * 3,
                        size * 2 + lineWidth * 2, size * 1 + lineWidth * 0,
                        size * 1 + lineWidth * 0)};
    }

    /**
     * 每一个方块对象包含：要显示的图片，显示位置，宽高
     */
    /**
     * 用来存放七个方块的数据
     */
    private TypeRect[] typeRects;
    private class TypeRect
    {
        Bitmap bitmap;

        int fromX;

        int fromY;

        int width;

        int height;

        Rect rect;

        public TypeRect(int fromX, int fromY, int width, int height)
        {
            this.fromX = fromX;
            this.fromY = fromY;
            this.width = width;
            this.height = height;

            rect = new Rect(fromX, fromY, width, height);
            LogUtil.w(toString());
        }

        /**
         * 判断该方块是否包含某一点
         *
         * @param x
         * @param y
         * @return
         */
        boolean include(float x, float y)
        {
            return x > fromX && x < fromX + width && y > fromY && y < fromY + height;
        }

        @Override
        public String toString()
        {
            return "TypeRect{" + "fromX=" + fromX + ", fromY=" + fromY
                    + ", width=" + width + ", height=" + height + '}';
        }
    }

}
