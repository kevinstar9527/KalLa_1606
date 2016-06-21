package a05.qianfeng.edu.cn.kalla_1606.mine.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.adapter.CommonFragmentPagerAdapter;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.HomeActivity;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/6.
 */
@SuppressLint("ValidFragment")
public class MyRadioFragment extends BaseFragment {


    public static final int RESULT_OK = 1;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private ImageView message;
    private ViewPager viewPager;
    private HomeActivity home;
    private RelativeLayout login;
    private ImageView middle_photo;
    //背景图片以及头像

    public RelativeLayout getLogin() {
        return login;
    }

    public void setLogin(RelativeLayout login) {
        this.login = login;
    }

    private Bitmap photo = null;

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //用来接收从登陆界面返回的头像等信息
    private String nickname = null;
    public String picUrl = null;
    private View subLayout;

    public ImageView getMiddle_photo() {
        return middle_photo;
    }

    public void setMiddle_photo(ImageView middle_photo) {
        this.middle_photo = middle_photo;
    }

    @SuppressLint("ValidFragment")
    public MyRadioFragment() {

    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_myradio;
    }

    @Override
    protected void initViews() {

        ButterKnife.bind(root);

       // subLayout =root.findViewById(R.id.log_inside);
        //找到include中的视图
        login = (RelativeLayout)root.findViewById(R.id.to_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Login","这里执行了");
                Intent intent = new Intent(getActivity(),LogOnActivity.class);
                getActivity().startActivityForResult(intent,RESULT_OK);
            }
        });
        //设置图像
        middle_photo = (ImageView)root.findViewById(R.id.middle_photo);

        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        toolbar.setNavigationIcon(R.drawable.selector_myradio_settings);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        message = (ImageView) root.findViewById(R.id.myradio_iv);
        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("收听历史"));
        tabLayout.addTab(tabLayout.newTab().setText("订阅"));
        tabLayout.addTab(tabLayout.newTab().setText("收藏"));

        viewPager = (ViewPager) root.findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();
        Fragment[] fragmnetList = new Fragment[]{
                new ListeningHistoryFragment(),
                new DingYueFragment(),
                new ListeningHistoryFragment()
        };
        for (int i = 0; i < fragmnetList.length; i++) {
            fragments.add(fragmnetList[i]);
        }
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        //点击跳转到登陆页面



    }

    @Override
    protected void initData() {

        List<Fragment> list = new ArrayList<>();
        list.add(new ListeningHistoryFragment());
        list.add(new DingYueFragment());
        list.add(new FavoriteFragment());

        CommonFragmentPagerAdapter viewPagerAdapter = new CommonFragmentPagerAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    protected void initEvents() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getContext(), "正在点击图标", Toast.LENGTH_SHORT).show();
//                Log.e("Login","这里执行了");
//                Intent intent = new Intent(getContext(),LogOnActivity.class);
//                startActivity(intent);
            }
        });

        /*为toolbar中自定义的视图设置事件监听*/
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "您正在点击的是自定义视图", Toast.LENGTH_SHORT).show();
            }
        });
        //为viewPager设置滑动监听，以及与tablayout的联动
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                //设置滑动事件监听
                home = (HomeActivity) getActivity();
                home.setDiscoverPagerIndex(position);
                Log.e("Scroll", "位置:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //点击跳转到登陆按钮


    }

    @Override
    public void onResume() {
        super.onResume();
        if(photo!=null){
            Drawable drawable = new BitmapDrawable(getResources(),photo);
            login.setBackground(drawable);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.myradio_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mylistening) {

            Toast.makeText(getContext(), "这是菜单按钮", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
