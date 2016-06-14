package a05.qianfeng.edu.cn.kalla_1606.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Radio_Antor_hot {

    /**
     * fansCount : 249
     * recommendReson : 资深情感节目主持人
     * isVanchor : 0
     * likedNum : 4
     * avatar : http://image.kaolafm.net/mz/images/201601/0dc7a2b7-e7f6-4aef-a09f-f79e093dcfcc/default.jpg
     * liveStatus : 0
     * relation : 0
     * gender : 3
     * extraAttributes :
     * desc : TA懒懒的什么也没写
     * nickName : 青音
     * uid : 2400521
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

    public static Radio_Antor_hot objectFromData(String str) {

        return new Gson().fromJson(str, Radio_Antor_hot.class);
    }

    public static Radio_Antor_hot objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Radio_Antor_hot.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Radio_Antor_hot> arrayRadio_Antor_hotFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<Radio_Antor_hot>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Radio_Antor_hot> arrayRadio_Antor_hotFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Radio_Antor_hot>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

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
}
