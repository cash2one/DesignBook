package com.m520it.designbook.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.m520it.designbook.R;
import com.m520it.designbook.config.SPConstances;
import com.m520it.designbook.utils.IntentUtils;

/**
 * @desc 1.过渡界面 2.版本判断  3.有需要的话 做数据库更新  4
 *
 *
 *
 * Created by dragon on 2016/7/29.
 */
public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sp ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        sp = getSharedPreferences(SPConstances.SP_BASE,MODE_PRIVATE);
        //跳转界面
        skipActivity();
        //TODO 版本判断 之类的
    }

    /**
     * 跳转界面  如果是第一次进入 先进入引导界面 否者直接进入正页
     */
    private void skipActivity() {
        boolean isFirst = sp.getBoolean(SPConstances.SP_ISFIRST, true);
        //如果为true  表示是第一次进入
        if(isFirst) {
            IntentUtils.startActivityAndFinishAndDelay(this,GuideActivity.class,3000);
        }else{
            IntentUtils.startActivityAndFinishAndDelay(this,MainActivity.class,3000);
        }
    }


    /**
     * 仅上传测试用  稍后删除
     */
    private void initData(){}
}
