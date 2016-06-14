package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.RadioInner;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CornerRaidoImageLayout extends LinearLayout {
    private CornerRadioImageItem c1,c2,c3;
    private List<RadioInner>list;

    public CornerRaidoImageLayout(Context context, List<RadioInner>listItem){
        super(context);
        initViews(context);
        this.list= listItem;
        setSpecialList();
    }

    private void initViews(Context context) {

        //初始化控件
        inflate(context, R.layout.widget_radio_corner_image_layout,this);
        c1 = (CornerRadioImageItem) findViewById(R.id.c1);
        c2 = (CornerRadioImageItem) findViewById(R.id.c2);
        c3 = (CornerRadioImageItem) findViewById(R.id.c3);

    }

      /*显示内容*/
    public void setSpecialList(){

        c1.setSpecial(list.get(0));
        c2.setSpecial(list.get(1));
        c3.setSpecial(list.get(2));
    }
}
