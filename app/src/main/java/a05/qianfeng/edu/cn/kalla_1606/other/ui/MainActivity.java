package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import a05.qianfeng.edu.cn.kalla_1606.other.utils.Contants;

public class MainActivity extends AppCompatActivity {

    private Intent intent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(Contants.FLAG_FIRST_USE, Context.MODE_PRIVATE);
        boolean firstUsed = sharedPreferences.getBoolean(Contants.FLAG_FIRST_USED_VALUT, true);
        if(firstUsed){
            //如果是第一次使用那么跳转到引导页
            intent = new Intent(this,GuideActivity.class);
            startActivity(intent);
        }else{
             intent = new Intent(this,BannerActivity.class);
        }

        startActivity(intent);

        finish();

    }
}
