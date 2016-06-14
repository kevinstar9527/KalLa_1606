package a05.qianfeng.edu.cn.kalla_1606.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class Anchor {


    /**
     * contentType : 1
     * relatedValue : -1
     * icon :
     * id : 254
     * componentType : 1
     * desc :
     * pic :
     * dataList : []
     * moreType : 3
     * count : 0
     * contentSourceId : 6
     * name : 轮播图
     * hasmore : 1
     */

    private int contentType;
    private String relatedValue;
    private String icon;
    private int id;
    private int componentType;
    private String desc;
    private String pic;
    private int moreType;
    private int count;
    private int contentSourceId;
    private String name;
    private int hasmore;
    private List<AnchorInner> dataList;

    public static Anchor objectFromData(String str) {

        return new Gson().fromJson(str, Anchor.class);
    }

    public static List<Anchor> arrayAnchorFromData(String str) {

        Type listType = new TypeToken<ArrayList<Anchor>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComponentType() {
        return componentType;
    }

    public void setComponentType(int componentType) {
        this.componentType = componentType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getMoreType() {
        return moreType;
    }

    public void setMoreType(int moreType) {
        this.moreType = moreType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getContentSourceId() {
        return contentSourceId;
    }

    public void setContentSourceId(int contentSourceId) {
        this.contentSourceId = contentSourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHasmore() {
        return hasmore;
    }

    public void setHasmore(int hasmore) {
        this.hasmore = hasmore;
    }

    public List<AnchorInner> getDataList() {
        return dataList;
    }

    public void setDataList(List<AnchorInner> dataList) {
        this.dataList = dataList;
    }

    public static class CompomentType{
        public static final int TYPE_BANNER = 1;

        public static final int TYPE_SCROLL_TEXT = 31;
        public static final int TYPE_ANCHOR = 26;
    }
}
