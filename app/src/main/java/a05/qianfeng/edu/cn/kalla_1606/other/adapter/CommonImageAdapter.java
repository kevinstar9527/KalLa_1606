package a05.qianfeng.edu.cn.kalla_1606.other.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;

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
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = viewList.get(position);
        container.addView(imageView);

        ImageLoader imageLoader =  ImageLoader.getInstance();
        imageLoader.displayImage(urlList.get(position),imageView,ImageUtil.getDefaultOption());

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imageView = viewList.get(position);
        container.removeView(imageView);
    }
}

