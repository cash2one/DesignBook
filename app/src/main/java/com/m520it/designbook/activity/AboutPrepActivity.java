package com.m520it.designbook.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;

/**
 * @author 慕泽
 * @time 2016/7/30  18:53
 * @desc 关于设计本 的界面类
 */

public class AboutPrepActivity extends BaseActivity {
    private ImageView mGuanyu_fanhui_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_prep);
        initUi();
        mGuanyu_fanhui_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initUi() {
        mGuanyu_fanhui_iv = (ImageView) findViewById(R.id.guanyu_fanhui_iv);
    }
}
