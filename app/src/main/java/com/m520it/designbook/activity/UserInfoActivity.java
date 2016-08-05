package com.m520it.designbook.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;
import com.m520it.designbook.base.MyApplication;
import com.m520it.designbook.bean.RLoginResult;
import com.m520it.designbook.config.NetWorkCons;

/**
 * @author 慕泽
 * @time 2016/8/3  10:00
 * @desc 用户资料
 */

public class UserInfoActivity extends BaseActivity {
    private ImageView mFanhui;//返回键
    private SimpleDraweeView mTouxiang;//头像;
    private TextView userinfo_name;//真实姓名
    private TextView userinfo_chengshi;//所在城市
    private TextView userinfo_shouji;//手机
    private TextView userinfo_qq;//QQ
    private TextView userinfo_jieduan;//装修阶段
    private TextView muserinfo_fengge;//家居风格

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiyt_userinfo);
        initUi();
        Uri uri = Uri.parse(NetWorkCons.TOUXIANG_URL);
        mTouxiang.setImageURI(uri);
        MyApplication application = (MyApplication) getApplication();
        RLoginResult mUserinfo = application.mUserinfo;
        userinfo_name.setText(mUserinfo.getUserName());
        userinfo_shouji.setText(String.valueOf(mUserinfo.getId()));
        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initUi() {
        mFanhui = (ImageView) findViewById(R.id.userinfo_fanhui);
        mTouxiang = (SimpleDraweeView) findViewById(R.id.userinfo_facebool);
        userinfo_name = (TextView) findViewById(R.id.userinfo_name);
        userinfo_chengshi = (TextView) findViewById(R.id.userinfo_chengshi);
        userinfo_shouji = (TextView) findViewById(R.id.userinfo_shouji);
        userinfo_qq = (TextView) findViewById(R.id.userinfo_qq);
        userinfo_jieduan = (TextView) findViewById(R.id.userinfo_jieduan);
        muserinfo_fengge = (TextView) findViewById(R.id.userinfo_fengge);
    }
}
