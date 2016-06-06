package a05.qianfeng.edu.cn.kalla_1606.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * Created by Administrator on 2016/6/6.
 */
public class MyRadioFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myradio,container,false);
    }
}
