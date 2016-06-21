package a05.qianfeng.edu.cn.kalla_1606.mine.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import a05.qianfeng.edu.cn.kalla_1606.R;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by Administrator on 2016/6/20.
 */
public class LogOnActivity extends AppCompatActivity {

    private final String APP_ID = "1105410075";
    private Tencent tencent;
    private String title = null;
    private Bitmap mHeadPhoto = null;

    /*回调接口*/
//验证登陆
    private IUiListener listener = new IUiListener() {
        @Override
        public void onComplete(Object o) {
            Log.e("tencnet","--onComponent+"+o.toString());
            //登陆完成后返回json字符串
            try {
                JSONObject root = new JSONObject(o.toString());

                String openId= root.getString("openid");//你在那台机子上登陆的

                String expires_in = root.getString("expires_in");//登陆有效期

                String access_token = root.getString("access_token");//登陆token

                tencent.setOpenId(openId);//重新设置一下设备id
                tencent.setAccessToken(access_token,expires_in);
                //创建并获取用户信息
                UserInfo userInfo = new UserInfo(LogOnActivity.this,tencent.getQQToken());
                userInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Log.e("info","getUserIno onComplete>>"+o.toString());
                        Intent intent = new Intent();
                        intent.putExtra("msg",o.toString());
                        setResult(2,intent);
                        finish();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e("info","getUserIno onError>>"+uiError.errorMessage);

                    }

                    @Override
                    public void onCancel() {
                        Log.e("info","getUserIno onCancel>>");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Log.e("tencnet","--onComponent "+uiError.errorMessage);

        }

        @Override
        public void onCancel() {
            Log.e("tencnet","--onComponent");

        }
    };

    private TextView qq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        //创建腾讯对象
     //   tencent = Tencent.createInstance(APP_ID,this);
        tencent = Tencent.createInstance(APP_ID, this.getApplicationContext());
        qq = (TextView) findViewById(R.id.qq);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loginqq();
                loginQQbyShare();

            }
        });
    }

    private void loginQQbyShare(){
        //调用时必须先初始化
        final Platform qq = ShareSDK.getPlatform(QQ.NAME);
        //避免重复登陆
        if (qq.isValid()) {
            qq.removeAccount();//注销登陆
            return;
        }
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e("onComplete"," "+platform.toString()+"+++_"+i);
                PlatformDb db = qq.getDb();
                Log.e("logQQ","++"+db.getToken()+",");

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("onError"," "+platform.toString()+"+++_"+i);

            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("onCancel"," "+platform.toString()+"+++_"+i);

            }
        });
        //授权,也就是发送登陆请求
        qq.authorize();

        //qq.showUser(null);
    }

    private void loginqq() {
        //判断是否已经登陆了
        if (tencent.isSessionValid()){
            //可以注销
            tencent.logout(LogOnActivity.this);
        }else {
            //登陆第二个参数表示你想要获取的权限
            tencent.login(LogOnActivity.this,"all",listener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //要使用插件必须调用这个方法
        Tencent.onActivityResultData(requestCode,resultCode,data,listener);

        //传递数据
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //结束当前activity的生命周期
    }


}
