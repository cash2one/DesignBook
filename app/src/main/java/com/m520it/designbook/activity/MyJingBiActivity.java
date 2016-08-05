package com.m520it.designbook.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;

/**
 * @author 慕泽
 * @time 2016/8/3  19:28
 * @desc 我的金币
 */

public class MyJingBiActivity extends BaseActivity{
    private ImageView mFanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_jingbi);
        initUi();

        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IntentUtils.startActivityAndFinish(MyJingBiActivity.this,MineFragment.class);
                //IntentUtils.removerActivity(MyJingBiActivity.this,MineFragment.class);
                finish();
            }
        });

    }

    @Override
    protected void initUi() {
        mFanhui = (ImageView) findViewById(R.id.jingbi_fanhui);
    }
}
