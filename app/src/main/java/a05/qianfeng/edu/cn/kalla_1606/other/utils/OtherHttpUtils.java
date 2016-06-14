package a05.qianfeng.edu.cn.kalla_1606.other.utils;

/**
 * other模块所有的网络请求接口和方法
 * Created by Administrator on 2016/6/7.
 */
public class OtherHttpUtils {
    //进入应用的广告
    public static final String url ="http://api.kaolafm.com/api/v4/splashscreen/list?timezone=28800&installid=10000&udid=df89950db6c8b27b94e3112145a9217b&sessionid=df89950db6c8b27b94e3112145a9217b1464231568066&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1464231568&sign=89f2b488d14f006f11f7fb61199b7cd5&resolution=768*1280&devicetype=0&channel=upgrade&version=4.8.1&appid=0&";
    public static  String verstion = "http://api.kaolafm.com/api/v4/upgrade/check?timezone=28800&installid=00031PNx&udid=e71f486d3189806721fd311ec90c9788&sessionid=e71f486d3189806721fd311ec90c97881465792510416&imsi=460019275200774&operator=2&lon=0.0&lat=0.0&network=1&timestamp=1465800037&sign=a44eea2071be0c3ec74d2927389d05f6&resolution=720*1280&devicetype=0&channel=B-xiaomi&appid=0&version=";//删了/n
    /*请求广告*/
    public static void getBanner(KaoLaTask.IRequestCallBack callBack){
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtils.doGet(url);
            }
        };
        KaoLaTask kaoLaTask = new KaoLaTask(request,callBack);
        kaoLaTask.execute();
    }
    /*
    *
    * 请求版本号
    * @params Versions版本号
    * */
    public static void getVersion(KaoLaTask.IRequestCallBack callBack, final String versions){
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtils.doGet(verstion+versions);
            }
        };
        new KaoLaTask(request,callBack).execute();
    }
    /*下载业务封装*/
    public static void downLoadApk(KaoLaTask.IDownlaodListener listener, final String apkUrls){
        KaoLaTask.IDownLoadRequest request = new KaoLaTask.IDownLoadRequest() {
            @Override
            public Object doRequest(KaoLaTask.IDownlaodListener listener) {
                return HttpUtils.downLoadEverything(apkUrls,FileUtil.dir_apk,"KaoLa.apk",listener);
            }
        };
        new KaoLaTask(request,listener).execute();



    }

}
