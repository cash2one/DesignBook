package com.m520it.designbook.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;

/**
 * @author 慕泽
 * @time 2016/8/4  1:27
 * @desc 我的消息
 */

public class MyInFoActivity extends BaseActivity{
    private ImageView mFanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_xiaoxi);
        initUi();

        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initUi() {
        mFanhui = (ImageView) findViewById(R.id.xiaoxi_fanhui);
    }
}
