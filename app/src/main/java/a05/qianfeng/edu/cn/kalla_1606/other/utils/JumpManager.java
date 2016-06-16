package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.content.Context;
import android.content.Intent;

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
    public static void jumpToPlayer1(Context context, String mp3url){
        Intent intent = new Intent(context, a05.qianfeng.edu.cn.kalla_1606.discover.ui.Player1Activity.class);
        intent.putExtra(TAG_MP3_URL,mp3url);
        context.startActivity(intent);
    }
}
