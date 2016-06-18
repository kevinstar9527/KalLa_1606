package a05.qianfeng.edu.cn.kalla_1606.other.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.HttpUtils;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CommonImageAdapter extends PagerAdapter {

    private List<ImageView> viewList;
    //传入图片的地址
    private List<String> urlList;


    public CommonImageAdapter(){}
    public CommonImageAdapter(List<ImageView>viewList,List<String>urlList){

        this.viewList = viewList;
        this.urlList = urlList;

    }


    @Override
    public int getCount() {
        //如果不存在返回0
        return viewList==null?0:viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //还不懂啥意思
         return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        final ImageView imageView = viewList.get(position);
        container.addView(imageView);
        final String url = urlList.get(position);
        new Thread() {
            @Override
            public void run() {

                HttpUtils.downLoadEverything(url, FileUtil.dir_image, FileUtil.getFileNameByHashCode(url), new KaoLaTask.IDownlaodListener() {
                    @Override
                    public void upgradeProgress(float progress) {

                    }

                    @Override
                    public void onCompleted(File file) {
                        Bitmap tempBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        int width = tempBitmap.getWidth();
                        int height = tempBitmap.getHeight();
                        final Bitmap target = Bitmap.createBitmap(tempBitmap, 0, height / 3, width, height *2/ 3);
                        container.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(target);
                            }
                        });
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    public void start() {

                    }
                });
            }
        }.start();

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView = viewList.get(position);
        container.removeView(imageView);
    }
}

