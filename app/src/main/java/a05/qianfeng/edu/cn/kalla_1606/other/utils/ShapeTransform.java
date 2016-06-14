package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by Liu Jianping
 *图片裁剪
 * @date : 16/5/26.
 */
public class ShapeTransform implements Transformation
{

    public enum Shape
    {
        Circle, // 圆

        Prismatic, // 棱形

        Star, // 五角星

        Heart //心形
    }

    private Shape shape;

    private int width;

    private int height;

    public ShapeTransform(Shape shape)
    {
        this.shape = shape;
    }

    @Override
    public Bitmap transform(Bitmap source)
    {
        width = source.getWidth();
        height = source.getHeight();

        if (shape == Shape.Circle)
        {
            return createCircle(source);
        }
        else if (shape == Shape.Prismatic)
        {
            return createPrismatic(source);
        }
        else if(shape == Shape.Heart)
        {
            return createHeart(source);
        }
        else if (shape == Shape.Star)
        {
            return createStar(source);
        }
        return source;
    }

    /**
     * 角度转弧度公式
     *
     * @param degree
     * @return
     */
    private float degree2Radian(int degree) {
        return (float) (Math.PI * degree / 180);
    }

    /**
     * 五角星
     *
     * @param source
     * @return
     */
    public Bitmap createStar(Bitmap source)
    {
        // 获取边长小的值作为裁剪后的正方形图片的边长
        int size = Math.min(width, height);
        int radius = size / 2;

        // 计算正方形的左上角坐标
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        // 从原图片上裁剪指定大小的正方形生成一张新的图片
        Bitmap transformBitmap = Bitmap.createBitmap(source, x, y, size, size);
        // 创建一张空图片作为画布的背景
        Bitmap bitmap = Bitmap
                .createBitmap(size, size, Bitmap.Config.ARGB_8888);

        // 创建画布
        Canvas canvas = new Canvas(bitmap);


        Paint paint = new Paint();
        paint.setAntiAlias(true);

        BitmapShader shader = new BitmapShader(transformBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        paint.setShader(shader);

        Path path = new Path();
        float radian = degree2Radian(36);// 36为五角星的角度
        float radius_in = (float) (radius * Math.sin(radian / 2) / Math
                .cos(radian)); // 中间五边形的半径

        path.moveTo((float) (radius * Math.cos(radian / 2)), 0);// 此点为多边形的起点
        path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
                        * Math.sin(radian)),
                (float) (radius - radius * Math.sin(radian / 2)));
        path.lineTo((float) (radius * Math.cos(radian / 2) * 2),
                (float) (radius - radius * Math.sin(radian / 2)));
        path.lineTo((float) (radius * Math.cos(radian / 2) + radius_in
                        * Math.cos(radian / 2)),
                (float) (radius + radius_in * Math.sin(radian / 2)));
        path.lineTo(
                (float) (radius * Math.cos(radian / 2) + radius
                        * Math.sin(radian)), (float) (radius + radius
                        * Math.cos(radian)));
        path.lineTo((float) (radius * Math.cos(radian / 2)),
                (float) (radius + radius_in));
        path.lineTo(
                (float) (radius * Math.cos(radian / 2) - radius
                        * Math.sin(radian)), (float) (radius + radius
                        * Math.cos(radian)));
        path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
                        * Math.cos(radian / 2)),
                (float) (radius + radius_in * Math.sin(radian / 2)));
        path.lineTo(0, (float) (radius - radius * Math.sin(radian / 2)));
        path.lineTo((float) (radius * Math.cos(radian / 2) - radius_in
                        * Math.sin(radian)),
                (float) (radius - radius * Math.sin(radian / 2)));

        path.close();// 使这些点构成封闭的多边形
        canvas.drawPath(path, paint);

        source.recycle();

        return bitmap;
    }

    /**
     * 心形
     *
     * @param source
     * @return
     */
    public Bitmap createHeart(Bitmap source)
    {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        paint.setShader(shader);

        Path path = new Path();
        path.moveTo(width / 2, width / 5);
        path.quadTo(width, 0, width / 2, width / 1.0f);
        path.quadTo(0, 0, width / 2, width / 5);

        path.close();
        canvas.drawPath(path, paint);

        source.recycle();

        return bitmap;
    }

    /**
     * 创建棱形
     * 
     * @param source
     * @return
     */
    public Bitmap createPrismatic(Bitmap source)
    {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //设置原素，不改变图片的色彩，从原图里面获取对应位置的内容
        BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        paint.setShader(shader);

        // 计算棱形的顶点
        Path path = new Path();
        path.moveTo(width / 2, 0);
        path.lineTo(0, height / 2);
        path.lineTo(width / 2, height);
        path.lineTo(width, height / 2);
        path.close();

        canvas.drawPath(path, paint);

        source.recycle();
        return bitmap;
    }

    /**
     * 创建圆形图片
     * 
     * @param source
     * @return
     */
    private Bitmap createCircle(Bitmap source)
    {
        // 获取边长小的值作为裁剪后的正方形图片的边长
        int size = Math.min(width, height);
        int radius = size / 2;

        // 计算正方形的左上角坐标
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        // 从原图片上裁剪指定大小的正方形生成一张新的图片
        Bitmap transformBitmap = Bitmap.createBitmap(source, x, y, size, size);
        // 创建一张空图片作为画布的背景
        Bitmap bitmap = Bitmap
                .createBitmap(size, size, Bitmap.Config.ARGB_8888);

        // 创建画布
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿

        // 从剪完后的图片去除内容
        // BitmapShader shader = new BitmapShader(transformBitmap,
        // Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // paint.setShader(shader);

        // 把内容画成一个圆形
        canvas.drawCircle(radius, radius, radius, paint);

        //取两张图的交集
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(transformBitmap, 0, 0, paint);

        // 释放资源
        source.recycle();
        return bitmap;
    }

    @Override
    public String key()
    {
        return "ShapeTransform";
    }
}
