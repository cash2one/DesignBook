package com.m520it.designbook.controller;

import com.m520it.designbook.utils.HttpHelper;
import com.m520it.designbook.utils.LogUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.Map;

/**
 * @author JJ
 * @time 2016/7/30 0030  16:40
 * @desc 连接网络类
 */
public class Controller {

    /**
     * get异步请求
     * @param url 网址
     * @param params 参数map,没有参数给null
     * @param id 分辨当前数据行为
     * @param callback
     */
    public static void doGetAsync(final String url, final Map<String,Object> params, final int id,final Callback<?> callback){
        new Thread(){
            @Override
            public void run() {
                HttpHelper.doGet(url,params,id,callback);
            }
        }.start();
    }

    /**
     * POST异步请求
     * @param url 网址
     * @param params 参数map,没有参数给null
     * @param id 分辨当前数据行为
     * @param callback
     */
    public static void doPostAsync(final String url, final Map<String,Object> params, final int id,final Callback<?> callback){
        LogUtils.vAdv("POST请求地址:"+url);
        new Thread(){
            @Override
            public void run() {
                HttpHelper.doPost(url,params,id,callback);
            }
        }.start();
    }
}
