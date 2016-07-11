package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.adapter.Player2PagerAdapter;
import a05.qianfeng.edu.cn.kalla_1606.other.bean.PlayEntity;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.Contants;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FastBlur;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.HttpUtils;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaPageTransformer;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.OtherHttpUtils;

/**
 * 1.获取playerUrl对应的url的值，下载文件
 * 2.用字BufferReader读取文件获得一http开头的一行url
 * 3截取第二步得到url下载文件
 * 4.读取以.ts结尾的数据就是我们需要的文件
 *
 *
 *
 * Created by Administrator on 2016/6/16.
 */
public class Player2Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private Player2PagerAdapter adapter;
    private List<PlayEntity> list= new ArrayList<>();
    private ImageView headerIv;
    private TextView title_tv;
    private TextView type_tv;
    private RelativeLayout rootLayout;
    private Bitmap tempBitmap;
    //播放页面底部背景图片
    private ImageView ivBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);




        rootLayout = (RelativeLayout)findViewById(R.id.root_rl);

        headerIv = (ImageView)findViewById(R.id.header_iv);
        title_tv = (TextView) findViewById(R.id.title_tv);
        type_tv = (TextView) findViewById(R.id.type_tv);

        ivBottom = (ImageView) findViewById(R.id.player2_iv_bottom);
        viewPager = (ViewPager) findViewById(R.id.player2_vp);
        viewPager.measure(0,0);
        /*设置禁止全屏显示*/
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        //如果为空则设置它的宽高,为ViewPager中间的page显示屏幕宽度的3/5
        int width = (int) (getResources().getDisplayMetrics().widthPixels*3.0/5);
        int height =(int)(getResources().getDisplayMetrics().heightPixels*6.0/8);
        if(layoutParams==null){

            layoutParams = new ViewGroup.LayoutParams(width, height);

        }else {
            layoutParams.width=width;
            layoutParams.height = height;
        }
        viewPager.setLayoutParams(layoutParams);
        //设置页面的修改方案
        //设置为true表示是按照图片的顺序来显示
        viewPager.setPageTransformer(true,new KaoLaPageTransformer());
        viewPager.setOffscreenPageLimit(2);//缓存
        //设置间隔
        viewPager.setPageMargin(50);
        requestList();

    }
    /*获取播放列表*/
    private void requestList(){

        adapter = new Player2PagerAdapter(this,list);
        viewPager.setAdapter(adapter);
        OtherHttpUtils.getPlayerList(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {
                try {
                    JSONObject root = new JSONObject(object.toString());
                    String message  = root.getString("message");
                    if (Contants.flag_result_success.equals(message)) {
                        JSONObject result = root.getJSONObject("result");
                        JSONArray dataList = result.getJSONArray("dataList");
                        Log.e("msgPlayer2",dataList.toString());
                        final List<PlayEntity> entityList =PlayEntity.arrayPlayEntityFromData(dataList.toString());
                        //没想到这样也可以
                        list.addAll(entityList);
                        adapter.notifyDataSetChanged();
                        //这里只会调用他的实instantiateItem和destroyItem方法
                        viewPager.setCurrentItem(1);
                        //下载背景图片以及头像图片（两者是一样的）
                        final String url = entityList.get(0).getPic();
                        //第一次加载时显示的图片
                        title_tv.setText(entityList.get(0).getName());

                        new Thread(){
                            @Override
                            public void run() {
                                HttpUtils.downLoadEverything(url, FileUtil.dir_image, FileUtil.getFileNameByHashCode(url), new KaoLaTask.IDownlaodListener() {
                                    @Override
                                    public void upgradeProgress(float progress) {

                                    }

                                    @Override
                                    public void onCompleted(File file) {
                                        tempBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Bitmap backgroud = tempBitmap;
                                                //模糊图片

                                                FastBlur.blur(Player2Activity.this,backgroud,ivBottom);

                                                setHeaderIvImage(tempBitmap);
                                            }
                                        });
                                    }

                                    @Override
                                    public void error(String msg) {

                                    }

                                    @Override
                                    public void start() {

                                    }
                                });

                            }
                        }.start();

                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                final String tempUrl = entityList.get(position).getPic();
                                title_tv.setText(entityList.get(position).getName());

                                new Thread(){
                                    @Override
                                    public void run() {
                                        HttpUtils.downLoadEverything(tempUrl, FileUtil.dir_image, FileUtil.getFileNameByHashCode(tempUrl), new KaoLaTask.IDownlaodListener() {
                                            @Override
                                            public void upgradeProgress(float progress) {

                                            }

                                            @Override
                                            public void onCompleted(File file) {
                                                tempBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Bitmap backgroud = tempBitmap;
                                                        FastBlur.blur(Player2Activity.this,backgroud,ivBottom);
                                                        //设置头像
                                                        setHeaderIvImage(tempBitmap);
                                                    }
                                                });
                                            }

                                            @Override
                                            public void error(String msg) {

                                            }

                                            @Override
                                            public void start() {

                                            }
                                        });
                                    }
                                }.start();

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {

            }
        });
    }

    private void setHeaderIvImage(Bitmap tempBitmap) {

        Bitmap header = tempBitmap;
        int width = 50;
        int height =50;
        float sx=1.0f,sy = 1.0f;//设置缩放效果的比例
        int tWidth = tempBitmap.getWidth();
        int tHeight = tempBitmap.getHeight();
        Log.e("WH","1."+width+"2."+height+"3."+tWidth+"4."+tHeight);
        //分别设置图片的缩放比例
        sx = sx*width/tWidth;
        sy= sy*height/tHeight;
        //设置缩放的比例
        Matrix matrix = new Matrix();
        matrix.setScale(sx,sy);
        final Bitmap target = Bitmap.createBitmap(header,0,0,tWidth,tHeight,matrix,true);
        headerIv.setImageBitmap(target);
    }

}
