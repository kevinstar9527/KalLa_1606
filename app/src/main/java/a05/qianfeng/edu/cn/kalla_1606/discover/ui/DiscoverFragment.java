package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.adapter.CommonFragmentPagerAdapter;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;

/**
 * Created by Administrator on 2016/6/6.
 */
public class DiscoverFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected int getLayoutId() {

        return R.layout.fragment_discover;
    }

    @Override
    protected void initViews() {

        tabLayout = (TabLayout) root.findViewById(R.id.dicover_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("推荐"));
        tabLayout.addTab(tabLayout.newTab().setText("分类"));
        tabLayout.addTab(tabLayout.newTab().setText("电台"));
        tabLayout.addTab(tabLayout.newTab().setText("直播"));
        tabLayout.addTab(tabLayout.newTab().setText("主播"));


        viewPager = (ViewPager)root.findViewById(R.id.radio_viewPager);

    }

    @Override
    protected void initData() {


/*
        初始化数据（Fragment）为ViewPager添加页面
*/
        List<Fragment>list = new ArrayList<>();
        list.add(new RecommendFragment());
        list.add(new TypeFragment());
        list.add(new RadioFragment());
        list.add(new LiveFragment());
        list.add(new AnchorFragment());

        CommonFragmentPagerAdapter viewPagerAdapter = new CommonFragmentPagerAdapter(getActivity().getSupportFragmentManager(), list);
        viewPager.setAdapter(viewPagerAdapter);
    }



    @Override
    protected void initEvents() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //切换ViewPager的时候让TabLayout联动
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击TabLayout的时候，让ViewPager联动
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
