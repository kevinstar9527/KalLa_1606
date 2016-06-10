package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 *
 * 纵向滚动的TextView
 * Created by Administrator on 2016/6/8.
 */
public class VerticalText extends View{

   // private List<String> list;
    //设置滚动的速度(及向上的偏移量)
    private int speed = 5 ;

    /*时间间隔，每滑动一个偏移量的时间间隔*/
    private int intervalTime  ;


    // 向上的时间（滚动事件，花一秒从下面滚动到上面）
    private int scrollTime = 1000;

    /*
     停顿的时间
    *
    */
    private int deLayTime = 3000;

    /*滑动停止后的字符的Y坐标*/
    private int fromY;
    /*记录text1当前高度*/
    private int currY1,currY2;

    /*文字对像
    * 用来记录正在滑动的两个字符
    * */

    private String text1,text2;

    /*矩形对象*/
    RectF circleRec = new RectF();



/*字符颜色*/
    private int textColor = Color.BLACK;

    /*控件高度*/
    private int height;

    private int textSize = 30;

    List<String> list;

    private Paint paint = new Paint();

    private Paint paintRec = new Paint();


    /*当前选中的是哪一个字符,text1*/
    private int currIndex = -1;
    /*是否停止滑动，用来防止退出界面的时候线程还在继续执行*/
    private boolean toggle = false;

    public VerticalText(Context context, List<String> list) {
        super(context);

        this.list = list;
        /*设置文字的画笔属性*/
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        /*设置圆角矩形的画笔和矩形属性*/
       // paintRectAttributeSet();
        start();
    }

    private void paintRectAttributeSet() {

        paintRec.setAntiAlias(true);//设置画笔为无锯齿
        paintRec.setColor(Color.RED);//设置颜色
        paintRec.setStrokeWidth((float)3.0);//设置线宽
        paintRec.setStyle(Paint.Style.STROKE);//空心效果

        /*设置矩形的位置*/
        circleRec.left = 2;
      //  circleRec.right=height;
        circleRec.top=2;
        //circleRec.bottom=height;
    }

    public VerticalText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(text1==null||text2==null){
            return;
        }

        /*画一个圆角矩形*/

       // LogUtil.e("圆角矩阵正在绘画......"+"top : "+ circleRec.top+"...left :"+circleRec.left+".....right:"+circleRec.right);
       // canvas.drawRoundRect(circleRec,10,10,paintRec);
       // LogUtil.e("正在画字符");

        /*画第一个字符*/
        canvas.drawText(text1,0,currY1,paint);
        /*画第二个字符*/
        canvas.drawText(text2,0,currY2,paint);

    }

    //获取文字的高度

    public int getTextHeigth(String text1) {

        Rect rect = new Rect();
        //获取文字的边界值
        paint.getTextBounds(text1,0,text1.length(),rect);
        //文字的高度等于（底-高）矩形的最上减去最下
        return rect.bottom-rect.top;
    }

   /*************************流程方法*************************************8*/
    /*启动按钮*/

   private void start(){

       //在构造方法里直接调用getHeight是0，所以必须要等测量结束后再获取
       //而 post 就是在测量结束后再调用的方法

       post(new Runnable() {
           @Override
           public void run() {

               /*获取控件高度*/
               height = getHeight();
               circleRec.right=height;
               circleRec.bottom=height;
               /*要偏移多少次 要移动多少次？*/
               int count = height / speed;

               //我要在一秒钟之内完成，计算每一次偏移的时间间隔（平均速度）

               intervalTime = scrollTime /count;
               prepareScroll();
           }
       });

   }
    /*准备滚动*/
    private void prepareScroll(){


        currIndex++;
        int index1 = currIndex % list.size();
        int index2 = (index1+1)%list.size();
        //在这里赋值
        text1= list.get(index1);
        text2 = list.get(index2);
        //首个文字的位置
        fromY =height-(height-getTextHeigth(text1))/2;
        //实时更新Y坐标
        currY1=fromY;
        currY2=currY1+height;
        scroll();
    }
    /*开始滚动*/
    private void scroll(){

        if(toggle){

            return;
        }

      //  LogUtil.w("scroll CurrY2"+ currY2);
      //  LogUtil.w("scroll CurrY1"+ currY1);
        //开始变更坐标
        currY1=  currY1-speed;
        currY2 = currY1+height;
        //重绘一次
        invalidate();

       // LogUtil.w("变更后的坐标scroll CurrY2"+ currY2);
       // LogUtil.w("变更后的坐标scroll CurrY1"+ currY1);
        //是暂停还是继续滚
        if (needPause()){
            pause();
        }else{
            goOnScroll();
        }

    }

    private boolean needPause() {

        return currY2<=fromY;
    }


    /*正在滚动*/
    private void goOnScroll(){

      //隔一个时间片再进行下一次滚动
       postDelayed(new Runnable() {
           @Override
           public void run() {

               scroll();

           }
       }, intervalTime);

    }
    /*停止滚动*/

    private void pause() {

//        currIndex++;
//        text1 = list.get(currIndex%list.size());
//        text2 = list.get((currIndex+1)%list.size());
        //停留三秒准备下一次滚动
        postDelayed(new Runnable() {
            @Override
            public void run() {
               // LogUtil.e("当前的Index.....:"+currIndex);
                prepareScroll();
            }
        },deLayTime);

    }


    public int getCurrY1() {
        return currY1;
    }

    /********************************************************/

   public  void setList(List<String > list)
    {
        this.list = list;
    }
    /*获取当前滚动的下标*/
    public int getCurrIndex(){

       // LogUtil.e("点击后的IndexCurrent....:"+currIndex);

        if(currIndex%list.size()==0){
            return 0;
        }
        return (currIndex+1)%list.size();
    }

    /*在控件脱离窗口时调用的方法*/

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        toggle = true;
    }
}
