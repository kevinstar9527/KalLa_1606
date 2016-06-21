package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Live;
import a05.qianfeng.edu.cn.kalla_1606.discover.util.DiscoverUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;

/**
 * Created by Administrator on 2016/6/7.
 */
public class LiveFragment extends BaseFragment {

    private ViewPager vpBannerTop;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover_live;
    }

    @Override
    protected void initViews() {

        vpBannerTop = (ViewPager) root.findViewById(R.id.vp_Live);

    }

    @Override
    protected void initEvents() {
        vpBannerTop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

        DiscoverUtil.getLive(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {
                try {
                    //解析从直播界面请求回来的信息
                    JSONObject root = new JSONObject(object.toString());
                    String message = root.getString("message");
                    if ("success".equals(message)) {
                        JSONObject result = root.getJSONObject("result");
                        JSONArray dataList = result.getJSONArray("dataList");
                        //得到数据
                        List<Live> live = Live.arrayLiveFromData(dataList.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {

            }
        });

        List<ImageView> ivBanners = new ArrayList<>();
        List<String> ivUrl;
       // CommonImageAdapter imageAdapter = new CommonImageAdapter(ivBanners,ivUrl);
    }

}
