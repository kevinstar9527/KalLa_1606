package a05.qianfeng.edu.cn.testevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/*
*
* 分发事件
* */

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("event","MainActivity"+"----diaptchTouchEvent:"+EventUtil.parseEvent(ev));

        return super.dispatchTouchEvent(ev);
    }

    /*
    *   执行事件
    *
    * */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("event","MainActivity"+"----onTouchEvent"+EventUtil.parseEvent(event));

        return super.onTouchEvent(event);
    }
}
