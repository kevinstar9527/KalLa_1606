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
public class TypeTop {


    /**
     * rid : 1331
     * rtype : 16
     * rname : 王牌脱口秀
     * pic : http://image.kaolafm.net/mz/images/201605/a2cdf22b-92cf-44a2-a1fd-ad6f0df9e740/default.jpg
     * weburl :
     * des : 王牌脱口秀
     * albumName :
     * num : 0
     * host : []
     * mp3PlayUrl :
     * cornerMark : 0
     * rvalue : 1331
     * tip :
     * followedNum : 0
     * listenNum : 0
     * area : null
     */

    private int rid;
    private int rtype;
    private String rname;
    private String pic;
    private String weburl;
    private String des;
    private String albumName;
    private int num;
    private String mp3PlayUrl;
    private int cornerMark;
    private String rvalue;
    private String tip;
    private int followedNum;
    private int listenNum;
    private Object area;
    private List<?> host;

    public static TypeTop objectFromData(String str) {

        return new Gson().fromJson(str, TypeTop.class);
    }

    public static TypeTop objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), TypeTop.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TypeTop> arrayTypeTopFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<TypeTop>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<TypeTop> arrayTypeTopFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<TypeTop>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMp3PlayUrl() {
        return mp3PlayUrl;
    }

    public void setMp3PlayUrl(String mp3PlayUrl) {
        this.mp3PlayUrl = mp3PlayUrl;
    }

    public int getCornerMark() {
        return cornerMark;
    }

    public void setCornerMark(int cornerMark) {
        this.cornerMark = cornerMark;
    }

    public String getRvalue() {
        return rvalue;
    }

    public void setRvalue(String rvalue) {
        this.rvalue = rvalue;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getFollowedNum() {
        return followedNum;
    }

    public void setFollowedNum(int followedNum) {
        this.followedNum = followedNum;
    }

    public int getListenNum() {
        return listenNum;
    }

    public void setListenNum(int listenNum) {
        this.listenNum = listenNum;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public List<?> getHost() {
        return host;
    }

    public void setHost(List<?> host) {
        this.host = host;
    }
}
