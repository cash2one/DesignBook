package com.m520it.designbook.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JJ
 * @time 2016/7/30 0030  18:47
 * @desc okhttp请求帮助类, 方便参数的添加
 */
public class HttpHelper {

    /**
     * 方便地获取一个HashMap<String, Object>
     *
     * @return
     */
    public static HashMap<String, Object> getEmptyMap() {
        return new HashMap<String, Object>();
    }

    /**
     * 通过map参数获取url的参数,例子:获取a=0
     *
     * @param map String="a" object=0
     * @return "a=n"
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {//视图集
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    /**
     * get请求
     *
     * @param url      地址
     * @param params   参数
     * @param callback 带有泛型的callback
     */
    public static void doGet(String url, Map<String, Object> params, int id, Callback<?> callback) {
        LogUtils.vAdv("POST请求地址:"+url+getUrlParamsByMap(params));
        addGetParams(OkHttpUtils.get().url(url), params)
                .id(id)
                .build()
                .execute(callback);
    }

    /**
     * post请求
     *
     * @param url      地址
     * @param params   参数
     * @param callback 带有泛型的callback
     */
    public static void doPost(String url, Map<String, Object> params, int id, Callback<?> callback) {
        addPostParams(OkHttpUtils.post().url(url), params)
                .id(id)
                .build()
                .execute(callback);
    }


    private static GetBuilder addGetParams(GetBuilder build, Map<String, Object> map) {
        if (map == null) {
            return build;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {//视图集
            build.addParams(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return build;
    }

    private static PostFormBuilder addPostParams(PostFormBuilder build, Map<String, Object> map) {
        if (map == null) {
            return build;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {//视图集
            build.addParams(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return build;
    }
}
