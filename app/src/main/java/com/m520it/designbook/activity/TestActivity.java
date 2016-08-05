package com.m520it.designbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.ecloud.pulltozoomview.PullToZoomBase;
import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.m520it.designbook.R;
import com.m520it.designbook.protocol.IOnScrollChangedListener;
import com.m520it.designbook.ui.MyPullToZoomScrollViewEx;
import com.m520it.designbook.utils.LogUtils;

public class TestActivity extends AppCompatActivity {

    public static final int INT = 68;
    public static final int INT1 = 63;
    private MyPullToZoomScrollViewEx scroll_EX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();

    }

    private void initView() {
        scroll_EX = (MyPullToZoomScrollViewEx) findViewById(R.id.scroll_EX);
//        View head = View.inflate(this, R.layout.head_case_activity, null);
        View background = View.inflate(this, R.layout.background_scroll_activity, null);
        View content = View.inflate(this, R.layout.content_case_activity, null);

//        scroll_EX.setHeaderView(head);
        scroll_EX.setZoomView(background);
        scroll_EX.setScrollContentView(content);

        scroll_EX.setOnPullZoomListener(new PullToZoomBase.OnPullZoomListener() {
            @Override
            public void onPullZooming(int newScrollValue) {
                LogUtils.v("newScrollValue"+newScrollValue);
            }

            @Override
            public void onPullZoomEnd() {
                LogUtils.v("END");

            }
        });

        scroll_EX.setIOnScrollChangedListener(new IOnScrollChangedListener() {
            @Override
            public void onScrollChanged(int x, int y, int oldX, int oldY) {
                LogUtils.v("滑动"+x+":"+y+":"+oldX+":"+oldY+":");
            }
        });

//        setPullToZoomViewLayoutParams(scroll_EX);
    }


    private void setPullToZoomViewLayoutParams(PullToZoomScrollViewEx scrollView) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth,
                (int) (9.0F * (mScreenWidth / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }
}
