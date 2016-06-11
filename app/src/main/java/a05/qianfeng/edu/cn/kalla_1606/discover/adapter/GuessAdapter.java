package a05.qianfeng.edu.cn.kalla_1606.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;

/**
 * Created by Administrator on 2016/6/11.
 */
public class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.GuessViewHolder> {

    private List<Special>list;
    private LayoutInflater inflater;


    public GuessAdapter(Context context,List<Special>list){
        this.list = list;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public GuessAdapter.GuessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.widget_guess_item,null);
        return new GuessViewHolder(view);

    }

    @Override
    public void onBindViewHolder(GuessAdapter.GuessViewHolder holder, int position) {
        /*绑定数据*/
        ImageLoader.getInstance().displayImage(list.get(position).getPic(),holder.guessIv, ImageUtil.getDefaultOption());
        Log.e("GuessPanel:","这里执行了");
        holder.title.setText(list.get(position).getAlbumName());
        holder.content.setText(list.get(position).getTip());
        holder.reason.setText(list.get(position).getDes());

    }
    /*这里可不能忘了*/
    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    class GuessViewHolder extends RecyclerView.ViewHolder{
        /*在这里绑定控件*/
        private  TextView title;
        private  TextView content;
        private  TextView reason;
        private  ImageView guessIv;

        public GuessViewHolder(View itemView) {
            super(itemView);

            guessIv = (ImageView) itemView.findViewById(R.id.guess_iv);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.content);
            reason = (TextView) itemView.findViewById(R.id.reason);
        }
    }

}
