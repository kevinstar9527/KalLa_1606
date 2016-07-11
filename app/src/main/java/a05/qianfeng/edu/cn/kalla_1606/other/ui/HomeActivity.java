package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.ui.DiscoverFragment;
import a05.qianfeng.edu.cn.kalla_1606.download.ui.DownLoadOfflineFragment;
import a05.qianfeng.edu.cn.kalla_1606.mine.ui.MyRadioFragment;
import a05.qianfeng.edu.cn.kalla_1606.other.Encode;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.HttpUtils;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.KaoLaoSildePanelLayout;

/**
 * Created by Administrator on 2016/6/6.
 */
public class HomeActivity extends AppCompatActivity {


    private RadioGroup group;

    private Button exitBtn;
    private Button cancelBtn;
    private Button smallestBtn;
    private Fragment[] fragments;
    private KaoLaoSildePanelLayout drawLayout;
    private NavigationView navigationView;
    private MyRadioFragment fragment = new MyRadioFragment();
    int lastPosition;
    //判断NavigationView是否可滑动的第二个参数
    private boolean judge2 = true;
    //判断
    float mHeight = 0;
    float mMaxHeight= 0;

    public float getmHeight() {
        return mHeight;
    }

    public void setmHeight(float mHeight) {
        this.mHeight = mHeight;
    }

    public float getmMaxHeight() {
        return mMaxHeight;
    }

    public void setmMaxHeight(float mMaxHeight) {
        this.mMaxHeight = mMaxHeight;
    }

    public boolean isJudge2() {
        return judge2;
    }

    public void setJudge2(boolean judge2) {
        this.judge2 = judge2;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        navigationView = (NavigationView) findViewById(R.id.home_nv);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()){
                    case R.id.shake:
                        intent = new Intent(HomeActivity.this,ShakeActivity.class);
                        break;
                    case R.id.camera:
                        intent = new Intent(HomeActivity.this,CamderActivity.class);
                        break;
                    case R.id.scan:
                        intent = new Intent(HomeActivity.this,ScanQrActivity.class);
                        break;
                    case R.id.encode:
                        intent = new Intent(HomeActivity.this, Encode.class);
                        break;
                }
                startActivity(intent);
                //跳转的同时关闭侧边栏
                drawLayout.closePane();
                return false;
            }
        });

        fragments = new Fragment[]{
                new DiscoverFragment(),
                fragment,
                new DownLoadOfflineFragment()
        };

        //获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开始事物
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            //先添加再隐藏
            fragmentTransaction.add(R.id.home_fragment_container,fragments[i]);
            fragmentTransaction.hide(fragments[i]);
        }
         //先添加再隐藏
        //默认显示第一个
         fragmentTransaction.show(fragments[0]);
        lastPosition=0;
        //提交事物
        fragmentTransaction.commit();



    }

    private void initView() {

        drawLayout = (KaoLaoSildePanelLayout)findViewById(R.id.home_left);
        drawLayout.setIntercept(changeListener);

        group = (RadioGroup) findViewById(R.id.bar);

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = 0;
                Log.e("mine","checkedId="+checkedId);
                switch (checkedId) {
                    case R.id.btn_discover:
                        //点击跳转到发现页面
                       index = 0;
                        break;
                    case R.id.btn_mine:
                        Log.e("mine","别跟我说这里没执行");
                        //点击跳转到我的电台页面
                      index = 1;
                        break;

                    case R.id.btn_offline:
                        index = 2;
                        break;
                    default:
                        index=0;
                        break;

                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(fragments[lastPosition]);
                fragmentTransaction.show(fragments[index]);
                fragmentTransaction.commit();
                lastPosition = index;
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showExitFMAlert();
        }
//
//            //弹出确定退出对话框
//            new AlertDialog.Builder(this)
//                    .setTitle("退出")
//                    .setMessage("确定退出吗？")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // TODO Auto-generated method stub
//                            Intent exit = new Intent(Intent.ACTION_MAIN);
//                            exit.addCategory(Intent.CATEGORY_HOME);
//                            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(exit);
//                            System.exit(0);
//                        }
//                    })
//                    .setNeutralButton("最小化", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent();
//                            intent.setAction("android.intent.action.MAIN");
//                            intent.addCategory("android.intent.category.HOME");
//                            startActivity(intent);
//                        }
//                    })
//                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // TODO Auto-generated method stub
//                            dialog.cancel();
//                        }
//                    })
//                    .show();
//            //这里不需要执行父类的点击事件，所以直接return
//            return true;
//        }
//        //继续执行父类的其他点击事件

        return super.onKeyDown(keyCode, event);
    }

    private void showExitFMAlert() {
        final AlertDialog exitDialog = new AlertDialog.Builder(this).create();
        exitDialog.show();
        Window window = exitDialog.getWindow();
        //实现对话框中的效果
        //设置窗口内容页面,定义view中的内容
        window.setContentView(R.layout.show_exit_dialog);
        exitBtn = (Button) window.findViewById(R.id.exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exit = new Intent(Intent.ACTION_MAIN);
                exit.addCategory(Intent.CATEGORY_HOME);
                exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(exit);
                System.exit(0);
            }
        });
        //关闭alter对话框
        cancelBtn = (Button) window.findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitDialog.cancel();
            }
        });

        //最小化
        smallestBtn = (Button) window.findViewById(R.id.smallest);
        smallestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                startActivity(intent);
            }
        });
        //setClickEffect();
    }
    private int discoverPagerIndex;
    public void setDiscoverPagerIndex(int index){
        discoverPagerIndex = index;
    }
    private KaoLaoSildePanelLayout.IIntercept changeListener = new KaoLaoSildePanelLayout.IIntercept() {
        @Override
        public boolean needIntercepet() {
            if (discoverPagerIndex==0&&judge2){
                return judge2;
            }
            return false;
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //计算手指点击的位置
       // float x = ev.getX();
        float y = ev.getY();


        if ((y>=mHeight)&&(y<=mMaxHeight)){

            setJudge2(false);
        }else{
            setJudge2(true);
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
        if (resultCode==2){
            Log.e("resultCode","是几个");
            Log.e("resultCode"," + "+requestCode);//?65537
            if (true){

                Log.e("resultCode","是几个呢");

                //取到从登陆界面传递过来的数据
               // Bitmap bitmap = result.getParcelableExtra("data");
                if (requestCode==1){


               // String name = result.getParcelableExtra("result");
                String demo =result.getStringExtra("msg");
              //  String demo = getIntent().getStringExtra("msg");

                Log.e("datas","getExtra - "+demo);
               // Log.e("datas",name);
                //解析出Myradio界面需要的信息
                        try {
                            JSONObject root = new JSONObject(demo);

                            String nickName = root.getString("nickname");
                            fragment.setNickname(nickName);
                            final String photoHead = root.getString("figureurl_qq_2");
                            //下载头像
                            new KaoLaTask(new KaoLaTask.IRequest() {
                                @Override
                                public Object doRequest() {

                                    File file = HttpUtils.downLoadEverything(photoHead, FileUtil.dir_image, FileUtil.getFileNameByHashCode(photoHead),null);
                                    return file;
                                }
                            }, new KaoLaTask.IRequestCallBack() {
                                @Override
                                public void success(Object object) {
                                    File file = (File) object;
                                    Bitmap head = BitmapFactory.decodeFile(file.getAbsolutePath());
                                    Drawable drawable = new BitmapDrawable(getResources(),head);
                                    fragment.getLogin().setBackground(drawable);
                                    ImageLoader.getInstance().displayImage(photoHead,fragment.getMiddle_photo(), ImageUtil.getCircleOption());
                                  //  fragment.getMiddle_photo().setImageBitmap(header);
                                }

                                @Override
                                public void error(String msg) {

                                }
                            }).execute();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
            }

            }
        }
    }
}

