package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.adapter.CommonFragmentPagerAdapter;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.Contants;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.IndexViewDot;

/**
 * 引导页
 * <p/>
 * Created by Administrator on 2016/6/6.
 * @date :16/6/6
 */
public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private IndexViewDot indexViewDot;

    private int[] videoIds = new int[]{
      R.raw.splash_1,
      R.raw.splash_2,
      R.raw.splash_4,
      R.raw.splash_5
    };
//左边的图片资源id
    private int[] ivLefts = new int[]{
            R.drawable.guide_anim_1_2,
            R.drawable.guide_anim_2_2,
            R.drawable.guide_anim_3_2,
            R.drawable.guide_anim_4_2,
    };

    //右边的图片资源ID
    private int[] ivRights = new int[]{
      R.drawable.guide_anim_1_1,
      R.drawable.guide_anim_2_1,
      R.drawable.guide_anim_3_1,
      R.drawable.guide_anim_4_1
    };
    /*用来显示上一次显示的位置*/
    private int lastPositon;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //充满全屏
        //requestWindowFeature(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //界面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        indexViewDot = (IndexViewDot) findViewById(R.id.dot);
        viewPager = (ViewPager) findViewById(R.id.guide_vp);
        final List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            GuideFragment fragment = new GuideFragment(ivLefts[i],ivRights[i],videoIds[i],i);
            list.add(fragment);
        }
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //隐藏上一次的画面
                if(lastPositon!=-1){
                    GuideFragment fragment1= (GuideFragment) list.get(lastPositon);
                    fragment1.isHidden();
                }

                //显示当前页面的动画
                GuideFragment fragment= (GuideFragment) list.get(position);
                fragment.showAnim();

                //设置小圆点
                indexViewDot.setCurrIndex(position);
                //
                lastPositon=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        GuideFragment firstFragement = (GuideFragment) list.get(0);
//        firstFragement.showAnim();
        findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转主页
                Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
                //修改配置文件的内容
                SharedPreferences sharedPreferences = getSharedPreferences(Contants.FLAG_FIRST_USE, Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean(Contants.FLAG_FIRST_USED_VALUT,false);
                edit.commit();
                //防止从Home界面返回到引导页,
            }
        });
    }
}
