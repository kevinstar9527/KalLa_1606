package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FileUtil;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.HttpUtils;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.JumpManager;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.KaoLaTask;

/**
 * Created by Administrator on 2016/6/15.
 */
public class Player1Activity extends AppCompatActivity implements MediaPlayer.OnPreparedListener,MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnCompletionListener {
    String mp3Url;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ImageView ivPlay;
    private PlayMode mode = PlayMode.LOOP;
    private SeekBar seekBar;

    private Timer timer  = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {

            int currTime = mediaPlayer.getCurrentPosition();//当前时间
            int allTime = mediaPlayer.getDuration();//总时长
            final int progress = seekBar.getMax()*currTime/allTime;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    seekBar.setProgress(progress);
                }
            });

        }
    };

    /*默认是循环模式*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mp3Url = getIntent().getStringExtra(JumpManager.TAG_MP3_URL);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        seekBar = (SeekBar) findViewById(R.id.player_pr);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                int duration = mediaPlayer.getDuration();
                int postition =  progress*duration/100;
                seeKTo(postition);//设置播放比
            }
        });
        playByUrl(mp3Url);
       // timer.schedule(task,0,1000);//每隔一秒钟更新一下进度
    }

    private void playByUrl (final String mp3Url){
        new Thread(){
            @Override
            public void run() {
                HttpUtils.downLoadEverything(mp3Url, FileUtil.DIR_MP3, FileUtil.getFileNameByHashCode(mp3Url), new KaoLaTask.IDownlaodListener() {
                    @Override
                    public void upgradeProgress(float progress) {

                    }

                    @Override
                    public void onCompleted(File file) {
                        Log.e("msg","下载成功");
                        play(file);
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    public void start() {

                    }
                });
            }
        }.start();
    }
    private void play(File file){
        Uri uri = Uri.fromFile(file);
        mediaPlayer.reset();
        try {
            //设置存放路径
            mediaPlayer.setDataSource(this,uri);
            //开始播放
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mode== PlayMode.SINGLE) {
            /*接着播放这首歌*/
            mp.start();
            /*如果要播放下一首，路径要重设*/
        }else {
            if (mode== PlayMode.LOOP) {
                /*获取下一首歌的播放路径*/
                //playByUrl();
            }
        }
    }

    private void pause(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            }
    }
    private void stop(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
    private void seeKTo(int position){
        mediaPlayer.seekTo(position);
    }
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        seekBar.setProgress(0);
        timer.schedule(task,0,1000);

    }
    /*播放模式*/
    public enum PlayMode{
        /*
        * 单曲循环
        * */
        SINGLE,
        /*列表循环*/
        LOOP,
        /*随机播放*/
        RANDOM,
        /*顺序播放*/
        ORDER
    }

    @Override
    protected void onDestroy() {
        //在退出时释放资源
        super.onDestroy();
        task.cancel();
        timer.cancel();
        mediaPlayer.release();
        mediaPlayer=null;
    }
}
