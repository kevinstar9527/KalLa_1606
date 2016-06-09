package a05.qianfeng.edu.cn.kalla_1606.mine.ui;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

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


        Toolbar toolbar =  (Toolbar) root.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        tabLayout = (TabLayout) root.findViewById(R.id.myradio_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("收听历史"));
        tabLayout.addTab(tabLayout.newTab().setText("订阅"));
        tabLayout.addTab(tabLayout.newTab().setText("收藏"));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

    }
}
