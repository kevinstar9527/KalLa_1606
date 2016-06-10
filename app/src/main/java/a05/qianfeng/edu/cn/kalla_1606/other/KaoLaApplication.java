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

    }
}
