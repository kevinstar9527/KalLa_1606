package a05.qianfeng.edu.cn.testevent;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/18.
 */
public class MyProvider extends ContentProvider {
    /*Provider第一次被访问的时候回调的方法
    * 这是个在主线程中运行，所以不能有耗时操作
    * */
    @Override
    public boolean onCreate() {
        Log.e("MyProvider", "onCreate():");
        return false;
    }

    //给外界提供一个查询的功能

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.e("MyProvider", "onCreate():");

        return null;
    }

    /*指定的一个uri返回的数据类型*/
    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.e("MyProvider", "getType):");

        return null;
    }

    /*提供插入的功能*/
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.e("MyProvider", "insert():");

        return null;
    }

    /*提供删除数据的功能*/
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d("MyProvider", "delete():" );
        return 0;
    }
    /*提供一个更新数据的功能*/
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.e("MyProvider", "update");
        return 0;
    }
}
