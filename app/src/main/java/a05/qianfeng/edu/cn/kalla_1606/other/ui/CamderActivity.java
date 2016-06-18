package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/18.
 */
public class CamderActivity extends AppCompatActivity {
    @Bind(R.id.getDecode)
    Button getDecode;
    @Bind(R.id.getSource)
    Button getSource;
    @Bind(R.id.camera_photo_iv)
    ImageView cameraPhotoIv;
    /*请求码*/
    int code_album = 3;
    /*获取源图请求码*/
    int source_album = 4;
    private File photo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        photo = new File(FileUtil.dir_image,"spuerStar.jpg");
    }

    @OnClick({R.id.getDecode, R.id.getSource, R.id.camera_photo_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getDecode:
                getDecodeMethod();
                break;
            case R.id.getSource:
                getSourceIv();
                break;
            case R.id.camera_photo_iv:
                getCameraIv();
                break;
        }
    }
/*设置图片*/
    private void getCameraIv() {
    }

    /*获取原图片*/
    private void getSourceIv() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //指定保存路径
        Uri uri = Uri.fromFile(photo);
        //另一种设置uri的方式 Uri uri = Uri.parse("file///sdcard/***.jpg");
        //指定动作，拍完后保存到指定路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,source_album);
    }

    /*h获取缩略图*/
    private void getDecodeMethod() {
        /*指定打开照相机的操作意图*/
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,code_album);
    }
    /*从请求的页面获取返回的结果必须重写这个方法*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
        if (requestCode==code_album) {
           /* Bundle tempBitmap=result.getExtras();
            tempBitmap.get("data");*/
          Bitmap bitmap =  result.getParcelableExtra("data");
            cameraPhotoIv.setImageBitmap(bitmap);
        }else if (requestCode ==source_album){
//            //解析图片
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            //设置缩放比例，值越大，图片越小，1表示源图大小
//            options.inSampleSize = 2; //1表示原始大小，2表示缩放到一半
//            Bitmap bitmap = BitmapFactory.decodeFile(photo.getAbsolutePath(),options);
//            cameraPhotoIv.setImageBitmap(bitmap);
            /*另一种方法*/
            //获取屏幕的宽高(选取最合适的宽高比例)

            int width =  getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;

            BitmapFactory.Options options1= new BitmapFactory.Options();
            options1.inJustDecodeBounds =true;
            Bitmap bitmap1 = BitmapFactory.decodeFile(photo.getAbsolutePath(),options1);
            int widthRatio = (int) Math.ceil(options1.outWidth/(float)width);
            int heightRatio = (int) Math.ceil(options1.outHeight/(float)height);

            if (widthRatio>1&&heightRatio>1){
                if(widthRatio>heightRatio){
                    options1.inSampleSize = widthRatio;
                }else{
                    options1.inSampleSize = heightRatio;

                }
            }
            options1.inJustDecodeBounds = false;
            //得到真实适合的缩放比例的图片
            bitmap1 = BitmapFactory.decodeFile(photo.getAbsolutePath(),options1);
            cameraPhotoIv.setImageBitmap(bitmap1);

        }
    }
}
