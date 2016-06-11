package a05.qianfeng.edu.cn.kalla_1606.mine.ui;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.HomeActivity;

/**
 * Created by Administrator on 2016/6/6.
 */
@SuppressLint("ValidFragment")
public class MyRadioFragment extends BaseFragment {

    private HomeActivity activity;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ImageView message;

    public  MyRadioFragment(){}

    @SuppressLint("ValidFragment")
    public MyRadioFragment(HomeActivity activity){

        this.activity = activity;
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_myradio;
    }

    @Override
    protected void initViews() {


        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        toolbar.setNavigationIcon(R.drawable.selector_myradio_settings);
        activity.setSupportActionBar(toolbar);

        message = (ImageView) root.findViewById(R.id.myradio_iv);
        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("收听历史"));
        tabLayout.addTab(tabLayout.newTab().setText("订阅"));
        tabLayout.addTab(tabLayout.newTab().setText("收藏"));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "正在点击图标", Toast.LENGTH_SHORT).show();
            }
        });

        /*为toolbar中自定义的视图设置事件监听*/
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "您正在点击的是自定义视图", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        activity.getMenuInflater().inflate(R.menu.myradio_toolbar,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mylistening){

            Toast.makeText(activity, "这是菜单按钮", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
