package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.adapter.GuessAdapter;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Recommond;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/8.
 */
public class GuessPannel extends LinearLayout {


    private TextView guessTitle;
    private TextView refresh;

    public GuessPannel(Context context, Recommond recommond) {
        super(context);
        inflate(context, R.layout.widget_guess_panel, this);
        guessTitle = (TextView) findViewById(R.id.guess_pannel_tv);
        refresh = (TextView) findViewById(R.id.guess_pannel_tv_re);

        setRecommend(recommond);
        //左下右上
        setPadding(20, 20, 20, 20);
        setOrientation(VERTICAL);
    }

    public GuessPannel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRecommend(Recommond recommend) {
        LogUtil.e("这里是GuessPanel 页面的设置 ....." + recommend.getName());
        Log.e("GuessPanel:",recommend.getName());
        guessTitle.setText(recommend.getName());
        List<Special> dataList = recommend.getDataList();
        RecyclerView recyclerView = new RecyclerView(getContext());
        /*宽高*/
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        recyclerView.setLayoutParams(params);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        /*设置方向为垂直*/
        manager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(manager);

        GuessAdapter adapter = new GuessAdapter(getContext(),dataList);
        recyclerView.setAdapter(adapter);

        addView(recyclerView);
    }
}

