package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * Created by Administrator on 2016/6/6.
 */
public class BannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
    }
    public void jumpToHome(View view){
        Intent intent =new Intent(BannerActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
