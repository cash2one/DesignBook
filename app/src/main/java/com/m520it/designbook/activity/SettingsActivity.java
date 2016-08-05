package com.m520it.designbook.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.m520it.designbook.R;
import com.m520it.designbook.base.BaseActivity;
import com.m520it.designbook.base.MyApplication;
import com.m520it.designbook.utils.IntentUtils;

/**
 * @author 慕泽
 * @time 2016/7/30  16:43
 * @desc 我的 里面的设置界面类
 */

public class SettingsActivity extends BaseActivity {

    private ImageView mFanhui;
    private RelativeLayout mVersions;
    private RelativeLayout mCache;
    private RelativeLayout mPraise;
    private RelativeLayout mOpinion;
    private RelativeLayout mAbout_Prep;
    private RelativeLayout mTuichu_Login;
    private RelativeLayout mTuichu_yinyong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initUi();
        //判断是否登入成功
        MyApplication application = (MyApplication) getApplication();
        boolean mShiFouDengRu = application.mShiFouDengRu;
        if (!mShiFouDengRu) {
            mTuichu_Login.setVisibility(View.INVISIBLE);//显示
            mTuichu_yinyong.setVisibility(View.VISIBLE);//隐藏
        } else {
            mTuichu_Login.setVisibility(View.VISIBLE);//隐藏
            mTuichu_yinyong.setVisibility(View.INVISIBLE);//显示
        }


        mFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //版本更新
        mVersions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        //缓存
        mCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        //好评
        mPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        //意见
        mOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        //关于
        mAbout_Prep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击到另一个界面 顺带网络请求
                IntentUtils.startActivity(SettingsActivity.this, AboutPrepActivity.class);
            }
        });
        //退出登入
        mTuichu_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行下面方法,给一个弹出框
                tuichudengru();
            }
        });
        //退出应用
        mTuichu_yinyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行下面的方法 给他一个弹出框
                tuichuyingyong();
            }
        });
    }

    //退出登入
    private void tuichudengru() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置名字
        builder.setTitle("提示");
        //设置内容
        builder.setMessage("敢退出就打你哦!");
        //设置里面的两个按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳到登入界面
                IntentUtils.startActivityAndFinish(SettingsActivity.this,LoginActivity.class);
            }
        });
        builder.setNegativeButton("取消", null);
        //启动一下
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //退出应用
    private void tuichuyingyong() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置名字
        builder.setTitle("提示");
        //设置内容
        builder.setMessage("你确定要退出? 想清楚一点先!");
        //设置里面的两个按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //退出整个应用
                MyApplication.exit();
                finish();
            }
        });
        builder.setNegativeButton("取消", null);
        //启动一下
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void initUi() {
        mFanhui = (ImageView) findViewById(R.id.iv_fanhui);//返回
        mVersions = (RelativeLayout) findViewById(R.id.rl_set11);//版本检查
        mCache = (RelativeLayout) findViewById(R.id.rl_set22);//清除缓存
        mPraise = (RelativeLayout) findViewById(R.id.rl_set33);//给我好评
        mOpinion = (RelativeLayout) findViewById(R.id.rl_set44);//意见反馈
        mAbout_Prep = (RelativeLayout) findViewById(R.id.rl_set55);//关于设置本
        mTuichu_Login = (RelativeLayout) findViewById(R.id.tuichu_login);//退出登入
        mTuichu_yinyong = (RelativeLayout) findViewById(R.id.tuichu_yinyong);//退出应用
    }
}
