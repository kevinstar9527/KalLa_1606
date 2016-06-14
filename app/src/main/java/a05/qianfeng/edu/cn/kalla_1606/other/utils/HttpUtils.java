package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *用HttpUrlConnection封装的网络请求
 * Created by Administrator on 2016/6/7.
 */
public class HttpUtils {



    /*Get请求*/
    public static Object doGet(String httpUrl) {

        InputStream inputStream =null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer resultBuff = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //请求方式（默认也是）
            conn.setRequestMethod("GET");
            //读取超时报错
            conn.setReadTimeout(5000);
            //连接超时
            conn.setConnectTimeout(5000);
            //建立连接
            conn.connect();
            //获取返回码



            int responseCode = conn.getResponseCode();
            //如果请求成功了
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //获取输入流
                inputStream = conn.getInputStream();
                //用来存储读取的一行字符（临时操作符）
                String readTemp = null;
                //封装流操作
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
                while ((readTemp = bufferedReader.readLine()) != null) {

                    //每读取一行把读取的内容拼接到结果里面
                    resultBuff.append(readTemp);
                }
                String result = resultBuff.toString();
                LogUtil.w("请求成功！");
                LogUtil.w("数据请求成功！返回结果" + result);
                //返回结果
                return result;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //如果inputStream不为空则其他也不为空
            if (inputStream!= null) {
                try {
                    inputStream.close();
                    inputStreamReader.close();
                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        Log.e("HttpUtils", "请求失败");
        return null;
    }

    /*Post带参数的请求*/
    public static Object doPost(String httpUrl, Map<String, String> params) {
        //先解析参数 拼接成类似于 platForm = 2&ver=v1.2.2的结果

        //先把map转换为集合（遍历MAP的一种方式）
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        //获得迭代器（可以循环的集合）
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();

        StringBuffer buffer = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            //获取键名
            String key = entry.getKey();
            buffer.append(key);
            buffer.append("=");
            //获取值
            String value = entry.getValue();
/*
            拼接value
*/
            buffer.append(value);
            buffer.append("&");
        }
        //去掉最后面的屁股&
        String paramsString = buffer.toString();
        paramsString = paramsString.substring(0, paramsString.length() - 1);

        //开始与流与网络的相关操作
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        //用来将参数以流的方式添加
        OutputStream outputStream = null;
         try {
                URL url = new URL(httpUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setConnectTimeout(5000);
                urlConnection.setReadTimeout(5000);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);//允许输出
                urlConnection.setUseCaches(true);//允许使用缓存

                urlConnection.connect();
                //稍后提取
                outputStream = urlConnection.getOutputStream();
/*              //先写入参数再获取响应代码
                用输出流写入参数
*/
                outputStream.write(paramsString.getBytes());
                outputStream.flush();

                int response = urlConnection.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {

                    inputStream = urlConnection.getInputStream();
                    inputStreamReader = new InputStreamReader(inputStream);
                    bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuffer resultBuffer = new StringBuffer();
                    String readTmepPost = null;
                    while ((readTmepPost = bufferedReader.readLine()) != null) {
                        resultBuffer.append(readTmepPost);
                    }
                    String result = resultBuffer.toString();
                    LogUtil.w("数据请求成功~");
                    return result;

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (inputStream != null) {
                    try {
                        inputStreamReader.close();
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //参数拼接完毕

            //网络连接失败，没有数据
            LogUtil.e("请求失败~");
            return null;
        }

    /*下载图片
    *
    * httpurl 图片地址
    * dir 文件名
    * rename 文件名
    * */
    public static File downLoadEverything(String httpUrl, File dir, String rename,KaoLaTask.IDownlaodListener listener){

            InputStream inputStream =null;
           // InputStreamReader inputStreamReader = null;
            FileOutputStream fileOutputStream = null;
            try {
                URL url = new URL(httpUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //请求方式（默认也是）
                conn.setRequestMethod("GET");
                //读取超时报错
                conn.setReadTimeout(5000);
                //连接超时
                conn.setConnectTimeout(5000);
                //建立连接
                conn.connect();
                //获取返回码
                int responseCode = conn.getResponseCode();
                //如果请求成功了
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    //获取输入流
                    inputStream = conn.getInputStream();
/*
                    如果指定目录不存在那么创建所有的次级目录
*/
                    if(!dir.exists()){
                        dir.mkdirs();
                    }
                    File file = new File(dir,rename);
                    //用来存储读取的一行字符（临时操作符）
                    fileOutputStream = new FileOutputStream(file);
                    //定义（文件）下载速度
                    byte[] buff = new byte[256];
                    int read= -1;
                    int contentLength = conn.getContentLength();
                    /*已下载*/
                    int downLoad= 0;
                    if (listener!=null) {
                        /*开始下载*/
                        listener.start();
                    }
                    while((read=inputStream.read(buff))!=-1){

                        downLoad+=read;
                        double d =downLoad*100.0/contentLength;
                        /*取两位小数*/
                        BigDecimal bigDecimal = new BigDecimal(d).setScale(2,BigDecimal.ROUND_HALF_UP);//四舍五入保留小数点后两位
                        float floatValue = bigDecimal.floatValue();
    /*
                        得到更新的进度值
    */
                        if (listener!=null) {
                            //更新进度
                            listener.upgradeProgress(floatValue);
                        }
                        fileOutputStream.write(buff,0,read);
                        fileOutputStream.flush();//注意
                    }

                    if (listener!=null) {
                        //下载成功
                        LogUtil.w("下载成功！");
                        listener.onCompleted(file);
                    }

                    return file;

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (inputStream!= null) {
                    try {
                        inputStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            Log.e("HttpUtils", "请求失败");
        if (listener!=null) {
            listener.error("监听失败");
        }
            return null;
        }
}
