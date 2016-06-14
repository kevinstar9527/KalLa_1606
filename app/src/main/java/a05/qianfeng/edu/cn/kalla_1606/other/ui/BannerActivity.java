package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.HttpUtils;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTranSformation;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.OtherHttpUtils;

/**
 * Created by Administrator on 2016/6/6.
 */
public class BannerActivity extends AppCompatActivity {
    private ImageView imageView;
    private Timer timer;
    private TimerTask task ;//一个线程
    private int time=3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        imageView = (ImageView) findViewById(R.id.banner_content_iv);

        task = new TimerTask() {
            @Override
            public void run() {
                time--;
                //在主线程中运行ui操作
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //给一个界面提示
                        Toast.makeText(BannerActivity.this, "倒计时" + time + "秒 跳转", Toast.LENGTH_SHORT).show();

                    }
                });
                if (time == 0) {
                    Intent intent = new Intent(BannerActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer = new Timer();
/*
        一秒钟后每隔一秒钟开始执行，并且每隔一秒钟执行一次
        线程调度
*/
        timer.schedule(task, 3000, 1000);
        //测试新的封装类

        OtherHttpUtils.getBanner(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object resultPre) {
                JSONObject root = null;
                try {
                    //检测返回的数据的正确性
                    LogUtil.w(resultPre.toString());
                    root = new JSONObject(resultPre.toString());
                    JSONObject result = root.getJSONObject("result");
                    String img = result.getString("img");
                    LogUtil.w("img = " + img);
                    //showImage(img);
                    /*Picasso网络请求*/
                    Picasso.with(BannerActivity.this)
                            .load(img)//下载图片
                            .error(R.drawable.ic_launcher)//设置默认图片
                            .placeholder(R.drawable.album_hidden)//正在加载中显示的图片
                            .resize(200,300)//把图片显示多大
                                .transform(new KaoLaTranSformation())
                            .into(imageView);//到那个控件上
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void error(String msg) {

            }
        });


//        new Thread(){
//            @Override
//            public void run() {
//               Object o =  HttpUtils.doGet(OtherHttpUtils.url);
//                try {
//                    JSONObject root = new JSONObject(o.toString());
//                    String message = root.getString("message");
//                    if("success".equals(message)) {
//                        JSONObject result = root.getJSONObject("result");
//
//                        String img = result.getString("img");
//                        LogUtil.w("json图片请求成功 img = "+img);
//
//                       String rename = FileUtil.getFileNameByHashCode(img);
//                        File image = new File(FileUtil.dir_image,"banner.jpg");
//                        //判断这个图片有没有下载过，如果存在表示下载过
//                        if(!image.exists()) {
//                            image = HttpUtils.downLoadEverything(img, FileUtil.dir_image, "banner.jpg");
//                        }
//
//                        final Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                //显示图片
//                                if(bitmap!=null) {
//                                    imageView.setImageBitmap(bitmap);
//                                }else{
//                                    LogUtil.e("bitMap的值没有取到！");
//                                }
//                            }
//                        });
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }

    /*img是图片的url？yes*/
    }

    private void showImage(final String img){
        final String rename = FileUtil.getFileNameByHashCode(img);
        File image = new File(FileUtil.dir_image,rename);
        //判断着个图片有没有下载过，如果存在，表示下载过
        if(!image.exists()) {
            KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
                @Override
                public Object doRequest() {
                    //下载图片
                    return HttpUtils.downLoadEverything(img, FileUtil.dir_image, rename,null);
                }
            };

            KaoLaTask.IRequestCallBack callBack = new KaoLaTask.IRequestCallBack() {
                @Override
                public void success(Object object) {
                    //将返回的图片资源提取出来（目的是为了得到它的缓存路径）
                    File file = (File) object;
                    final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);

                }

                @Override
                public void error(String msg) {
                    Toast.makeText(BannerActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            };

            KaoLaTask kaoLaTask = new KaoLaTask(request,callBack);
            kaoLaTask.execute();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //如果业务写在这里是空白的
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        在页面退出的时候关闭线程
        task.cancel();
        timer.cancel();

    }
}
