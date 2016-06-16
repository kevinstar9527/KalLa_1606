package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.adapter.Player2Adapter;
import a05.qianfeng.edu.cn.kalla_1606.other.bean.PlayEntity;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.Contants;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.OtherHttpUtils;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Palyer2Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private Player2Adapter adapter;
    private List<PlayEntity> list= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);
        viewPager = (ViewPager) findViewById(R.id.player2_vp);
        requestList();
    }
    /*获取播放列表*/
    private void requestList(){

        adapter = new Player2Adapter(this,list);
        viewPager.setAdapter(adapter);
        OtherHttpUtils.getPlayerList(new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {
                try {
                    JSONObject root = new JSONObject(object.toString());
                    String message  = root.getString("message");
                    if (Contants.flag_result_success.equals(message)) {
                        JSONObject result = root.getJSONObject("result");
                        JSONArray dataList = result.getJSONArray("dataList");
                        List<PlayEntity> entityList =PlayEntity.arrayPlayEntityFromData(dataList.toString());
                        //没想到这样也可以
                        list.addAll(entityList);
                        adapter.notifyDataSetChanged();
                        //这里只会调用他的实instantiateItem和destroyItem方法
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

}
