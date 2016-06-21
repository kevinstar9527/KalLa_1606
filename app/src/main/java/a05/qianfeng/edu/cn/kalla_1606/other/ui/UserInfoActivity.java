package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/18.
 */
public class UserInfoActivity extends AppCompatActivity {
    @Bind(R.id.header_photo)
    ImageView headerPhoto;
    @Bind(R.id.camera_photo)
    ImageView cameraPhoto;
    @Bind(R.id.myradio_iv)
    ImageView myradioIv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    //请求码
    private int decodePhoto = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        //为图片设置圆角图片
        ImageLoader.getInstance().displayImage("drawable://"+R.drawable.ic_launcher,headerPhoto, ImageUtil.getCircleOption());
      //  ImageLoader.getInstance().displayImage("drawable://"+R.drawable.ic_camera,cameraPhoto,);

    }

    @OnClick({R.id.header_photo, R.id.camera_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_photo:
                getSuoLueTuForHeadPhoto();
                break;
            case R.id.camera_photo:
                break;
        }
    }

    private void getSuoLueTuForHeadPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,decodePhoto);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if (requestCode==decodePhoto)
    }
}
