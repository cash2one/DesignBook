package com.m520it.designbook.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.m520it.designbook.R;

import java.lang.reflect.Method;

/**
 * 网页显示的activity--JJ
 * <p/>
 * intent需要传入两个参数:
 * "title",显示在标题栏的标题,String类型
 * "url", 网页url,String类型
 */
public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TITLE = "title";
    public static final String URL = "url";

    private ImageView goback;//返回
    private ProgressBar progress;//进度条
    private WebView mWebView;//web
    private TextView mTv_title;//标题
    private String mTitle;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mUrl = getIntent().getStringExtra(URL);
        mTitle = getIntent().getStringExtra(TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initUi();
        setWebView();
    }


    protected void initUi() {
        goback = (ImageView) findViewById(R.id.goback);
        progress = (ProgressBar) findViewById(R.id.progress);
        mWebView = (WebView) findViewById(R.id.webView);
        mTv_title = (TextView) findViewById(R.id.tv_title);

        goback.setOnClickListener(this);
        if (mTitle != null) {
            mTv_title.setText(mTitle);
        }
    }


    private void setWebView() {
        //能够支持H5之类的效果的设置
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//启用JS
        settings.setBuiltInZoomControls(false);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setBlockNetworkImage(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setSaveFormData(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportZoom(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //自己处理跳转
                view.loadUrl(url);
                return true;
            }

        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            //            显示progress
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == progress.getVisibility()) {
                        progress.setVisibility(View.VISIBLE);
                    }
                    progress.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            /*** 使用 js 的弹出框 */
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return true;
            }

            /** 使用 js 的确认框 **/
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return true;
            }

        });

        try {
            //禁用硬件加速
            Method method = WebView.class.getMethod("setLayerType", int.class, Paint.class);
            method.setAccessible(true);
            method.invoke(mWebView, 1, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWebView.getSettings().setBlockNetworkImage(false);
            }
        }, 1000);

        mWebView.loadUrl(mUrl);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }
}
