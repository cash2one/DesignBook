package com.m520it.designbook.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 慕泽
 * @time 2016/7/10  23:19
 * @desc 网络请求的get方式 和post方式
 */

public class NetHttpUtil {
    public static String doGet(String urlPath){
        try {
            URL url=new URL(urlPath);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode()==200) {
                //				获取数据流 转换成String
                InputStream is = conn.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                return reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String doPost(String urlPath,HashMap<String, String> params){
        try {
            URL url=new URL(urlPath);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //			将请求参数设置到请求头里面 设置之前应该启动输出的开关
            conn.setDoOutput(true);
            String paramsStr="";
            for (Map.Entry<String, String> entry:params.entrySet()) {
                paramsStr+=("&"+entry.getKey()+"="+entry.getValue());
            }
            paramsStr=paramsStr.substring(1);
            conn.getOutputStream().write(paramsStr.getBytes());
            if (conn.getResponseCode()==200) {
                //				获取数据流 转换成String
                InputStream is = conn.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                return reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
