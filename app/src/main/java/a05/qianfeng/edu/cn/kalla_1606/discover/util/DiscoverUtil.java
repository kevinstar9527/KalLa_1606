package a05.qianfeng.edu.cn.kalla_1606.discover.util;

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


}
