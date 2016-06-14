package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.adapter.ZhiNengJXAdapter;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Radio;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.RadioInner;
import a05.qianfeng.edu.cn.kalla_1606.discover.util.DiscoverUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.adapter.CommonImageAdapter;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.Contants;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.CornerRadioPannel;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.IndexViewLine;

/**
 * Created by Administrator on 2016/6/7.
 */
public class RadioFragment extends BaseFragment {

    private PullToRefreshScrollView refreshMyradio;
    private ViewPager viewPager;
    private IndexViewLine indexLine;
    private RecyclerView recycleView;
    private LinearLayout llroot;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover_radio;
    }

    @Override
    protected void initViews() {
        refreshMyradio = (PullToRefreshScrollView) root.findViewById(R.id.refreshMyradio);
        viewPager = (ViewPager)root.findViewById(R.id.radio_viewPager);
        indexLine = (IndexViewLine) root.findViewById(R.id.index_line);
        recycleView = (RecyclerView) root.findViewById(R.id.radio_recycle);
        llroot = (LinearLayout) refreshMyradio.findViewById(R.id.linearRoot);
    }



    @Override
    protected void initData() {
        /*开始执行*/
        refreshData();
    }

    @Override
    protected void initEvents() {
        refreshMyradio.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ScrollView> refreshView) {
                /*下拉*/
                /*这里模拟下拉刷新*/

                /*测试*/

                new Thread(){

                    @Override
                    public void run() {
                        super.run();
                        try {

                            sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                       getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /*停止刷新*/
                                Log.e("cancel",""+"Yes");
                                refreshMyradio.onRefreshComplete();
                                //refreshView.setRefreshing(false);
                            }
                        });

                    }
                }.start();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                        /*上拉加载*/
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refreshMyradio.onRefreshComplete();
                            }
                        });
                    }
                }.start();

            }
        });
    }

    private void refreshData(){

        DiscoverUtil.getRadio(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {

                /*开始解析数据*/
                try {
                    JSONObject root = new JSONObject(object.toString());
                   String message =  root.getString("message");
                    if(Contants.flag_result_success.equals(message)){
                        JSONObject result = root.getJSONObject(Contants.flag_result_dataresult);
                        JSONArray dataList=result.getJSONArray(Contants.flag_result_datalist);
                        /*用Gson解析list*/
                        List<Radio> list = Radio.arrayRadioFromData(dataList.toString());
                        for (int i = 0; i < list.size(); i++) {

                            Radio radio =list.get(i);
                            switch (radio.getComponentType()){
                                case Radio.ComponentType.TYPE_Banner:
                                    /*设置滚动页面的内容*/
                                    addBanner(radio);
                                    break;
                                case Radio.ComponentType.TYPE_AI:
                                    addZhiNengJingXuan(radio);
                                    break;
                                case Radio.ComponentType.TYPE_ANTOR_HOt:

                                    addHotAntor(radio);


                            }

                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(String msg) {

            }
        });


    }

    private void addHotAntor(Radio radio) {
        /*增加主播页面*/
        CornerRadioPannel cornerRadioPannel = new CornerRadioPannel(getContext(),radio);
        llroot.addView(cornerRadioPannel);

    }

    private void addZhiNengJingXuan(Radio radio) {


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView.setLayoutManager(manager);
        ZhiNengJXAdapter adapter = new ZhiNengJXAdapter(getContext(),radio.getDataList());
        recycleView.setAdapter(adapter);

    }

    private void addBanner(Radio radio) {
        /*初始化ViewPager页面的组件*/
        List<ImageView> imageViews = new ArrayList<>();
        /*获取页面组件数据的url*/
        List<String>urls = new ArrayList<>();
        /*添如数据，设置图片显示属性*/
        List<RadioInner> dataList = radio.getDataList();

        for (int i = 0; i < dataList.size(); i++) {

            ImageView imageView = new ImageView(getActivity());
                    /*将图片全屏显示*/
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewPager.LayoutParams params = new ViewPager.LayoutParams();

            params.width= ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = 300;
            imageView.setLayoutParams(params);
            /*初始化数据*/
            urls.add(dataList.get(i).getPic());
            imageViews.add(imageView);

        }
        indexLine.setCount(dataList.size());
        indexLine.setCurrIndex(0);
        CommonImageAdapter imageAdapter = new CommonImageAdapter(imageViews,urls);
        viewPager.setAdapter(imageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indexLine.setCurrIndex(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

}
