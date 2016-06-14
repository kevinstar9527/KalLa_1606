package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.AnchorInner;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CornerAchorImageItem extends RelativeLayout {

    private ImageView iv;
    private TextView title;
    private TextView content;

    public CornerAchorImageItem(Context context, AnchorInner special){

        super(context);
        initView(context);
        setSpecial(special);
    }

    private void initView(Context context) {
        //把指定的布局文件作为当前控件的显示内容
      inflate(context, R.layout.widget_special_corner_item,this);
        iv = (ImageView) findViewById(R.id.corner_iv);
        title = (TextView) findViewById(R.id.corner_item_title);
        content = (TextView)findViewById(R.id.corner_itme_content);

    }

    /*设置内容*/
    public void setSpecial(AnchorInner special){
        ImageLoader.getInstance().displayImage(special.getAvatar(),iv, ImageUtil.getCircleOption());
        Log.e("msg",special.getNickName());
        title.setText(special.getNickName());
       // Log.e("msg",special.getLikedNum());
        content.setText(special.getRecommendReson());

    }
    public CornerAchorImageItem(Context context, AttributeSet set){

        super(context,set);
        initView(context);

    }
}
