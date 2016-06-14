package a05.qianfeng.edu.cn.kalla_1606.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class AnchorInner {


    /**
     * fansCount : 562
     * recommendReson : 知性女音萝莉心
     * isVanchor : 1
     * likedNum : 109373
     * avatar : http://image.kaolafm.net/mz/images/201604/34b262db-c522-44b1-9f05-54cee631413f/default.jpg
     * liveStatus : 0
     * relation : 0
     * gender : 1
     * extraAttributes :
     * desc : 爱声音 爱京剧的电台主播。
     * nickName : 晴雨
     * uid : 2075442
     */


    private int fansCount;
    private String recommendReson;
    private int isVanchor;
    private int likedNum;
    private String avatar;
    private int liveStatus;
    private int relation;
    private int gender;
    private String extraAttributes;
    private String desc;
    private String nickName;
    private int uid;

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public String getRecommendReson() {
        return recommendReson;
    }

    public void setRecommendReson(String recommendReson) {
        this.recommendReson = recommendReson;
    }

    public int getIsVanchor() {
        return isVanchor;
    }

    public void setIsVanchor(int isVanchor) {
        this.isVanchor = isVanchor;
    }

    public int getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(int likedNum) {
        this.likedNum = likedNum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(int liveStatus) {
        this.liveStatus = liveStatus;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getExtraAttributes() {
        return extraAttributes;
    }

    public void setExtraAttributes(String extraAttributes) {
        this.extraAttributes = extraAttributes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * weburl :
     * area : null
     * listenNum : 0
     * rtype : 13
     * followedNum : 0
     * albumName :
     * cornerMark : 0
     * rid : 2087421
     * host : []
     * pic : http://image.kaolafm.net/mz/images/201606/36da244c-ea74-4075-8b23-2078e6eae850/default.jpg
     * num : 0
     * tip :
     * mp3PlayUrl :
     * rvalue : 2087421
     * rname : 彤掌柜
     * des : 彤掌柜
     */

    private String weburl;
    private Object area;
    private int listenNum;
    private int rtype;
    private int followedNum;
    private String albumName;
    private int cornerMark;
    private long rid;
    private String pic;
    private int num;
    private String tip;
    private String mp3PlayUrl;
    private String rvalue;
    private String rname;
    private String des;
    private List<?> host;

    public static AnchorInner objectFromData(String str) {

        return new Gson().fromJson(str, AnchorInner.class);
    }


    public static List<AnchorInner> arrayAnchorInnerFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<AnchorInner>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }



    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public int getListenNum() {
        return listenNum;
    }

    public void setListenNum(int listenNum) {
        this.listenNum = listenNum;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public int getFollowedNum() {
        return followedNum;
    }

    public void setFollowedNum(int followedNum) {
        this.followedNum = followedNum;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getCornerMark() {
        return cornerMark;
    }

    public void setCornerMark(int cornerMark) {
        this.cornerMark = cornerMark;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getMp3PlayUrl() {
        return mp3PlayUrl;
    }

    public void setMp3PlayUrl(String mp3PlayUrl) {
        this.mp3PlayUrl = mp3PlayUrl;
    }

    public String getRvalue() {
        return rvalue;
    }

    public void setRvalue(String rvalue) {
        this.rvalue = rvalue;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public List<?> getHost() {
        return host;
    }

    public void setHost(List<?> host) {
        this.host = host;
    }
}
