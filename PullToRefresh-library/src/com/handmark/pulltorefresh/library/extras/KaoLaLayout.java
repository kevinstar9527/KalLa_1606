package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

/**
 * Created by Administrator on 2016/6/12.
 */
public class KaoLaLayout extends LoadingLayout {

    private AnimationDrawable drawable;
    public KaoLaLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
    }

    @Override
    protected int getDefaultDrawableResId() {
        /*得到真动画的图片*/
        return R.drawable.refreshing;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {
            drawable = (AnimationDrawable) imageDrawable;
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    @Override
    protected void refreshingImpl() {
       // mHeaderImage.startAnimation();
        mHeaderImage.setImageDrawable(drawable);
        drawable.start();
    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {
        mHeaderImage.clearAnimation();
    }
}
