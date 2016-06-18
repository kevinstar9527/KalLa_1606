package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.JumpManager;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SpecialItem extends RelativeLayout {
    private ImageView ivContent,ivPlay;
    private TextView tvDes,tvRename;
    private Special special;


    public SpecialItem(Context context,Special special){

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
    public void setSpecial(final Special special){
        //显示内容

        tvRename.setText(special.getDes());
        tvDes.setText(special.getRname());
        ImageLoader.getInstance().displayImage(special.getPic(),ivContent, ImageUtil.getRoundCircleOption());
        if(ivContent!=null){
            Log.e("ivContent","有图片啊");
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!special.getMp3PlayUrl().isEmpty()){
                    Log.e("ivContent",special.getMp3PlayUrl());
                    JumpManager.jumpToPlayer1(getContext(),special.getMp3PlayUrl());
                   // Log.e("ivContent",special.getMp3PlayUrl());
                }else {
                    if (!special.getWeburl().isEmpty()) {
                        Log.e("ivContent",special.getWeburl());
                        JumpManager.jumpToWeb(getContext(),special.getWeburl());
                        //Log.e("ivContent",special.getWeburl());
                    }
                    else if (special.getWeburl().isEmpty()&&special.getMp3PlayUrl().isEmpty()){
                        JumpManager.jumpToPlayer2(getContext());
                    }
                }
            }
        });

    }
    public SpecialItem(Context context, AttributeSet set){

        super(context,set);
        initView(context);

    }
}
