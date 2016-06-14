package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import a05.qianfeng.edu.cn.kalla_1606.R;

public class NewsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        /*设置返回键*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    private void initViews() {

        tab = (TabLayout) findViewById(R.id.tabLayout);
        tab.addTab(tab.newTab().setText("精选"));
        tab.addTab(tab.newTab().setText("全部"));
        tab.addTab(tab.newTab().setText("综合"));
        tab.addTab(tab.newTab().setText("深度评论"));



    }
}
