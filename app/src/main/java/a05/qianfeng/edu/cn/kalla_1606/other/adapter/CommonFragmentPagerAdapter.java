package a05.qianfeng.edu.cn.kalla_1606.other.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment>list){
        super(fm);
        this.fragmentList =list;
    }
   


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
