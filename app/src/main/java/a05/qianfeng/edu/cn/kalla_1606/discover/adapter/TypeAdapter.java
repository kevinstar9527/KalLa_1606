package a05.qianfeng.edu.cn.kalla_1606.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Type;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;

/**
 *
 * 分类下面的表格
 * Created by Administrator on 2016/6/12.
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> implements View.OnClickListener {

    private List<Type>list;
    private LayoutInflater inflater;
    /*声明点击事件接口变量*/
    private OnRecycleViewItemClickListener mOnItemClickListener = null;

    public TypeAdapter(Context context, List<Type> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
        /*第二种初始化方式*/
       // inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapter_type_grid,null);
       /*将创建的Viewz注册点击事件*/
        view.setOnClickListener(this);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TypeViewHolder holder, int position) {
            Type type = list.get(position);
        holder.tvType.setText(type.getTitle());
        ImageLoader.getInstance().displayImage(list.get(position).getIcon(),holder.ivIcon, ImageUtil.getDefaultOption());
        //将数据保存在itemView的Tag中以便点击时获取
        holder.itemView.setTag(type.getTitle());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!=null){
            /*这里使用getTage方法获取数据*/
            mOnItemClickListener.onItemClick(v,(String) v.getTag());
        }
    }

    public  static class TypeViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        TextView tvType;
        public TypeViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView)itemView.findViewById(R.id.type_grid_icon_iv);
            tvType = (TextView)itemView.findViewById(R.id.type_grid_icon_tv);

        }
    }

    /*定义点击事件接口*/
    public static interface OnRecycleViewItemClickListener{

         void onItemClick(View view,String data);
    }
    public void setOnItemClickListener(OnRecycleViewItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;

    }

}
