package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.Contants;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.OtherHttpUtils;

public class MainActivity extends AppCompatActivity {

    private Intent intent=null;
    private String localVersion;
    private String TAG = "msg";
    private ProgressBar progr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(Contants.FLAG_FIRST_USE, Context.MODE_PRIVATE);
        boolean firstUsed = sharedPreferences.getBoolean(Contants.FLAG_FIRST_USED_VALUT, true);
        if(firstUsed){
            //如果是第一次使用那么跳转到引导页
            intent = new Intent(this,GuideActivity.class);
            startActivity(intent);
            finish();
        }else{
             //判断是否进入广告页还是进入版本提示
              compileVersion();
        }
     }

    private void compileVersion() {
         /*进入应用就先请求一下最新版本*/
        localVersion = "";
        /*获取本地管理号*/
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            localVersion = packageInfo.versionName;
            LogUtil.w("++++++++++"+ localVersion);
           /*如果清单文件里面和build.gradle里面都有版本信息，那么会以build.gradle的版本信息为准*/
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        OtherHttpUtils.getVersion(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {

                try {
                    JSONObject root = new JSONObject(object.toString());
                    String message = root.getString("message");
                    if (Contants.flag_result_success.equals(message)) {
                        JSONObject result = root.getJSONObject(Contants.flag_result_dataresult);
                        //JSONArray dataList = result.getJSONArray("dataList");
                        //获取服务器的版本奥
                        String updateVersion = result.getString("updateVersion");
                        if (localVersion.equals(updateVersion)) {
                            Log.e("msg","已经是最新版本了");
                        }else{
                            //显示更新对话框
                            Log.e(TAG, "success: 这里执行更新对话框");
                            String updatInfo = result.getString("updateInfo");//重要信息
                            String apkUrl = result.getString("updateUrl");//更新信息
                            showUpgradeDialog(updatInfo,apkUrl);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String msg) {

            }
        }, localVersion);
    }

    /*显示更新对话框*/
    private void showUpgradeDialog(String msg, final String apkUrl) {
        /*自定义对话框*/
        final Dialog dialog = new Dialog(this,R.style.dialog_upgrate);
        /*先写一个布局*/
        dialog.setContentView(R.layout.dialog_upgrade);
        TextView textView = (TextView) dialog.findViewById(R.id.msg);
        textView.setText(msg);
        progr = (ProgressBar) dialog.findViewById(R.id.progress);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        Button yes = (Button) dialog.findViewById(R.id.yes);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //如果点击取消，那么跳转到广告页
                Intent intent = new Intent(MainActivity.this,BannerActivity.class);
                startActivity(intent);
                finish();

            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OtherHttpUtils.downLoadApk(new KaoLaTask.IDownlaodListener() {
                    @Override
                    public void upgradeProgress(final float progress) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progr.setProgress((int) progress);
                            }
                        });
                    }

                    @Override
                    public void onCompleted(File file) {
                        /*下载成功之后安装应用程序*/

                        FileUtil.installApk(MainActivity.this,file);

                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    public void start() {

                    }
                },apkUrl);

            }
        });
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        /*点击dialog以外区域不让消失*/
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Log.e("msg","这里执行到了");
        dialog.show();//显示

        /*去头部设置样式*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
