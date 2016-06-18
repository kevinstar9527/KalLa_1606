package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.content.Context;
import android.content.Intent;

import a05.qianfeng.edu.cn.kalla_1606.discover.ui.Player2Activity;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.WebActivity;

/**
 * Created by Administrator on 2016/6/15.
 */
public class JumpManager {
    public static final String TAG_URL = "url";
    public static final String TAG_MP3_URL = "mp3Url";

    public static void jumpToWeb(Context context, String url){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(TAG_URL,url);
        context.startActivity(intent);
    }
    //跳转到第一个播放界面
    public static void jumpToPlayer1(Context context, String mp3url){
        Intent intent = new Intent(context, a05.qianfeng.edu.cn.kalla_1606.discover.ui.Player1Activity.class);
        intent.putExtra(TAG_MP3_URL,mp3url);
        context.startActivity(intent);
    }
    //跳转到第二个播放界面(推荐页面所以不需要传数据)
    public static void jumpToPlayer2(Context context){
        Intent intent = new Intent(context,Player2Activity.class);
        context.startActivity(intent);
    }
}
