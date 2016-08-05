package com.m520it.designbook.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.m520it.designbook.bean.RLoginResult;
import com.m520it.designbook.utils.LogUtils;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * @author JJ
 * @time 2016/7/29 0029  20:13
 * @desc 全局常量
 */
public class MyApplication extends Application{

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        Fresco.initialize(this);
        mApplicationContext = getApplicationContext();
        Logger.init(LogUtils.mTag);
    }
    public static  Context getContext(){
        return mApplicationContext;
    }



    //声明用户信息 保存在整个引用上面 慕泽添加
    public RLoginResult mUserinfo;

    //在声明一个布尔值 判断他是否已经登入了 慕泽添加
    public boolean mShiFouDengRu;

    //下面这个方法是关闭整个应用的  慕泽添加
    public static List<Activity> mList = new LinkedList<Activity>();
    public static void addActivity(Activity activity) {
        mList.add(activity);
    }

    public static void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
