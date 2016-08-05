package com.m520it.designbook.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.m520it.designbook.bean.RResult;
import com.m520it.designbook.config.IDMessage;
import com.m520it.designbook.config.NetWorkCons;
import com.m520it.designbook.utils.NetHttpUtil;

import java.util.HashMap;

/**
 * @author 慕泽
 * @time 2016/7/31  20:16
 * @desc 登入的网络请求
 */

public class LogInController extends BaseController {
    private Context mContext;
    public LogInController(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        if (action == IDMessage.MUZE_CONTENT_LOGIN_ACTION) {
            RResult result = getlogin((String) values[0], (String) values[1]);
            //返回一个对象回去
            mListener.onModeChange(IDMessage.MUZE_CONTENT_LOGIN_ER_ACTION,result);
        }
    }

    //做一个网络请求的方法
    private RResult getlogin(String user, String mima) {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", user);
        params.put("pwd", mima);
        try {
            String json = NetHttpUtil.doPost(NetWorkCons.LOGIN_URL, params);
            RResult rResult = JSON.parseObject(json, RResult.class);
            return rResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
