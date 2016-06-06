package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.FullScreenVideoView;

/**
 * 引导页每一页的内容
 * Created by Administrator on 2016/6/6.
 */
@SuppressLint("ValidFragment")
public class GuideFragment extends Fragment {

    private int videId;
    private int ivLeftId,ivRightId;
    private FullScreenVideoView videoView;
    private ImageView ivLeft,ivRight;
    Animation animLeft,animRight;
    //判断当前这个Fragment是第几个Fragment
    private int position;

    @SuppressLint("ValidFragment")
    public GuideFragment( int ivLeftId, int ivRightId,  int videoId,int position) {

        this.ivLeftId = ivLeftId;
        this.ivRightId = ivRightId;
        this.videId = videoId;
        this.position = position;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      return   inflater.inflate(R.layout.fragment_guide,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getView() 找到根布局
        videoView = (FullScreenVideoView) getView().findViewById(R.id.guide_vv);
        ivLeft = (ImageView) getView().findViewById(R.id.guide_left_iv);
        ivRight = (ImageView) getView().findViewById(R.id.guide_right_iv);
        //设置显示的图片内容
        ivLeft.setImageResource(ivLeftId);
        ivRight.setImageResource(ivRightId);
        //表示如果当前页面是第一个页面调用方法,那么执行动画
        if(position==0){

            this.showAnim();

        }
//        添加视频的本地路径
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+getActivity().getPackageName()+"/"+videId);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            //设置循环播放
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        //设置播放路径
        videoView.setVideoURI(uri);
        //开始播放
        videoView.start();

    }

    /*
  * 当页面隐藏的时候调用该方法
  * */
    public void hideImages(){

        //用Gone的目的：防止来回滑动的时候太快导致上一页动画没结束
        ivLeft.setVisibility(View.INVISIBLE);
        ivRight.setVisibility(View.INVISIBLE);
        if(animLeft!=null&&animLeft.hasEnded()){

            animLeft.cancel();
        }
        if(animRight!=null&&animRight.hasEnded()){

            animRight.cancel();
        }


    }

    public void showAnim(){

        ivRight.setVisibility(View.INVISIBLE);
        ivLeft.setVisibility(View.INVISIBLE);
        //当切换回来时，开启动画
        videoView.start();
        //加载动画(需要改成局部变量)
        animLeft = AnimationUtils.loadAnimation(getContext(),R.anim.guide_iv_left);
        animRight = AnimationUtils.loadAnimation(getContext(),R.anim.guide_iv_right);
        animRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //在执行左边的动画
                ivLeft.startAnimation(animLeft);
                //动画结束后要显示出来
                ivRight.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始前的操作
                /*
                * 比如这里可以初始化一些UI
                * */
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束的操作
                /*比如这里可以等动画结束进行一些账号登陆或者网络数据请求等*/
                //ivRight.startAnimation(animRight);
                ivLeft.setVisibility(View.VISIBLE);
            }




            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        //执行动画
        //先执行右边的动画
        ivRight.startAnimation(animRight);
    }

    @Override
    public void onPause() {
        super.onPause();
        //当暂停的时候停止页面
        videoView.pause();
    }
}
