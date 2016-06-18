package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.content.Intent;
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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.ui.DiscoverFragment;
import a05.qianfeng.edu.cn.kalla_1606.download.ui.DownLoadOfflineFragment;
import a05.qianfeng.edu.cn.kalla_1606.mine.ui.MyRadioFragment;
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
                }
                startActivity(intent);
                //跳转的同时关闭侧边栏
                drawLayout.closePane();
                return false;
            }
        });

        fragments = new Fragment[]{
                new DiscoverFragment(),
                new MyRadioFragment(),
                new DownLoadOfflineFragment()
        };

        //首次进入界面时，显示发现页面
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //先添加再隐藏
        fragmentTransaction.replace(R.id.home_fragment_container,fragments[0]);
        fragmentTransaction.commit();


    }

    private void initView() {

        drawLayout = (KaoLaoSildePanelLayout)findViewById(R.id.home_left);

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
                fragmentTransaction.replace(R.id.home_fragment_container,fragments[index]);
                fragmentTransaction.commit();

            }
        });
        drawLayout.setIntercept(changeListener);
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
            if (discoverPagerIndex==0){
                return true;
            }
            return false;
        }
    };

}

