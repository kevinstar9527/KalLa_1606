package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.os.AsyncTask;

import java.io.File;

/**
 *
 * 请求任务（订单）
 * Created by Administrator on 2016/6/7.
 */
public class KaoLaTask extends AsyncTask<Void,Void,Object> {
    private IRequest request;
    private IRequestCallBack callBack;
    private IDownlaodListener listener;
    private IDownLoadRequest requestt;
    public KaoLaTask(IRequest request,IRequestCallBack callBack){
      if(request==null||callBack==null) {
        throw new NullPointerException("IRequest or IRequestCallBack can not be null !!!");
      }
        this.request = request;
        this.callBack = callBack;
    }

    /*下面这个方法用来处理。。。*/
    public KaoLaTask(IDownLoadRequest requestT,IDownlaodListener listener){

        if (requestT==null||listener==null){

            throw new NullPointerException("IDownLoadRequest or IDownLoader can not be null!!");
        }
        this.requestt = requestT;
        this.listener = listener;
    };


    @Override
    protected Object doInBackground(Void... params) {

        if(request==null){
            return requestt.doRequest(listener);
        }

        return request.doRequest();
    }

    @Override
    protected void onPostExecute(Object result) {

        if(callBack==null){
            return;
        }

        if(result==null){
            callBack.error("请求失败了");
        }else{
            callBack.success(result);
        }
    }

    /*
           * 请求接口
        * */
    public interface IRequest{
        Object doRequest();
    }
    /*请求结果回调(收货人信息)*/
    public interface IRequestCallBack{
        /*
        * 成功回调 object 请求结果
        * */
        void success(Object object);
        /*
        * 错误回调
        * msg 错误信息
        * */
        void error(String msg);
    }

    public interface IDownLoadRequest{
        /*
        * 执行网络请求
        * */
        Object doRequest(IDownlaodListener listener);
    }

    /*
    * 下载监听
    * */
    public interface  IDownlaodListener{
        /*更新进度*/
        void upgradeProgress(float progress);
//      下载完成
        void onCompleted(File file);
        //下载失败
        void error(String msg);
        //开始下载
        void start();
    }
}
