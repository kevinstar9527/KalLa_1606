package a05.qianfeng.edu.cn.kalla_1606.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class TypeTopRoot {


    /**
     * id : 345
     * name : 分类页顶部运营1
     * icon :
     * desc :
     * hasmore : 0
     * moreType : 0
     * componentType : null
     * contentType : 1
     * relatedValue : 0
     * pic :
     * contentSourceId : -1
     * count : 0
     * dataList : []
     */

    private int id;
    private String name;
    private String icon;
    private String desc;
    private int hasmore;
    private int moreType;
    private Object componentType;
    private int contentType;
    private String relatedValue;
    private String pic;
    private int contentSourceId;
    private int count;
    private List<TypeTop> dataList;

    public static TypeTopRoot objectFromData(String str) {

        return new Gson().fromJson(str, TypeTopRoot.class);
    }

    public static TypeTopRoot objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), TypeTopRoot.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TypeTopRoot> arrayTypeTopRootFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<TypeTopRoot>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<TypeTopRoot> arrayTypeTopRootFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<TypeTopRoot>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getHasmore() {
        return hasmore;
    }

    public void setHasmore(int hasmore) {
        this.hasmore = hasmore;
    }

    public int getMoreType() {
        return moreType;
    }

    public void setMoreType(int moreType) {
        this.moreType = moreType;
    }

    public Object getComponentType() {
        return componentType;
    }

    public void setComponentType(Object componentType) {
        this.componentType = componentType;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getRelatedValue() {
        return relatedValue;
    }

    public void setRelatedValue(String relatedValue) {
        this.relatedValue = relatedValue;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getContentSourceId() {
        return contentSourceId;
    }

    public void setContentSourceId(int contentSourceId) {
        this.contentSourceId = contentSourceId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<TypeTop> getDataList() {
        return dataList;
    }

    public void setDataList(List<TypeTop> dataList) {
        this.dataList = dataList;
    }
}
