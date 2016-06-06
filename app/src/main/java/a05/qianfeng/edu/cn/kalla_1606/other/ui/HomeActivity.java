package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.DiscoverFragment;
import a05.qianfeng.edu.cn.kalla_1606.download.DownLoadOfflineFragment;
import a05.qianfeng.edu.cn.kalla_1606.mine.MyRadioFragment;

/**
 * Created by Administrator on 2016/6/6.
 */
public class HomeActivity extends AppCompatActivity  {


    private RadioGroup group;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, new DiscoverFragment(), null);
        replace.commit();


    }

    private void initView() {

        group = (RadioGroup) findViewById(R.id.bar);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_discover:
                        //点击跳转到发现页面
                        FragmentTransaction replace = getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, new DiscoverFragment(), null);
                        replace.commit();
                        break;
                    case R.id.btn_mine:
                        //点击跳转到我的电台页面
                        FragmentTransaction replace1 = getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, new MyRadioFragment(), null);
                        replace1.commit();
                        break;
                    case R.id.btn_offline:
                        FragmentTransaction replace2 = getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container, new DownLoadOfflineFragment(), null);
                        replace2.commit();
                        break;
                }
            }
        });
    }




    }

