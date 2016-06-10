package a05.qianfeng.edu.cn.kalla_1606.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;

/**
 * 1.命名一个类，先别忙着继承
 *
 * 2.写一个局部内部类继承RecyclerView.ViewHolder，在构造方法里面给控件初始化
 *
 * 快捷入口的adapter
 *
 * 3.adapter继承recycle.adapter
 *
 * 4.实现三个抽象方法
 * created by Administrator on 2016/6/8.
 */
public class EnterAdpater extends  RecyclerView.Adapter<EnterAdpater.EnterViewHoder>{

    public EnterAdpater(Context context, List<Special> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    private List<Special> list;
    private LayoutInflater inflater;

    @Override
    public EnterAdpater.EnterViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_enter_item,null);
        return new EnterViewHoder(view);
    }

    @Override
    public void onBindViewHolder(EnterAdpater.EnterViewHoder holder, int position) {

        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.imageView, ImageUtil.getDefaultOption());


    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class EnterViewHoder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public EnterViewHoder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.adapter_enter_iv);
        }
    }

}