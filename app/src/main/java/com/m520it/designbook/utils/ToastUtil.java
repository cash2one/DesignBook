package com.m520it.designbook.utils;

import android.app.Activity;
import android.widget.Toast;

import com.m520it.designbook.base.MyApplication;

/**
 * Created by dragon on 2016/6/3.
 *
 * @desc 能够在子线程和主线程 都能安全的运行的吐司
 */
public class ToastUtil {

    public static void showToast(Activity activity, final String text) {
        if ("main".equals(Thread.currentThread().getName())) {
            //说明是在主线程
            Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT).show();
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
