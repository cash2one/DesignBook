package com.m520it.designbook.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;

/**
 * @author 慕泽
 * @time 2016/8/3  17:02
 * @desc 我的好友
 */

public class MyHaoYouActivity extends BaseActivity{
    private ImageView mFanhui;//返回
    private RelativeLayout mGuanzhu;//关注
    private TextView mGuanzhu_tv;
    private RelativeLayout mGuanzhu_view;
    private RelativeLayout mFenshi;//粉丝
    private TextView mFenshi_tv;
    private RelativeLayout mFenshi_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_haoyou);
        initUi();
        mGuanzhu_tv.setTextColor(Color.RED);
        mFenshi_tv.setTextColor(Color.BLACK);
        mGuanzhu_view.setVisibility(View.VISIBLE);
        mFenshi_view.setVisibility(View.INVISIBLE);
        mGuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuanzhu_tv.setTextColor(Color.RED);
                mFenshi_tv.setTextColor(Color.BLACK);
                mGuanzhu_view.setVisibility(View.VISIBLE);
                mFenshi_view.setVisibility(View.INVISIBLE);
            }
        });
        mFenshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGuanzhu_tv.setTextColor(Color.BLACK);
                mFenshi_tv.setTextColor(Color.RED);
                mGuanzhu_view.setVisibility(View.INVISIBLE);
                mFenshi_view.setVisibility(View.VISIBLE);
            }
        });
        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IntentUtils.startActivityAndFinish(MyHaoYouActivity.this,MineFragment.class);
                finish();
            }
        });
    }

    @Override
    protected void initUi() {
        mFanhui = (ImageView) findViewById(R.id.haoyou_fanhui);
        mGuanzhu = (RelativeLayout) findViewById(R.id.haoyou_guanzhu);
        mGuanzhu_tv = (TextView) findViewById(R.id.haoyou_guanzhu_tv);
        mGuanzhu_view = (RelativeLayout) findViewById(R.id.haoyou_guanzhu_view);
        mFenshi = (RelativeLayout) findViewById(R.id.haoyou_fenshi);
        mFenshi_tv = (TextView) findViewById(R.id.haoyou_fenshi_tv);
        mFenshi_view = (RelativeLayout) findViewById(R.id.haoyou_fenshi_view);
    }
}
