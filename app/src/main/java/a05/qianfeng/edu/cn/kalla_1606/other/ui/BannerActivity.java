package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * Created by Administrator on 2016/6/6.
 */
public class BannerActivity extends AppCompatActivity {
    private ImageView imageView;
    private  Timer timer;
    private TimerTask task ;//一个线程
    private int time=3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        task = new TimerTask() {
            @Override
            public void run() {
                time--;
                //在主线程中运行ui操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //给一个界面提示
                        Toast.makeText(BannerActivity.this,"倒计时"+time+"秒 跳转",Toast.LENGTH_SHORT).show();

                    }
                });
                if(time==0){
                    Intent intent =new Intent(BannerActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer = new Timer();
/*
        一秒钟后每隔一秒钟开始执行，并且每隔一秒钟执行一次
        线程调度
*/
        timer.schedule(task,1000,1000);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //如果业务写在这里是空白的
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        在页面退出的时候关闭线程
        task.cancel();
        timer.cancel();

    }
}
