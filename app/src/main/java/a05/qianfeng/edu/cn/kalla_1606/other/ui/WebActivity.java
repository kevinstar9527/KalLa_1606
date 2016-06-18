package a05.qianfeng.edu.cn.kalla_1606.other.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import a05.qianfeng.edu.cn.kalla_1606.R;
import a05.qianfeng.edu.cn.kalla_1606.other.utils.JumpManager;

/**
 * 浏览器页面
 * Created by Administrator on 2016/6/15.
 */
public class WebActivity extends AppCompatActivity {

    private ProgressBar progre;
    private WebView webView;

    private WebViewClient webViewClient = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("msg:","url+"+url);
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            /*页面加载完成*/
            /*设置页面的标题*/
            String titles = view.getTitle();
            title.setText(titles);
            super.onPageFinished(view, url);
        }
    };
    private WebChromeClient chromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress==100) {
                progre.setVisibility(View.INVISIBLE);
            }else{
                progre.setProgress(newProgress);
            }
       }
    };
   ;
    private TextView title;
    private ImageView back;


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        // tvTitle = (TextView) findViewById(R.id.topTitle);
        progre = (ProgressBar) findViewById(R.id.web_pb);
        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(webViewClient);

        WebSettings settings = webView.getSettings();
        /*允许webview允许脚本语言*/
        settings.setJavaScriptEnabled(true);
        /*添加javaScriptt回调代码的接口*/
        webView.addJavascriptInterface(new IKaoLaoWebView(),"KaoLa");
        /*获取从其他页面传过来的url*/
        String url = getIntent().getStringExtra(JumpManager.TAG_URL);
        webView.loadUrl(url);
        title = (TextView) findViewById(R.id.title_web);
        back = (ImageView) findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    /*javaScript要访问的java类*/
    @SuppressLint("JavascriptInterface")
    class IKaoLaoWebView {

        public void setTitle(String titles){
            if(titles!=null){
                title.setText(titles);
                Log.e("Title",titles);
            }
        }
    }
}
