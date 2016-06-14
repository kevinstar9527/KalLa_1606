package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Anchor;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.AnchorInner;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CornerAchorPannel extends LinearLayout {

    private TextView textView,tvMove;
    public CornerAchorPannel(Context context, Anchor recommond) {
        super(context);
        inflate(context, R.layout.widget_corner_panel,this);
        textView = (TextView) findViewById(R.id.guess_pannel_tv);
        tvMove = (TextView) findViewById(R.id.special_pannel_tv_more);
        setRecommend(recommond);
        //左下右上
        setPadding(20,20,30,30);
        setOrientation(VERTICAL);
    }

    public CornerAchorPannel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setRecommend(Anchor recommend){
        LogUtil.e("这里是SpecialPannel 页面的设置 ....."+recommend.getName());
        LogUtil.e("这里显示hasmore的值........"+recommend.getHasmore());
        textView.setText(recommend.getName());
        int hasmore = recommend.getHasmore();
        if(hasmore==0){
            tvMove.setVisibility(GONE);
        }
        List<AnchorInner> dataList = recommend.getDataList();
        if(dataList.size()!=2) {

            CornerAchorImageLayout cornerLayout = new CornerAchorImageLayout(getContext(), dataList);
            addView(cornerLayout);
        }
//        }else{
//
//            Log.e("print","这里没执行到？");
//            List<AnchorInner> special03 = dataList.subList(0, 3);
//            CornerAchorImageLayout specialLayout6 = new CornerAchorImageLayout(getContext(),special03);
//
//            List<AnchorInner> special36 = dataList.subList(3, 6);
//            CornerAchorImageLayout specialLayout36 = new CornerAchorImageLayout(getContext(),special36);
//
//            addView(specialLayout6);
//            addView(specialLayout36);
//
//        }
    }
}
