package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *
 * 用户okHttp封装网络请求
 * Created by Administrator on 2016/6/14.
 */
public class OkHttpUtil {
    /*用单例模式封装OkHttpClient*/
    private static OkHttpClient okHttpClients = null;
    public static OkHttpClient getInstance(){
      if(okHttpClients==null){
          synchronized (OkHttpUtil.class){
              if (okHttpClients==null){
                  okHttpClients = new OkHttpClient();
                  return okHttpClients;
              }
          }
          return okHttpClients;
      }
        return okHttpClients;
    }
    /*
    * GET请求
    *
    *
    * */

    public void doGet(String url, final KaoLaTask.IRequestCallBack callBackKaoLao){
         getInstance();
        final Request request = new Request.Builder()
                .get()//get请求
                .url(url)
                .build();
        Call call = okHttpClients.newCall(request);
        /*不推荐这个*/
//        try {
//            call.execute();//另起一个子线程执行
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //放在线程队列中去执行,在子线程中执行
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                if (callBackKaoLao==null){
                    callBackKaoLao.error(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                //获取字符流
                Reader reader = body.charStream();
                BufferedReader bufferedReader = new BufferedReader(reader);
                String read = null;
                StringBuffer resultBuffer = new StringBuffer();
                while (( read=bufferedReader.readLine())!=null){
                    resultBuffer.append(read);
                }
                String result = resultBuffer.toString();
                LogUtil.e("请求成功 result:"+result);
                if (callBackKaoLao!=null) {
                    callBackKaoLao.success(request);
                }
            }
        });
    }
        public static void doPost(String url, RequestBody bodyCanShu,final KaoLaTask.IRequestCallBack callBackKaoLao){

           getInstance();
           final Request request = new Request.Builder()
                    .post(bodyCanShu)//写入参数
                    .url(url)
                    .build();
            Call call = okHttpClients.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (callBackKaoLao==null){
                        callBackKaoLao.error(e.getMessage());
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ResponseBody body = response.body();
                    //获取字符流
                    Reader reader = body.charStream();
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String read = null;
                    StringBuffer resultBuffer = new StringBuffer();
                    while ((read = bufferedReader.readLine()) != null) {
                        resultBuffer.append(read);
                    }
                    String result = resultBuffer.toString();
                    LogUtil.e("请求成功 result:" + result);
                    if (callBackKaoLao != null) {
                        callBackKaoLao.success(request);
                    }
                }
            });

        }
}
