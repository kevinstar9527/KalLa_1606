package a05.qianfeng.edu.cn.kalla_1606.other;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 在程序启动时最开始做的操作
 * Created by Administrator on 2016/6/8.
 */
public class KaoLaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /*ImageLoader初始化*/
        //因为ImageLoader需要在运用时先初始化，所以需要在Application类中初始化
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
       // OkHttpClient hello = OkHttpUtil.getInstance();
//        Picasso picasso = new Picasso.Builder(this)
//                .memoryCache(new LruCache(30<<20))//请求内存中的内存30*1024*1024请求30M空间
//                .downloader(new OkHttpDownloader())//磁盘缓存
//                .defaultBitmapConfig(Bitmap.Config.RGB_565)
//                .build();
//        Picasso.setSingletonInstance(picasso);//设置单利模式
    }
}
