package a05.qianfeng.edu.cn.kalla_1606.discover.util;

import android.util.Log;

import a05.qianfeng.edu.cn.kalla_1606.other.utils.HttpUtils;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;

/**
 *
 * Discover页面Util
 * Created by Administrator on 2016/6/8.
 */
public class DiscoverUtil {

    /*推荐Util*/

    public static  String recommendHttpUrl = "http://api.kaolafm.com/api/v4/pagecontent/list?pageid=104&installid=10000&udid=03109aaa93c76cad9a0327e52eacc93a&imsi=460030238920982&operator=3&lon=113.945338&lat=22.534432&network=1&timestamp=1461832025&sign=3a9525cee60a25e487a099edbfcef6b1&resolution=1080*1812&devicetype=0&channel=A-360&version=4.7.1&appid=0";
    public static String categoryTop = "http://api.kaolafm.com/api/v4/labelinfo/list?id=345&timezone=28800&installid=00032tSZ&udid=960874a160c73cedf7c0b1afc864c7cd&sessionid=960874a160c73cedf7c0b1afc864c7cd1465740376670&imsi=310260806395580&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1465740379&sign=1e691471d4c8b62f7fa314987ab76772&resolution=600*1024&devicetype=0&channel=A-baidu&version=4.8.1&appid=0&";
    public static String category = "http://api.kaolafm.com/api/v4/category/listofall?timezone=28800&installid=00031PNx&udid=e71f486d3189806721fd311ec90c9788&sessionid=e71f486d3189806721fd311ec90c97881465695147626&imsi=460019275200774&operator=2&lon=0.0&lat=0.0&network=1&timestamp=1465695154&sign=a44eea2071be0c3ec74d2927389d05f6&resolution=720*1280&devicetype=0&channel=B-xiaomi&version=4.8.1&appid=0&";
    public static String radio="http://api.kaolafm.com/api/v4/pagecontent/list?pageid=107&timezone=28800&installid=00032tSZ&udid=960874a160c73cedf7c0b1afc864c7cd&sessionid=960874a160c73cedf7c0b1afc864c7cd1465724531102&imsi=310260806395580&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1465724568&sign=1e691471d4c8b62f7fa314987ab76772&resolution=600*1024&devicetype=0&channel=A-baidu&version=4.8.1&appid=0&";
    public static String rachor = "http://api.kaolafm.com/api/v4/pagecontent/list?pageid=105&timezone=-18000&installid=00031Mvi&udid=5fa021f78bfbe3fa1b1d05e09afc1075&sessionid=5fa021f78bfbe3fa1b1d05e09afc10751465880456306&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1465880480&sign=36483795794a5c449bfea30e281ed9a8&resolution=768*1184&devicetype=0&channel=A-baidu&version=4.8.1&appid=0&";
    public static  String live = "http://api.kaolafm.com/api/v4/pagecontent/list?pageid=106&timezone=-18000&installid=00031QM9&udid=a133a6d2b642f89dea34e2d9642890de&sessionid=a133a6d2b642f89dea34e2d9642890de1465893713640&imsi=310260000000000&operator=0&lon=0.0&lat=0.0&network=1&timestamp=1465893724&sign=43bf33f23b0c3ff9c538cf7f50b8dee6&resolution=768*1184&devicetype=0&channel=A-baidu&version=4.8.1&appid=0&";

    /*请求推荐的数据*/

    public static void getRecommend(KaoLaTask.IRequestCallBack callBack){
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtils.doGet(recommendHttpUrl);
            }
        };
        KaoLaTask kaoLaTask = new KaoLaTask(request,callBack);
        kaoLaTask.execute();
    }
/*请求分类表的页面*/
    public static void getCategory(KaoLaTask.IRequestCallBack callBack){
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtils.doGet(category);
            }
        };
        KaoLaTask kaoLaTask = new KaoLaTask(request,callBack);
        kaoLaTask.execute();
    }
    /*电台的页面*/
    public static void getRadio(KaoLaTask.IRequestCallBack callBack){
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {

                return HttpUtils.doGet(radio);
            }
        };
        KaoLaTask kaoLaTask = new KaoLaTask(request,callBack);
        kaoLaTask.execute();
    }
    /**/

    public static void getTypeTopRoot(KaoLaTask.IRequestCallBack callBack){
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
             Object object =  HttpUtils.doGet(categoryTop);
                if(object==null){
                    Log.e("msg:","這不科學！");
                }
               return object;
            }
        };
        KaoLaTask kaoLaTask = new KaoLaTask(request,callBack);
        kaoLaTask.execute();
    }

    /*得到主播页面的数据*/
    public static void getAchor(KaoLaTask.IRequestCallBack callBack){
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtils.doGet(rachor);
            }
        };
        new KaoLaTask(request,callBack).execute();
    }
    /*得到直播页面的请求*/
    public static void getLive(KaoLaTask.IRequestCallBack callBack){
        KaoLaTask.IRequest request = new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                return HttpUtils.doGet(live);
            }
        };
        new KaoLaTask(request,callBack).execute();
    }

}
