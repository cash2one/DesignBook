package com.m520it.designbook.utils;

import android.app.Activity;
import android.content.Intent;

/**@desc 界面跳转工具类
 * Created by dragon on 2016/6/3.
 */
public class IntentUtils {
    /**
     * 启动一个新的的界面并且结束原界面并且有延迟
     *
     * @param activity
     * @param clsz
     * @param time
     */
    public static void startActivityAndFinishAndDelay(final  Activity activity,final Class<?> clsz ,final long time){
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(activity,clsz);
                activity.startActivity(intent);
                activity.finish();
            }
        }.start();
    }
    /**
     * 启动一个新的的界面并且结束原界面
     *
     * @param activity
     * @param clsz
     *
     */
    public static void startActivityAndFinish(final  Activity activity,final Class<?> clsz ){
        //因为界面要让他滑动起来 所以这里就不能放置多线程
                Intent intent = new Intent(activity,clsz);
                activity.startActivity(intent);
                activity.finish();
    }
    /**
     * 启动一个新的的界面
     *
     * @param activity
     * @param clsz
     *
     */
    public static void startActivity(final  Activity activity,final Class<?> clsz ){
        new Thread(){
            @Override
            public void run() {
                Intent intent = new Intent(activity,clsz);
                activity.startActivity(intent);
            }
        }.start();
    }
    /**
     * 关闭一个界面
     *
     * @param activity
     * @param
     *
     */
    public static void removerActivity(final Activity activity, final Class<?> clzz){
        activity.finish();
    }
}
