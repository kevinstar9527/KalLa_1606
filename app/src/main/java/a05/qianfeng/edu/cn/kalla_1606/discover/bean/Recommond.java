package a05.qianfeng.edu.cn.kalla_1606.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/6/8.
 */
public class Recommond {

    /**
     * contentType : 1
     * relatedValue : 0
     * icon :
     * id : 238
     * componentType : 1
     * desc :
     * pic :
     * dataList : []
     * moreType : 1
     * count : 0
     * contentSourceId : 6
     * name : 顶部banner
     * hasmore : 0
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
    private List<Special> dataList;

    public static Recommond objectFromData(String str) {

        return new Gson().fromJson(str, Recommond.class);
    }

    public static List<Recommond> arrayRecommondFromData(String str) {

        Type listType = new TypeToken<ArrayList<Recommond>>() {
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

    public List<Special> getDataList() {
        return dataList;
    }

    public void setDataList(List<Special> dataList) {
        this.dataList = dataList;
    }

/*
*   推荐页面要显示的内容所对应的类型常量
*
* */
    public static class ComponentType{
        //栏目信息，有三个的有六个的
        public static final int TYPE_PANEL = 3;
        //顶部广告页
        public static final int TYPE_Banner =1;
        //快捷入口
        public static final int TYPE_ENTER = 28;
        //滚动快讯
        public static final int TYPE_SCROLL_NEW = 31;
        //积分商城
        public static final int TYPE_JIFEN = 8;
        //猜你喜欢
        public static final int TYPE_GUESS_YOU_WHAT = 29;

        //专题/活动/商城
        public static final int TYPE_ENJOY = 27;
        //热门主播
        public static final int TYPE_ANTOR_HOt = 26;
        //自定义栏目

     public static final int TYPE_CUNSTOM = 32;


    }


}

