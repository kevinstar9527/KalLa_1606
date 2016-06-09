package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by Administrator on 2016/6/8.
 */
public class ImageUtil {

    private ImageView imageView;

    public ImageView getDefaultImage(){

        return  imageView;
    }

    /*
    *
    *
    * */

    public static DisplayImageOptions getCircleOption(){


        //建造者模式
        DisplayImageOptions options =  new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)//图片质量
                .cacheInMemory(true)//添加到缓存里面
                .cacheOnDisk(true)//下载到SD卡
                .build();//构建
        ;
        return options;

    }

    /*
    *   默认的图片配置
    * */

    public static DisplayImageOptions getDefaultOption() {

       //建造者模式
        DisplayImageOptions options =  new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)//图片质量
                .cacheInMemory(true)//添加到缓存里面
                .cacheOnDisk(true)//下载到SD卡
                .build();//构建
       ;
        return options;
    }
}
