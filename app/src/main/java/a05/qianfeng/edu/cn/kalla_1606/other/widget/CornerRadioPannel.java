package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Radio;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.RadioInner;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CornerRadioPannel extends LinearLayout {

    private TextView textView,tvMove;
    public CornerRadioPannel(Context context, Radio recommond) {
        super(context);
        inflate(context, R.layout.widget_corner_panel,this);
        textView = (TextView) findViewById(R.id.guess_pannel_tv);
        tvMove = (TextView) findViewById(R.id.special_pannel_tv_more);
        setRecommend(recommond);
        //左下右上
        setPadding(20,20,30,30);
        setOrientation(VERTICAL);
    }

    public CornerRadioPannel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setRecommend(Radio recommend){
        LogUtil.e("这里是SpecialPannel 页面的设置 ....."+recommend.getName());
        LogUtil.e("这里显示hasmore的值........"+recommend.getHasmore());
        textView.setText(recommend.getName());
        int hasmore = recommend.getHasmore();
        if(hasmore==0){
            tvMove.setVisibility(GONE);
        }
        List<RadioInner> dataList = recommend.getDataList();
        if(dataList.size()==3){

            CornerRaidoImageLayout cornerLayout = new CornerRaidoImageLayout(getContext(),dataList);
            addView(cornerLayout);
        }else{
//
//            Log.e("print","这里没执行到？");
//            List<Special> special03 = dataList.subList(0, 3);
//            SpecialLayout specialLayout6 = new SpecialLayout(getContext(),special03);
//
//            List<Special> special36 = dataList.subList(3, 6);
//            SpecialLayout specialLayout36 = new SpecialLayout(getContext(),special36);
//
//            addView(specialLayout6);
//            addView(specialLayout36);

        }
    }
}
