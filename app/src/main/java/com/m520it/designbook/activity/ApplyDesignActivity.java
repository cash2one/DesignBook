package com.m520it.designbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.m520it.designbook.R;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/7/30 17:08
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */

/**
 *申请定制设计
 */
public class ApplyDesignActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_design);
        initUI();
    }

    /**
     * 加载网页
     */
    private void initUI() {
        findViewById(R.id.go_back).setOnClickListener(this);
        /**-------进度条-----**/
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setMax(100);

        final WebView webView = (WebView) findViewById(R.id.apply_design_webview);

        /**------给webView注册一个事件实现加载进度------**/
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        webView.setInitialScale(25);
        webView.getSettings().setUseWideViewPort(true);
        /**加载网页*/
        webView.loadUrl("http://m.shejiben.com/appZb");

        /**------在WebView中打开所有链接------**/
        webView.setWebViewClient(new WebViewClient(){
            public void onProgressChanged(WebView view, int progress){
                // Activity和Webview根据加载程度决定进度条的进度大小
                // 当加载到100%的时候 进度条自动消失
                if(progress==100){
                    progressBar.setVisibility(View.GONE);

                }
            }
        });
    }

    /**-----返回键,直接finish-------**/
    @Override
    public void onClick(View view) {
        finish();
    }
}
