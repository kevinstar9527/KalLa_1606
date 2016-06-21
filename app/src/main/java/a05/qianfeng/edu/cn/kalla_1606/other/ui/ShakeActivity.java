package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ShakeSensorListener;

/**
 *
 * 摇一摇
 *
 * Created by Administrator on 2016/6/18.
 */
public class ShakeActivity extends AppCompatActivity {


    private ImageView imageView;
    private ShakeSensorListener shakeSensorListener;
    private AnimationDrawable animation;
    private Handler handler = new Handler();
    private Vibrator vibrator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        imageView = (ImageView) findViewById(R.id.shake_content_iv);
        shakeSensorListener = new ShakeSensorListener(this);
        animation = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_shake);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        shakeSensorListener.setIShakeListener(new ShakeSensorListener.IShakeListener() {
            @Override
            public void onShake() {
                //摇动的时候
                //让树摇起来
                Log.e("shake--","正在振动");
                imageView.setImageDrawable(animation);
                animation.start();
//                 long[] patter = new long[]{2000,5000,3000,600,700};
//                vibrator.vibrate(patter,0);
                //两秒后停止
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animation.stop();
                        imageView.setImageResource(R.drawable.a9);
//                        vibrator.cancel();
                    }
                },2000);

                //2.播放振动声音或者振动
                //振动十秒钟
                //有节奏的振动

                //3.执行网络请求
            }
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        shakeSensorListener.unRegister();
    }
}
