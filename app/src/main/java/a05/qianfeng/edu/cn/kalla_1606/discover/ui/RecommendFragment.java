package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.adapter.EnterAdpater;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Recommond;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;
import a05.qianfeng.edu.cn.kalla_1606.discover.util.DiscoverUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.adapter.CommonImageAdapter;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.ImageUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.JumpManager;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.LogUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.CornerPannel;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.GuessPannel;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.IndexViewLine;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.SpecialPannel;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.VerticalTextAndImageLayout;

/**
 * Created by Administrator on 2016/6/7.
 */
public class RecommendFragment extends BaseFragment {

    private LinearLayout llroot;
    private IndexViewLine indexViewLine;
    private SwipeRefreshLayout refresh;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover_recommend;
    }

    @Override
    protected void initViews() {
        llroot = (LinearLayout) root.findViewById(R.id.recommend_banner);

        refresh = (SwipeRefreshLayout)root.findViewById(R.id.refresh);
        /*设置颜色*/
        /*最多只能设置四种颜色*/
        /*设置刷新颜色*/
        refresh.setColorSchemeColors(new int[]{Color.RED,Color.GRAY,Color.BLUE});

    }

    @Override
    protected void initData() {

        refreshData();
    }

    private void refreshData() {
        DiscoverUtil.getRecommend(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {
                LogUtil.w("object="+object.toString());
                //开始解析数据
                try {
                    JSONObject root = new JSONObject(object.toString());
                    String message = root.getString("message");
                    if("success".equals(message)){
                        //获取result里面对象
                        JSONObject result = root.getJSONObject("result");
                        JSONArray dataList = result.getJSONArray("dataList");

                        //用Gson解析list
                        List<Recommond> recommondList = Recommond.arrayRecommondFromData(dataList.toString());
                        LogUtil.e("reconmendList.seze = "+recommondList.size());
                        /*清楚上一次的视图*/
                        llroot.removeAllViews();
                        for(int i =0 ;i<recommondList.size();i++){
                            Recommond recommend = recommondList.get(i);

                           //根据ComponentType判断要显示什么样的item布局
                            switch (recommend.getComponentType()){
                                case Recommond.ComponentType.TYPE_Banner:
                                      addBanner(recommend);
                                        break;
                                case Recommond.ComponentType.TYPE_ENTER:
                                       addEnter(recommend);
                                        break;
                                case Recommond.ComponentType.TYPE_SCROLL_NEW://滚动快讯
                                        //addVerticalScrollText(recommend);
                                        break;
                                case Recommond.ComponentType.TYPE_PANEL:
                                    addSpecialPanel(recommend);
                                    break;
                                case Recommond.ComponentType.TYPE_GUESS_YOU_WHAT:
                                    addGuessPanel(recommend);
                                    break;
                                case Recommond.ComponentType.TYPE_SINGLEBANNWE:
                                    addSingleBanner(recommend);
                                    break;
                                case Recommond.ComponentType.TYPE_ANTOR_HOt:
                                    addANTORHOT(recommend);
                            }

                        }
                        refresh.setRefreshing(false);

                        /*下面这些可以用来做测试使用*/

//                        addBanner(recommondList.get(0));
//                        addEnter(recommondList.get(1));
//                        addVerticalScrollText(recommondList.get(2));
//                        addSpecialPanel(recommondList.get(3));
//                        addSpecialPanel(recommondList.get(4));
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

    private void addANTORHOT(Recommond recommend) {

        CornerPannel corner = new CornerPannel(getContext(),recommend);
        llroot.addView(corner);

    }

    private void addBanner(Recommond reconmend){


            //开始动态添加
            LogUtil.e("开始动态添加");
            LogUtil.w("广告画廊");

            indexViewLine = new IndexViewLine(getContext());
            //indexViewLine.setBackgroundColor(Color.GRAY);



            int widthLine =ViewGroup.LayoutParams.MATCH_PARENT;
            int heightLine = 10;
            LinearLayout.LayoutParams rootParamsLine = new LinearLayout.LayoutParams(widthLine,heightLine);
            indexViewLine.setLayoutParams(rootParamsLine);
            //得到ViewPager中的页面数
            indexViewLine.setCount(reconmend.getDataList().size());

            //测试导航横线使用

            indexViewLine.setCurrIndex(0);

            //获取屏幕宽度
          //  Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width =ViewGroup.LayoutParams.MATCH_PARENT;
            int height = 300;
            //为控件设置宽高
            final ViewPager veiwPager = new ViewPager(getActivity());
            //为Viewpager设置宽高
            LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(width,height);
            veiwPager.setLayoutParams(rootParams);
            veiwPager.setPageMargin(50);//设置每一页之间的间隔
            veiwPager.setOffscreenPageLimit(2);//设置缓存页面
            //veiwPager.setPageTransformer(true,new Kao);//页面修改

            //开始往ViewPager中动态添加控件
            List<ImageView> imageViews = new ArrayList<>();
            List<String>urls = new ArrayList<>();
            final List<Special> dataList = reconmend.getDataList();
            for(int i =0 ;i<dataList.size();i++){
                //创建并添加ImageView
                //首先为ImageView控件设置宽高
                ImageView imageView = new ImageView(getActivity());
                //将图片“全屏”显示
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                ViewPager.LayoutParams iParams = new ViewPager.LayoutParams();

                iParams.width=width;
                iParams.height=height;
                imageView.setLayoutParams(iParams);
                //添加图片地址
                urls.add(dataList.get(i).getPic());
                //添加图片控件
                imageViews.add(imageView);

            }
            CommonImageAdapter imageAdapter = new CommonImageAdapter(imageViews,urls);
            veiwPager.setAdapter(imageAdapter);


            veiwPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    indexViewLine.setCurrIndex(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }


            });
            veiwPager.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currItem = veiwPager.getCurrentItem();
                    Special special = dataList.get(currItem);
                    String webUrl = special.getWeburl();
                    if(!webUrl.isEmpty()){
                        JumpManager.jumpToWeb(getActivity(),webUrl);
                    }
                }
            });

            llroot.addView(veiwPager);
            llroot.addView(indexViewLine);

        }

/*
*   添加快捷路口
*
* */
    private void addEnter(Recommond recommend){
        RecyclerView recyclerView = new RecyclerView(getActivity());
        //宽高
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,250);
        recyclerView.setLayoutParams(params);

        //设置布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        //瀑布流效果StaggedGridlayoutManager
        //GridLayoutManager
        //设置方向为横向
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置管理器
        recyclerView.setLayoutManager(manager);
        //升级版的RecycleView
        EnterAdpater adpater = new EnterAdpater(getActivity(),recommend.getDataList());
        recyclerView.setAdapter(adpater);

        llroot.addView(recyclerView);
    }
    private void addVerticalScrollText(Recommond recommond){
    
    /*自定义控件不加业务逻辑，可在其他处用*/
    List<Special>dataList = recommond.getDataList();
    final List<String> list = new ArrayList<>();
    for (int i = 0; i < dataList.size(); i++) {

        list.add(dataList.get(i).getDes());
    }


//    final VerticalText view = new VerticalText(getActivity(),list);
//    view.setBackgroundColor(Color.GRAY);
//    LinearLayout.LayoutParams params;
//    params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50);
//    params.topMargin=20;
//    params.bottomMargin=50;
//    view.setLayoutParams(params);
//    llroot.addView(view);
//    view.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//           int currIndex =  view.getCurrIndex();
//            Toast.makeText(getActivity(),list.get(currIndex),Toast.LENGTH_LONG).show();
//        }
//    });

    /*尝试
    /*正在设置布局*/
    Log.w("Layout","正在设置布局");
    VerticalTextAndImageLayout banner = new VerticalTextAndImageLayout(getContext(),list);
    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
    banner.setLayoutParams(params);
    llroot.addView(banner);

}

    /*添加专辑面板*/
    private void addSpecialPanel(Recommond recommond){
        SpecialPannel specialPannel = new SpecialPannel(getContext(),recommond);
        llroot.addView(specialPannel);
    }

    /*添加猜你猜你喜欢面板*/

    private void addGuessPanel(Recommond reconmend){
        GuessPannel guessPannel = new GuessPannel(getContext(),reconmend);
        llroot.addView(guessPannel);
    }

    /*增加一个单栏的banner*/
    private void addSingleBanner(Recommond recommond){
        ImageView singleBanner = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        singleBanner.setLayoutParams(params);
        singleBanner.setPadding(30,20,20,30);
        singleBanner.setScaleType(ImageView.ScaleType.FIT_XY);
        List<Special> special = recommond.getDataList();
        ImageLoader.getInstance().displayImage(special.get(0).getPic(),singleBanner, ImageUtil.getDefaultOption());

        llroot.addView(singleBanner);

    }
    @Override
    protected void initEvents() {

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

    }
}
