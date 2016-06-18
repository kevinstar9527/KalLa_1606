package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2016/6/16.
 */
public class KaoLaPageTransformer implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.2f ;
    private static final float MIN_SCALE = 0.8f;

    @Override
    public void transformPage(View page, float position) {
        if (position<-1){
            position=-1;
        }
        else if (position>1){
            position = 1;
        }
        //临时的缩放比例
        float tempScale = position<0?1+position:1-position;
        //缩放的变化系数
        float slope = (MAX_SCALE-MIN_SCALE)/1;
        //真实缩放比例=最小的比例+临时的缩放比例*缩放系数
        float scaleValue = MIN_SCALE+tempScale*slope;
        LogUtil.w("scaleValue = "+tempScale*slope);
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);
    }
}
