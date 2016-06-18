package a05.qianfeng.edu.cn.kalla_1606.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.RadioInnerContent;

/**
 * Created by Administrator on 2016/6/14.
 */
public class TeleversionAdapter extends RecyclerView.Adapter<TeleversionAdapter.TeleversionViewHolder>{
    private  int width;
    /*电台下面的选项*/
    LayoutInflater inflater;
    List<RadioInnerContent> innerData = null;

   // List<RadioInnerContent> tempData = null;

    private boolean scale = true;

    public List<RadioInnerContent> getInnerData() {
        return innerData;
    }

    public void setInnerData(List<RadioInnerContent> innerData) {
        this.innerData = innerData;
    }

    public boolean isScale() {
        return scale;

    }

    public void setScale(boolean scale) {
        this.scale = scale;
    }

    /*声明点击事件的接口变量*/
    private TeleversionAdapter.OnRecycleViewItemClickListener mOnRecycleViewItemClickListener;

    public TeleversionAdapter(Context context, List<RadioInnerContent>radioInners) {
        /*初始化数据与初始化填充器*/
        inflater = LayoutInflater.from(context);
        innerData =radioInners;
        width = context.getResources().getDisplayMetrics().widthPixels;


    }

    @Override
    public TeleversionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.radio_televison_item,null);

        return new TeleversionAdapter.TeleversionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeleversionViewHolder holder, final int position) {
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mOnRecycleViewItemClickListener.onItemClick(position);
              }
          });
        if(position == innerData.size()){
            holder.content.setVisibility(View.GONE);
            holder.btnIv.setVisibility(View.VISIBLE);
            if(position > 7){
                holder.btnIv.setImageResource(R.drawable.category_up);
            }else{
                holder.btnIv.setImageResource(R.drawable.category_down);
            }
            return;
        }
        holder.content.setVisibility(View.VISIBLE);
        holder.btnIv.setVisibility(View.GONE);
        holder.content.setText(innerData.get(position).getName());

    }

    @Override
    public int getItemCount() {

        return innerData==null?0:(innerData.size()+1);
    }


    public  class TeleversionViewHolder extends RecyclerView.ViewHolder

    {

        private final TextView content;
        private final ImageView btnIv;

        public TeleversionViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
            btnIv = (ImageView) itemView.findViewById(R.id.imageButton);
            //动态设置文字内容的高度
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width/4,width/4);
            content.setLayoutParams(params);
            btnIv.setLayoutParams(params);

        }
    }

    /*设置点击的监听事件*/
    /*
    * 1.首先定义一个接口
    *
    * */
    public void setOnRecycleViewItemClickListener(TeleversionAdapter.OnRecycleViewItemClickListener listener){
        this.mOnRecycleViewItemClickListener = listener;
    }
    public  interface OnRecycleViewItemClickListener{
        public void onItemClick(int position);
    }



}
