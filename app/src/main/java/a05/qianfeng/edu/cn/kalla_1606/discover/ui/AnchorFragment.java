package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Anchor;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.AnchorInner;
import a05.qianfeng.edu.cn.kalla_1606.discover.util.DiscoverUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.adapter.CommonImageAdapter;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.Contants;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.CornerAchorPannel;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.IndexViewLine;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.VerticalTextAndImageLayout;

/**
 * Created by Administrator on 2016/6/7.
 */
public class AnchorFragment extends BaseFragment {

    private LinearLayout llroot;
    private ViewPager viewPager;
    private String TAG = "msg";
    IndexViewLine indexNewLine;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_discover_anchor;
    }

    @Override
    protected void initViews() {


        llroot = (LinearLayout) root.findViewById(R.id.llroot);
      //  addViewPager();

    }

    private void addBanner(List<AnchorInner> list) {



        viewPager = new ViewPager(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        viewPager.setLayoutParams(params);
        List<ImageView> imageViews =  new ArrayList<>();
        List<String> httpUrls=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //动态获取数据和数据源
            ImageView imageView = new ImageView(getContext());
            httpUrls.add(list.get(i).getPic());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewPager.LayoutParams  paramsIv = new ViewPager.LayoutParams();
            paramsIv.width=ViewGroup.LayoutParams.MATCH_PARENT;
            paramsIv.height = 300;
            imageView.setLayoutParams(paramsIv);
            imageViews.add(imageView);
        }

        CommonImageAdapter adapter = new CommonImageAdapter(imageViews,httpUrls);
        viewPager.setAdapter(adapter);
         indexNewLine = new IndexViewLine(getContext());
        LinearLayout.LayoutParams param1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10);
        indexNewLine.setLayoutParams(param1);
        indexNewLine.setCount(list.size());
        indexNewLine.setCurrIndex(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indexNewLine.setCurrIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        llroot.addView(viewPager,0);
        llroot.addView(indexNewLine,1);



    }

    @Override
    protected void initData() {

        refreshData();

    }

    @Override
    protected void initEvents() {

    }

    public void refreshData(){

        DiscoverUtil.getAchor(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {
                try {
                    JSONObject root = new JSONObject(object.toString());
                    String message = root.getString("message");
                    if (Contants.flag_result_success.equals(message)) {
                        JSONObject result = root.getJSONObject("result");
                        JSONArray dataList =  result.getJSONArray("dataList");
                        /*得到各个组件的而数据*/
                        Log.e("msg",dataList.toString());
                        List<Anchor> anchors = Anchor.arrayAnchorFromData(dataList.toString());
                        for (int i = 0; i < anchors.size(); i++) {

                            List<AnchorInner> anchorInner = anchors.get(i).getDataList();
                            switch (anchors.get(i).getComponentType()){
                                case Anchor.CompomentType.TYPE_BANNER:
                                    addBanner(anchorInner);
                                    break;
                                case Anchor.CompomentType.TYPE_SCROLL_TEXT:
                                   addScrollText(anchorInner);
                                    break;
                                case Anchor.CompomentType.TYPE_ANCHOR:
                                    addAnchor(anchors.get(i));
                                    break;
                            }

                        }


                    }else{
                        Log.e(TAG, "success: 请求失败" );
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

    private void addAnchor(Anchor anchor) {

        Log.e(TAG, "addAnchor: " + anchor.getDataList().size());
        CornerAchorPannel achor = new CornerAchorPannel(getContext(),anchor);
        llroot.addView(achor);

    }

    private void addScrollText(List<AnchorInner> anchorInner) {

       List<String> scrollTexts = new ArrayList<>();
        for (int i = 0; i < anchorInner.size(); i++) {
            scrollTexts.add(anchorInner.get(i).getDes());
        }
        VerticalTextAndImageLayout scrollLayout = new VerticalTextAndImageLayout(getContext(),scrollTexts);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
        scrollLayout.setLayoutParams(params);
        llroot.addView(scrollLayout);

    }
}
