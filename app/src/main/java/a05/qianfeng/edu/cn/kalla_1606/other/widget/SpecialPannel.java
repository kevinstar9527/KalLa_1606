package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Recommond;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SpecialPannel extends LinearLayout {

    private TextView textView,tvMove;
    public SpecialPannel(Context context,Recommond recommond) {
        super(context);
        inflate(context, R.layout.widget_special_panel,this);
        textView = (TextView) findViewById(R.id.special_pannel_tv);
        tvMove = (TextView) findViewById(R.id.special_pannel_tv_more);
        setRecommend(recommond);
        //左下右上
        setPadding(30,30,30,30);
        setOrientation(HORIZONTAL);
    }

    public SpecialPannel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setRecommend(Recommond recommend){
        LogUtil.e("这里是SpecialPannel 页面的设置 ....."+recommend.getName());
        LogUtil.e("这里显示hasmore的值........"+recommend.getHasmore());
        textView.setText(recommend.getName());
        int hasmore = recommend.getHasmore();
        if(hasmore==0){
            tvMove.setVisibility(GONE);
        }
        List<Special> dataList = recommend.getDataList();
        if(dataList.size()==3){
            SpecialLayout specialLayout = new SpecialLayout(getContext(),dataList);
            addView(specialLayout);
        }else{
            List<Special> special03 = dataList.subList(0, 3);
            SpecialLayout specialLayout6 = new SpecialLayout(getContext(),special03);

            List<Special> special36 = dataList.subList(3, 6);
            SpecialLayout specialLayout36 = new SpecialLayout(getContext(),special36);

            addView(specialLayout6);
            addView(specialLayout36);

        }
    }
}
