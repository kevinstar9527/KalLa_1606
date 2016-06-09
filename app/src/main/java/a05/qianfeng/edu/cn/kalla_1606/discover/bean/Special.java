package a05.qianfeng.edu.cn.kalla_1606.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Special {


    /**
     * weburl :
     * area : null
     * listenNum : 0
     * rtype : 1
     * followedNum : 0
     * albumName : 旅游约吗
     * cornerMark : 0
     * rid : 1000002520128
     * host : []
     * pic : http://image.kaolafm.net/mz/images/201606/9f3312ef-c7e1-4aa2-94bf-167a7cf7d985/default.jpg
     * num : 0
     * tip :
     * mp3PlayUrl : http://image.kaolafm.net/mz/audios/201606/851fb6d3-03a2-4c44-ab7f-635707c98a26.mp3
     * rvalue : 1000002520128
     * rname : 有爸妈的欢笑，才是最美的旅行
     * des : 旅游约吗
     */
    /**
     * fansCount : 12755
     * recommendReson :
     * isVanchor : 0
     * likedNum : 7182659
     * avatar : http://image.kaolafm.net/mz/images/201602/5922f2f1-108e-40e8-99c7-ebaecd09d184/default.jpg
     * liveStatus : 0
     * relation : 0
     * gender : 0
     * extraAttributes :
     * desc : 我
     * nickName : 孔垂楠Korn
     * uid : 2418440
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

    public static Special objectFromData(String str) {

        return new Gson().fromJson(str, Special.class);
    }

    public static List<Special> arraySpecialFromData(String str) {

        Type listType = new TypeToken<ArrayList<Special>>() {
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

    public void setRid(long rid) {
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
