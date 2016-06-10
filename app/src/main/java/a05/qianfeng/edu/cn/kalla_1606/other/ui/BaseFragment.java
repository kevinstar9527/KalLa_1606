package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 本应用里面所有的Fragment的父类
 * Created by Administrator on 2016/6/7.
 */
public abstract class BaseFragment extends Fragment{
    /*表示子类的根布局*/
    protected View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutId(),null);
        /*如果不设置下面这句，将无法填充菜单*/
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*注意调用的顺序，调用顺序不能乱*/
        initViews();
        initEvents();
        initData();
    }
    /*从子类获取布局id*/
    protected abstract int getLayoutId();
    /*视图初始化*/
    protected abstract void initViews();
    /*数据初始化*/
    protected abstract void initData();
    /*事件初始化*/
    protected abstract void initEvents();
}
