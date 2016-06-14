package a05.qianfeng.edu.cn.kalla_1606.discover.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class Type {


    /**
     * categoryId : 706
     * title : 鏂伴椈
     * type : 5
     * parentId : -1
     * parentCategoryName :
     * label : -1
     * icon : http://image.kaolafm.net/mz/images/201512/21353684-3a8c-49e2-9332-9b71a0f36679/default.png
     * backgroundPic : http://image.kaolafm.net/mz/images/201512/22f6d896-f2de-437c-86b2-b8e432b26359/186_240.jpg
     */

    private int categoryId;
    private String title;
    private int type;
    private int parentId;
    private String parentCategoryName;
    private int label;
    private String icon;
    private String backgroundPic;

    public static Type objectFromData(String str) {

        return new Gson().fromJson(str, Type.class);
    }
/*用json中的一段生成json*/
    public static Type objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Type.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Type> arrayTypeFromData(String str) {

        java.lang.reflect.Type listType = new TypeToken<ArrayList<Type>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<Type> arrayTypeFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            java.lang.reflect.Type listType = new TypeToken<ArrayList<Type>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackgroundPic() {
        return backgroundPic;
    }

    public void setBackgroundPic(String backgroundPic) {
        this.backgroundPic = backgroundPic;
    }
}
