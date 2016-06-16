package a05.qianfeng.edu.cn.kalla_1606.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class RadioInnerContent {


    /**
     * type : 1
     * pic :
     * name : 国家台
     * id : 1
     */

    private int type;
    private String pic;
    private String name;
    private int id;

    public static RadioInnerContent objectFromData(String str) {

        return new Gson().fromJson(str, RadioInnerContent.class);
    }

    public static List<RadioInnerContent> arrayRadioInnerContentFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<RadioInnerContent>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
