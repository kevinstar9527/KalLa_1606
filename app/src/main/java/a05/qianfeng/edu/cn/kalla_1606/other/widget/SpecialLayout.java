package a05.qianfeng.edu.cn.kalla_1606.other.widget;

import android.content.Context;
import android.widget.LinearLayout;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SpecialLayout extends LinearLayout {
    private SpecialItem si1,si2,si3;
    private List<Special>list;

    public SpecialLayout(Context context,List<Special>listItem){
        super(context);
        initViews(context);
        this.list= listItem;
        setSpecialList(listItem);
    }

    private void initViews(Context context) {

        //初始化控件
        inflate(context, R.layout.widget_special_layout,this);
        si1 = (SpecialItem) findViewById(R.id.si1);
        si2 = (SpecialItem) findViewById(R.id.si2);
        si3 = (SpecialItem) findViewById(R.id.si3);

    }

    /*显示内容*/
    public void setSpecialList(List<Special> list){

        si1.setSpecial(list.get(0));
        si2.setSpecial(list.get(1));
        si3.setSpecial(list.get(2));
    }
}
