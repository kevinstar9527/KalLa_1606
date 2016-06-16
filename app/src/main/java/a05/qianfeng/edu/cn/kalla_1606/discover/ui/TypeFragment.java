package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.adapter.TypeAdapter;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Type;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.TypeTop;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.TypeTopRoot;
import a05.qianfeng.edu.cn.kalla_1606.discover.util.DiscoverUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.ui.BaseFragment;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.Contants;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.widget.TypeTopLayout;

/**
 * Created by Administrator on 2016/6/7.
 */
public class TypeFragment extends BaseFragment {

    private RecyclerView recycleView;

    private TypeAdapter gridAdapter;

    private String TAG = "msg";

    final List<Type> list = new ArrayList<>();
    List<Bitmap>bitmaps = new ArrayList<>();
    TypeTopLayout typeTop;
    LinearLayout llroot;
    private TypeTopLayout topLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover_type;
    }

    @Override
    protected void initViews() {

        recycleView = (RecyclerView) root.findViewById(R.id.bottom_rv);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        recycleView.setLayoutManager(manager);
        llroot = (LinearLayout)root.findViewById(R.id.type_linearLayout);
        topLayout = (TypeTopLayout)root.findViewById(R.id.topLayout);
        gridAdapter = new TypeAdapter(getActivity(), list);


    }

    @Override
    protected void initData() {


        recycleView.setAdapter(gridAdapter);

        Log.e("msg","Hello");

        //先静态加载试试测试成功

//        TypeTopLayout typeTopLayout = new TypeTopLayout(getContext());
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
//        typeTopLayout.setLayoutParams(params);
//        typeTopLayout.setBitmaps(bitmaps);
//        llroot.addView(typeTopLayout,0);

        /*用网络请求试试*/
        DiscoverUtil.getTypeTopRoot(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {
                try {

                    Log.e("object",object.toString());

                    JSONObject root = new JSONObject(object.toString());
                    String message= root.getString("message");
                    Log.e("msg",message);
                    if (Contants.flag_result_success.equals(message)) {
                        JSONObject roott = root.getJSONObject(Contants.flag_result_dataresult);
                        TypeTopRoot topRoot = TypeTopRoot.objectFromData(roott.toString());
                        /*得到顶部的数据*/
                        List<TypeTop> typeTops =topRoot.getDataList();
                        if (typeTops==null){
                            Log.e("msg","typeTop为空" );
                        }
                        /*开始解析数据*/

                        addTop(typeTops);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String msg) {

            }
        });


        DiscoverUtil.getCategory(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {
                try {
                    if(object==null){
                        Log.e("msg",": 這裡出錯了");
                        return ;
                    }
                    JSONObject root = new JSONObject(object.toString());
                    String message = root.getString(Contants.flag_result_message);
                    if (Contants.flag_result_success.equals(message)) {
                        JSONObject result = root.getJSONObject(Contants.flag_result_dataresult);
                        JSONArray dataList = result.getJSONArray(Contants.flag_result_datalist);
                        /*获取json的第一条数据*/
                        JSONObject object1 = (JSONObject) dataList.get(0);
                        JSONArray finalDatalist = object1.getJSONArray("dataList");

                        /*这里有不同的f方式*/
                        // List<Type> typeList1 = Type.arrayTypeFromData(object1.toString(), Contants.flag_result_datalist);//这种方式需要对里面的方法设置固定高度
                        List<Type> typeList2 = Type.arrayTypeFromData(finalDatalist.toString());
                        list.addAll(typeList2);
                        /*更新数据*/
                        gridAdapter.notifyDataSetChanged();

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

    private void addTop(final List<TypeTop> typeTops) {
        Log.e("type:",""+typeTops.size());
        List<String> imageUrl = new ArrayList<>();
        for (int i = 0; i < typeTops.size(); i++) {
            Log.e("msg", "addTop: " + typeTops.get(i).getPic());
            imageUrl.add(typeTops.get(i).getPic());
        }

        topLayout.setImageUrlList(imageUrl);

    }


    @Override
    protected void initEvents() {

        gridAdapter.setOnItemClickListener(new TypeAdapter.OnRecycleViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                intent.putExtra("msg", data);
                startActivity(intent);
            }
        });
    }
}
