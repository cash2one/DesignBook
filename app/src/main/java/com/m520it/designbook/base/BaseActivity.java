package com.m520it.designbook.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * @author 慕泽
 * @time 2016/7/10  18:42
 * @desc Activity 基类   因为后面用到 fragment  所以继承FragmentActivity
 *                                              也是不会报错的
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initUi();
    }


    //子类实现的方法
    protected abstract void initUi();//初始一个ui


}
