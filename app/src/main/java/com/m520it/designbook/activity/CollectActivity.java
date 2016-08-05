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
 * @time 2016/8/3  10:56
 * @desc 我的收藏
 */

public class CollectActivity extends BaseActivity{
    private ImageView mFanhui;//返回
    private ImageView mTianjia;//添加
    private RelativeLayout mTuku;//图库
    private RelativeLayout mAnli;//案例
    private RelativeLayout mTukuView;//图库View
    private RelativeLayout mAnliView;//案例View
    private TextView mTukuText;//图库text
    private TextView mAnliText;//案例text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initUi();
        //进去默认显示
        mTukuText.setTextColor(Color.RED);
        mAnliText.setTextColor(Color.BLACK);
        mTianjia.setVisibility(View.VISIBLE);
        mTuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTukuText.setTextColor(Color.RED);
                mAnliText.setTextColor(Color.BLACK);
                mTianjia.setVisibility(View.VISIBLE);

                mTukuView.setVisibility(View.VISIBLE);
                mAnliView.setVisibility(View.INVISIBLE);
            }
        });
        mAnli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnliText.setTextColor(Color.RED);
                mTukuText.setTextColor(Color.BLACK);
                mTianjia.setVisibility(View.INVISIBLE);

                mTukuView.setVisibility(View.INVISIBLE);
                mAnliView.setVisibility(View.VISIBLE);
            }
        });
        mTianjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTianjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IntentUtils.startActivityAndFinish(CollectActivity.this, MineFragment.class);
                finish();
            }
        });
    }

    @Override
    protected void initUi() {
        mFanhui = (ImageView) findViewById(R.id.collec_fanhui);
        mTianjia = (ImageView) findViewById(R.id.collec_tianjia);
        mTuku = (RelativeLayout) findViewById(R.id.collec_tuku);
        mAnli = (RelativeLayout) findViewById(R.id.collec_anli);
        mTukuText = (TextView) findViewById(R.id.collec_tuku_tv);
        mAnliText = (TextView) findViewById(R.id.collec_anli_tv);
        mTukuView = (RelativeLayout) findViewById(R.id.collec_tuku_view);
        mAnliView = (RelativeLayout) findViewById(R.id.collec_anli_view);
    }
}
