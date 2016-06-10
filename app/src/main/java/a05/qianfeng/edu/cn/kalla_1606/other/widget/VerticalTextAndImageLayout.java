package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * Created by Administrator on 2016/6/10.
 */
public class VerticalTextAndImageLayout extends LinearLayout {



    List<String> roundRectText;
    List<String> bannerText;
    private ImageView imageView;
    private VerticalText leftText;
    private VerticalTextAndRoundRect rightText;



    public VerticalTextAndImageLayout(Context context,List<String> verticalTextBanner) {
        super(context);
        //为广告字体赋值(为何静态填充不行)

//        inflate(context,R.layout.fragment_recommend_scroll_new,this);
//        imageView = (ImageView) imageView.findViewById(R.id.banner_iv);
//        leftText = (VerticalText) imageView.findViewById(R.id.banner_content_tv_l);
//        rightText = (VerticalTextAndRoundRect) imageView.findViewById(R.id.banner_content_tv_r);

        initData(verticalTextBanner);

        initViews(context);

        setOrientation(HORIZONTAL);
    }

    public VerticalTextAndImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void initViews(Context context){

        imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.kaola_headline);
        //为图片设置宽高

        LayoutParams paramsIv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT) ;
        /*设置控件的间距*/
        paramsIv.setMargins(10,10,10,10);
        imageView.setLayoutParams(paramsIv);
        addView(imageView);
        //将高度宽度设置为文字文字的宽度
        LayoutParams paramsRoundRect = new LinearLayout.LayoutParams(80,50);
        paramsRoundRect.setMargins(8,10,10,8);
        rightText = new VerticalTextAndRoundRect(context,roundRectText);
        rightText.setLayoutParams(paramsRoundRect);
        addView(rightText);
        //将高度设置为文字高度的+20
        LayoutParams paramsBanner = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,50);
        paramsBanner.setMargins(3,10,10,2);
        leftText = new VerticalText(context,bannerText);
        leftText.setLayoutParams(paramsBanner);
        addView(leftText);

    }

    public void initData(List<String> bannerText){
        roundRectText = new ArrayList<>();
        roundRectText.add("活动");
        roundRectText.add("获奖");
        //赋值成功
        this.bannerText = bannerText;

//        leftText.setList(roundRectText);
//        rightText.setList(bannerText);
    }
}
