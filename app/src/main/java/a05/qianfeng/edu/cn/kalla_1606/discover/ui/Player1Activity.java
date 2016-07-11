package a05.qianfeng.edu.cn.kalla_1606.discover.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.discover.bean.Special;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.FastBlur;
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
    private ImageView ivBottom;
    private ImageView ivContent;
    private ImageView ivHead;
    private TextView tvTitle;
    private ImageView btnPause;
    private ImageView ivBack;
    private TextView tvContent;

    /*默认是循环模式*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player1);
        //初始化数据
        ivBottom = (ImageView) findViewById(R.id.player1_iv_bottom);
        ivContent = (ImageView) findViewById(R.id.player1_iv);
        ivHead = (ImageView) findViewById(R.id.header_iv);
        //标题栏
        tvTitle = (TextView) findViewById(R.id.title_tv);
        //歌曲名称
        tvContent = (TextView) findViewById(R.id.player1_title_tv);
        //设置歌曲名称

        //播放暂停按钮
        btnPause = (ImageView) findViewById(R.id.player_pause_btn);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //播放与暂停
                pause();
            }
        });
        //退出应用按钮
        ivBack = (ImageView) findViewById(R.id.iv_btn_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //初始化数据
        initData();
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

    private void initData() {
        Special special = (Special) getIntent().getSerializableExtra(JumpManager.TAG_SPECIAL);
        //设置标题
        tvTitle.setText(special.getAlbumName());
        tvContent.setText(special.getDes());
        final String picUrl = special.getPic();
        new KaoLaTask(new KaoLaTask.IRequest() {
            @Override
            public Object doRequest() {
                //下载图片
                File file = HttpUtils.downLoadEverything(picUrl, FileUtil.dir_image,FileUtil.getFileNameByHashCode(picUrl),null);
                return file;
            }
        }, new KaoLaTask.IRequestCallBack() {
            @Override
            public void success(Object object) {
                //界面显示
                show((File) object);

            }

            @Override
            public void error(String msg) {

            }
        }).execute();
    }

    private void show(File object) {
        //处理以及显示图片
        File file = object;
        Bitmap pic = BitmapFactory.decodeFile(file.getAbsolutePath());
        FastBlur.blur(Player1Activity.this,pic,ivBottom);
       // Drawable target = new BitmapDrawable(getResources(),background);
        //设置背景
       // ivBottom.setBackground(target);
        //设置显示图片
        //缩放图片以达到显示效果
        float sx,sy;
        int width =400;
        int height = 400;
        int tBwidth = pic.getWidth();
        int tBheight = pic.getHeight();
        sx = 1.0f*width/tBwidth;
        sy = 1.0f*height/tBheight;
        Matrix matrix = new Matrix();
        matrix.setScale(sx,sy);
        Bitmap content = Bitmap.createBitmap(pic,0,0,pic.getWidth(),pic.getHeight(),matrix,false);

        ivContent.setImageBitmap(content);

        setHeaderIvImage(pic);
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
            //设置播放路径
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

    //暂停播放
    private void pause(){
        //因为点击按钮只有两种可能所以可以这样
        if(mediaPlayer.isPlaying()){
           mediaPlayer.stop();
          }else{
            mediaPlayer.start();
        }
      //  mediaPlayer.pause();
    }
    //停止播放
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
       //处理异常
        try {

            mediaPlayer.release();
        }catch (Exception e){

        }

        mediaPlayer=null;
    }

    @Override
    public void finish() {
        super.finish();
        task.cancel();
        timer.cancel();
        if (mediaPlayer.isPlaying())
        mediaPlayer.release();
        mediaPlayer=null;
    }

    //设置头像
    private void setHeaderIvImage(Bitmap tempBitmap) {

        Bitmap header = tempBitmap;
        int width = 50;
        int height =50;
        float sx=1.0f,sy = 1.0f;//设置缩放效果的比例
        int tWidth = tempBitmap.getWidth();
        int tHeight = tempBitmap.getHeight();
        Log.e("WH","1."+width+"2."+height+"3."+tWidth+"4."+tHeight);
        //分别设置图片的缩放比例
        sx = sx*width/tWidth;
        sy= sy*height/tHeight;
        //设置缩放的比例
        Matrix matrix = new Matrix();
        matrix.setScale(sx,sy);
        final Bitmap target = Bitmap.createBitmap(header,0,0,tWidth,tHeight,matrix,false);
        ivHead.setImageBitmap(target);
    }
}
