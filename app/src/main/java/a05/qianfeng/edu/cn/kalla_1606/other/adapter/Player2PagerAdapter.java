package a05.qianfeng.edu.cn.kalla_1606.other.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.bean.PlayEntity;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.HttpUtils;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;

/**
 * Created by Liu Jianping
 *
 * @date : 16/6/16.
 */
public class Player2PagerAdapter extends PagerAdapter
{
    private List<PlayEntity> list;

    private LayoutInflater inflater;
    private List<StringBuffer> urlList;

    private List<View> viewList = new ArrayList<>();
    private ImageView headerIv;
    private TextView title_tv;
    private TextView type_tv;
    private Bitmap mBitamp;
    private ImageView imageView;
    private ImageView ringhtBtn;

    public Player2PagerAdapter(Context context, List<PlayEntity> list)
    {
        this.list = list;
        inflater = LayoutInflater.from(context);

        for (int i = 0; i < list.size(); i++)
        {
            View contentView = inflater.inflate(R.layout.layout_palyer, null);
            viewList.add(contentView);
        }
    }

    @Override
    public int getCount()
    {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        if (viewList.size() == 0)
        {
            return null;
        }
        //获得需要注册监听器的控件
        ringhtBtn = (ImageView)viewList.get(position).findViewById(R.id.player_next_btn);
        ringhtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取到playUrl
                final String url = list.get(position).getPlayUrl();
                //下载url中对应的数据后得到一个文件
                KaoLaTask.IDownLoadRequest downLoadRequest = new KaoLaTask.IDownLoadRequest() {
                   @Override
                   public Object doRequest(KaoLaTask.IDownlaodListener listener) {

                       HttpUtils.downLoadEverything(url,FileUtil.dir_cache,FileUtil.getFileNameByHashCode(url),listener);
                      // getMp3Url(url, position);
                       return null;}
               };
                KaoLaTask.IDownlaodListener listener = new KaoLaTask.IDownlaodListener() {
                    @Override
                    public void upgradeProgress(float progress) {

                    }

                    @Override
                    public void onCompleted(File file) {
                        getMp3Url(file,url,position);
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    public void start() {

                    }
                };

                new KaoLaTask(downLoadRequest,listener).execute();
            }
        });

        final String url = list.get(position).getPic();
        setBitmapsToImageView(container, position, url);
        View view = viewList.get(position);
        container.addView(view);
        return view;
    }

    private void getMp3Url(File file,String url, int position) {
        try {
            InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
            //将字节流转换为字符流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String read = null;
            String httpUrlTmep = null;
            while((read=bufferedReader.readLine())!=null){

                if (read.contains("http")){
                    httpUrlTmep = read;
                    Log.e("NextMusic:",read);
                    break;
                }
            }

            //
            //得到最后一个/的索引
          int targetIndex = httpUrlTmep.lastIndexOf("/")+1;

            //根据得到的索引切分数组
            //切分后的字符串转换为StringBuffer以便之后的拼接字符串
          StringBuffer target = new StringBuffer(httpUrlTmep.substring(0,targetIndex));
            Log.e("NextMusic:"," Target"+target.toString()+"||"+httpUrlTmep);
            //请求httpUrlTmep中的文件
            File file2 = HttpUtils.downLoadEverything(httpUrlTmep, FileUtil.dir_cache,FileUtil.getFileNameByHashCode(httpUrlTmep),null);
            //得到文件2后继续读取文件，并将其转换为数组，步骤与上面类似
            InputStream inputStream2 = new FileInputStream(file2.getAbsoluteFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream2));
            //得到文件中的各个后缀
            String profix = null;
            List<String> profixs = new ArrayList<String>();
            while ((profix=reader.readLine())!=null){
               // Log.e("profix:"," "+profix);
                if(profix.contains(".ts")){
                    profixs.add(profix);
                    Log.e("profix:"," "+profix);
                }
            }
            //在读取完后缀之后将他们分别拼接起来
            List<StringBuffer>tartgetUrls = new ArrayList<StringBuffer>();

            for (int i = 0; i < profixs.size(); i++) {
                StringBuffer tempUrl = new StringBuffer();
                tempUrl.append(target.toString());
                tempUrl.append(profixs.get(i).toString());
                tartgetUrls.add(tempUrl);
                Log.e("HttpUrl:",tartgetUrls.get(i).toString());
            }
            urlList = tartgetUrls;
            //再之后再分别下载



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBitmapsToImageView(final ViewGroup container, final int position, final String url) {
        new Thread(){
            @Override
            public void run() {
                HttpUtils.downLoadEverything(url, FileUtil.dir_image, FileUtil.getFileNameByHashCode(url), new KaoLaTask.IDownlaodListener() {
                    @Override
                    public void upgradeProgress(float progress) {

                    }

                    @Override
                    public void onCompleted(File file) {
                        //裁剪图片
                        final ImageView imageView =  (ImageView)viewList.get(position).findViewById(R.id.player1_iv);
                        Bitmap tempBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        int width = 250;
                        int height =250;
                        float sx=1.0f,sy = 1.0f;//设置缩放效果的比例
                        int tWidth = tempBitmap.getWidth();
                        int tHeight = tempBitmap.getHeight();
                        Log.e("WH","1."+width+"2."+height+"3."+tWidth+"4."+tHeight);
                        //分别设置图片的缩放比例
                         sx = 1.0f*width/tWidth;
                         sy= 1.0f*height/tHeight;
                        //设置缩放的比例
                        Matrix matrix = new Matrix();
                        matrix.setScale(sx,sy);
                        final Bitmap target = Bitmap.createBitmap(tempBitmap,0,0,tWidth,tHeight,matrix,true);
                        container.post(new Runnable() {
                            @Override
                            public void run() {
                                //图片裁剪后将图片设置给目标控件
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
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        if (viewList.size() == 0)
        {
           return;
        }

        container.removeView(viewList.get(position));
    }

    @Override
    public void notifyDataSetChanged() {
        if (viewList.size() == 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                View contentView = inflater.inflate(R.layout.layout_palyer, null);
                viewList.add(contentView);
            }
        }

        super.notifyDataSetChanged();
    }
}
