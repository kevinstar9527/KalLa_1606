package a05.qianfeng.edu.cn.kalla_1606.other.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/18.
 */
public class ShakeSensorListener implements SensorEventListener {
    //加速度传感器，监听你的控件位置的变化
    //传感器对象
    private Sensor sensor;
    //传感器管理者

    private SensorManager manager;
    /*
    * 速度阀值
    * */
    private final int speedTag = 200;
    private long lastTime = 0;
    //延迟的时间
    private long delay = 150;
    private float lastX,lastY,lastZ;
    private IShakeListener listener ;

    public ShakeSensorListener(Context context) {
        //获取传感器服务
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //摇一摇的加速度传感器
        sensor =  manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //注册传感器
        manager.registerListener(this,sensor,Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values [2];
       // long currTime = SystemClock.currentThreadTimeMillis();//获取当前时间
       long currTime=  System.currentTimeMillis();//这个方法有延迟
        if (currTime-lastTime<delay){
            return;
        }
        lastTime = currTime;
        float distanceX = x -lastX;
        float distanceY = x -lastY;
        float distanceZ = x -lastZ;
        Log.e("msg","正在摇一摇+"+"x = "+x+" y = "+y+" z = "+z);
        //计算距离
       double distance =  Math.sqrt(distanceX*distanceX+distanceY*distanceY+distanceZ*distanceZ);
        //速度
        double speed =  (distance / delay * 1000);
        Log.e("speed","1 = " +speed);
        if (speed>speedTag){
            //如果速度达到阀值，可以进行你想要的操作
            Log.e("speed","2 = " +speed);
        }
        //重新计算上一次的坐标
        lastX =x;
        lastY = y;
        lastZ = z;
        //执行振动事件
        listener.onShake();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void unRegister(){
        //注销传感器
        manager.unregisterListener(this);
    }

    public interface IShakeListener{

        /*
        * 摇动的时候你想做什么
        * */
        void onShake();
    }
    public void setIShakeListener(IShakeListener listener){
            this.listener = listener;

    }
}
