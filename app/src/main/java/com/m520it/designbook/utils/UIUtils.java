package com.m520it.designbook.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

import com.m520it.designbook.base.MyApplication;


/**
 * @author Higo
 * @time 2016/7/1 0001  18:19
 * @desc ${TODD}
 */
public class UIUtils {
    /**
     * 获取上下文
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 获取resource
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获得String.xml中的字符
     */
    public static String getString(@StringRes int id) {
        return getResources().getString(id);
    }

    /**
     * 获得string.xml中的字符数组
     */
    public static String[] getStringArray(@ArrayRes int id) {
        return getResources().getStringArray(id);
    }

    /**
     * 获得color.xml中的颜色值
     **/
    public static int getColor(@ColorRes int id) {
        return getResources().getColor(id);
    }

    /**
     * 获取程序包名
     *
     * @return 包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

//    /**
//     * 获取全局Handler
//     *
//     * @return 全局Handler
//     */
//    public static Handler getHandler() {
//        return MyApplication.getHandler();
//    }

//    /**
//     * 获取主线程id
//     *
//     * @return 主线程id
//     */
//    public static int getMainThreadId() {
//        return MyApplication.getMainThreadId();
//    }

    /**
     * 获取当前线程id
     *
     * @return 当前线程id
     */
    public static int getCurThreadId() {
        return android.os.Process.myTid();
    }

//    /**
//     * 安全地执行任务,可以安全的进行更改UI等操作,只需要把操作放到task的run()中即可
//     *
//     * @param task 装载要运行的代码的runnable
//     */
//    public static void excuteTaskSecurely(Runnable task) {//要传入方法的话就需要用runnable装载,直接调用run()而不是start()
//        if (getMainThreadId() == getCurThreadId()) {//主线程
//            task.run();
//        } else {//子线程
//            Handler handler = getHandler();
//            handler.post(task);
//        }
//    }

//    /***
//     * 获取和存储fragment的fragmentMap
//     *
//     * @return
//     */
//    public static HashMap<Integer, BaseFragment> getFragmentMap() {
//        return MyApplication.getFragmentMap();
//    }


    /**
     * dip转px
     **/
    public static int dip2Px(int dip) {
        // px/dip=density
        //获取资源,获取显示的量度,密度
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
