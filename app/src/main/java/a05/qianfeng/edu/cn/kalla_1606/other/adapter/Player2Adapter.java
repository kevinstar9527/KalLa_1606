package a05.qianfeng.edu.cn.kalla_1606.other.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.bean.PlayEntity;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Player2Adapter extends PagerAdapter {

    private List<PlayEntity> list = null;
    private LayoutInflater inflater;

    private List<View> viewList = null;

    public Player2Adapter(Context context, List<PlayEntity> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    public List<PlayEntity> getList() {
        return list;
    }

    public void setList(List<PlayEntity> list) {
        this.list = list;
    }

    @Override

    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return object==view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (viewList.size()==0){
            return null;
        }
        View view = viewList.get(position);
        container.addView(viewList.get(position));
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (viewList.size()==0) {

            return;
        }
        container.removeViewAt(position);
    }

    @Override
    public void notifyDataSetChanged() {
        if(list.size()==0){
            for (int i = 0; i < list.size(); i++) {
                View contentView = inflater.inflate(R.layout.layout_palyer,null);
                viewList.add(contentView);
            }
        }
        super.notifyDataSetChanged();
    }
}
