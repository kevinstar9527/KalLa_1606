package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * 图片裁剪
 * Created by Administrator on 2016/6/14.
 */
public class KaoLaTranSformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(source, 0, height / 3, width, height / 3);

        source.recycle();//释放
        return bitmap;

    }

    @Override
    public String key() {
        return "KaoLaoTransfrom";
    }
}
