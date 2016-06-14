package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MyRadiolItem extends RelativeLayout {
    private ImageView ivContent,ivPlay;
    private TextView tvDes,tvRename;
    private Special special;

    public MyRadiolItem(Context context, Special special){

        super(context);
        initView(context);
        setSpecial(special);
    }

    private void initView(Context context) {
        //把指定的布局文件作为当前控件的显示内容
        inflate(context, R.layout.widget_special_item,this);
        ivContent = (ImageView) findViewById(R.id.special_iv);
        ivPlay = (ImageView) findViewById(R.id.special_item_des_iv);
        //显示内容
        tvRename = (TextView) findViewById(R.id.speical_item_title);
        tvDes = (TextView) findViewById(R.id.special_itme_content);
    }

    /*设置内容*/
    public void setSpecial(Special special){
        //显示内容
        LogUtil.e(" setSpecial....."+special.getDes());
        LogUtil.e(" setSpecial....."+special.getRname());
        LogUtil.e("setSpecial........."+special.getPic());
        tvRename.setText(special.getDes());
        tvDes.setText(special.getRname());
        ImageLoader.getInstance().displayImage(special.getPic(),ivContent, ImageUtil.getDefaultOption());
        if(ivContent!=null){
            Log.e("ivContent","有图片啊");
        }

    }
    public MyRadiolItem(Context context, AttributeSet set){

        super(context,set);
        initView(context);

    }
}
