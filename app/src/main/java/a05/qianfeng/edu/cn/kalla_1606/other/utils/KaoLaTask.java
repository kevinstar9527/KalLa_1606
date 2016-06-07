package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.os.AsyncTask;

/**
 *
 * 请求任务（订单）
 * Created by Administrator on 2016/6/7.
 */
public class KaoLaTask extends AsyncTask<Void,Void,Object> {
    private IRequest request;
    private IRequestCallBack callBack;

    public KaoLaTask(IRequest request,IRequestCallBack callBack){
      if(request==null||callBack==null) {
        throw new NullPointerException("IRequest or IRequestCallBack can not be null !!!");
      }
        this.request = request;
        this.callBack = callBack;
    }

    @Override
    protected Object doInBackground(Void... params) {


        return request.doRequest();
    }

    @Override
    protected void onPostExecute(Object o) {

        if(request==null){
            callBack.error("请求失败了");
        }else{
            callBack.success(request);
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
}
