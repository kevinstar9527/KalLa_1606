package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CornerImageLayout extends LinearLayout {
    private CornerImageItem c1,c2,c3;
    private List<Special>list;

    public CornerImageLayout(Context context, List<Special>listItem){
        super(context);
        initViews(context);
        this.list= listItem;
        setSpecialList();
    }

    private void initViews(Context context) {

        //初始化控件
        inflate(context, R.layout.widget_corner_image_layout,this);
        c1 = (CornerImageItem) findViewById(R.id.c1);
        c2 = (CornerImageItem) findViewById(R.id.c2);
        c3 = (CornerImageItem) findViewById(R.id.c3);

    }

      /*显示内容*/
    public void setSpecialList(){

        c1.setSpecial(list.get(0));
        c2.setSpecial(list.get(1));
        c3.setSpecial(list.get(2));
    }
}
