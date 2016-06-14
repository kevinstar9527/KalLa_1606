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
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.RadioInner;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/12.
 */
public class ZhiNengJXAdapter extends RecyclerView.Adapter<ZhiNengJXAdapter.ZhiNengJXViewHolder>{

    List<RadioInner> list;
    LayoutInflater inflater;
    public ZhiNengJXAdapter(Context context,  List<RadioInner> list) {

        this.list = list;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public ZhiNengJXAdapter.ZhiNengJXViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.fragment_discover_radio_zhinengjingxuanitem,null);


        return new ZhiNengJXViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ZhiNengJXViewHolder holder, int position) {
        //图片绑定
        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.znIV, ImageUtil.getCircleOption());
        //内容绑定
        holder.title.setText(list.get(position).getRname());
        holder.content.setText(list.get(position).getDes());
        holder.detial.setText(String.valueOf(list.get(position).getListenNum()));
        holder.addToday.setText(String.valueOf(list.get(position).getFollowedNum()));


    }


    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public static class ZhiNengJXViewHolder extends RecyclerView.ViewHolder{

        private final ImageView znIV;
        private final TextView title;
        private final TextView content;
        private final TextView detial;
        private final TextView addToday;

        public ZhiNengJXViewHolder(View itemView) {
            super(itemView);

            znIV = (ImageView) itemView.findViewById(R.id.zn_iv);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            detial = (TextView) itemView.findViewById(R.id.detail);
            addToday = (TextView) itemView.findViewById(R.id.addTomorrow);


        }
    }

}
