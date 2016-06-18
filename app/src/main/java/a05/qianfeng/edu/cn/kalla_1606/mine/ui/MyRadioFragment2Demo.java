package a05.qianfeng.edu.cn.kalla_1606.mine.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;

/**
 * Created by Administrator on 2016/6/17.
 */
public class MyRadioFragment2Demo extends BaseFragment {

    private TabLayout tabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myradio2;
    }

    @Override
    protected void initViews() {

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.toolbar);
        //原来是这样取到的actiobar
       // ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        tabLayout = (TabLayout) toolbar.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

    }
}
