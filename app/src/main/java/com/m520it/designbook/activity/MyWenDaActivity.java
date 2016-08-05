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
 * @time 2016/8/3  15:19
 * @desc 我的问答
 */

public class MyWenDaActivity extends BaseActivity{
    private ImageView mFanhui;//返回
    private RelativeLayout mTiwen;//提问
    private RelativeLayout mHuida;//回答
    private RelativeLayout mDuiwotiwen;//向我提问
    private RelativeLayout mTiwen_view;//提问View
    private RelativeLayout mHuida_view;//回答View
    private RelativeLayout mDuiwotiwen_view;//向我提问Veiw
    private TextView mTiwen_tv;//提问tv
    private TextView mHuida_tv;//回答tv
    private TextView mDuiwotiwen_tv;//向我题问tv

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wenda);
        initUi();
        //默认显示
        mTiwen_tv.setTextColor(Color.RED);
        mHuida_tv.setTextColor(Color.BLACK);
        mDuiwotiwen_tv.setTextColor(Color.BLACK);
        mTiwen_view.setVisibility(View.VISIBLE);

        mTiwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTiwen_tv.setTextColor(Color.RED);
                mHuida_tv.setTextColor(Color.BLACK);
                mDuiwotiwen_tv.setTextColor(Color.BLACK);
                mTiwen_view.setVisibility(View.VISIBLE);
                mHuida_view.setVisibility(View.INVISIBLE);
                mDuiwotiwen_view.setVisibility(View.INVISIBLE);
            }
        });
        mHuida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHuida_tv.setTextColor(Color.RED);
                mTiwen_tv.setTextColor(Color.BLACK);
                mDuiwotiwen_tv.setTextColor(Color.BLACK);
                mHuida_view.setVisibility(View.VISIBLE);
                mTiwen_view.setVisibility(View.INVISIBLE);
                mDuiwotiwen_view.setVisibility(View.INVISIBLE);
            }
        });
        mDuiwotiwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHuida_tv.setTextColor(Color.BLACK);
                mTiwen_tv.setTextColor(Color.BLACK);
                mDuiwotiwen_tv.setTextColor(Color.RED);
                mHuida_view.setVisibility(View.INVISIBLE);
                mTiwen_view.setVisibility(View.INVISIBLE);
                mDuiwotiwen_view.setVisibility(View.VISIBLE);
            }
        });
        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IntentUtils.startActivityAndFinish(MyWenDaActivity.this,MineFragment.class);
                finish();
            }
        });

    }

    @Override
    protected void initUi() {
        mFanhui = (ImageView) findViewById(R.id.wenda_fanhui);
        mTiwen = (RelativeLayout) findViewById(R.id.wenda_tiwen);
        mTiwen_tv = (TextView) findViewById(R.id.wenda_tiwen_tv);
        mTiwen_view = (RelativeLayout) findViewById(R.id.wenda_tiwen_view);
        mHuida = (RelativeLayout) findViewById(R.id.wenda_huida);
        mHuida_tv = (TextView) findViewById(R.id.wenda_huida_tv);
        mHuida_view = (RelativeLayout) findViewById(R.id.wenda_huida_view);
        mDuiwotiwen = (RelativeLayout) findViewById(R.id.wenda_duiwotiwen);
        mDuiwotiwen_tv = (TextView) findViewById(R.id.wenda_duiwotiwen_tv);
        mDuiwotiwen_view = (RelativeLayout) findViewById(R.id.wenda_duiwotiwen_view);
    }
}
