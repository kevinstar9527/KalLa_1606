package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 *
 * 文件工具类
 * Created by Administrator on 2016/6/7.
 */
public class FileUtil {
    /*应用目录下的image目录*/
public  static final File dir_image = getDir("image");
    public static final File dir_apk = getDir("apk");
    public static final File dir_cache = getDir("cache");


    public static File getSDcardDir(){

        //获取一下sd卡挂载状态
        String state = Environment.getExternalStorageState();

        //如果是已经下载
        if(state.equals(Environment.MEDIA_MOUNTED)){
            File directory = Environment.getExternalStorageDirectory();
            return directory;
        }else{
            LogUtil.e("没有SD卡");
        }
        return null;
    }

    /*获取应用目录*/
    public static File getAppDir(){

        File dir = new File(getSDcardDir(),"KalLa_1606");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }
/**/
    private static File getDir(String dirName){

        File dir = new File(getAppDir(),dirName);

        if(!dir.exists()){
            dir.mkdirs();
        }

        return dir;

    }
    /*用url转换成hashcode作为存储的文件名
    * url,
    * */

    public static String getFileNameByHashCode(String url){
        return ""+url.hashCode();
    }

    /**
     * 安装apk
     *
     * @param context
     * @param file
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        /*解包*/
        String type = "application/vnd.android.package-archive";
        intent.setDataAndType(Uri.fromFile(file), type);
        context.startActivity(intent);
    }

}
