package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import a05.qianfeng.edu.cn.kalla_1606.R;

/**
 * Created by Administrator on 2016/6/11.
 */
public class ViewPagerBehavior extends CoordinatorLayout.Behavior {

    private int targetId;

    public ViewPagerBehavior() {

    }

    public ViewPagerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.target_view);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if(typedArray.getIndex(i)==R.styleable.target_view_behavior_target){
                targetId = typedArray.getResourceId(attr,-1);
            }

        }
        typedArray.recycle();
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setY(dependency.getY()+dependency.getHeight());
        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId()==targetId;
    }
}
