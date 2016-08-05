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
 * @time 2016/8/2  20:02
 * @desc 注册的控制器
 */

public class ReginterController extends BaseController {
    private Context context;

    public ReginterController(Context mContext) {
        super(mContext);
        this.context = mContext;
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        if (action == IDMessage.MUZE_CONTEXT_REGISTER_ACTION) {
            RResult rResult = zhuche((String) values[0], (String) values[1]);
            mListener.onModeChange(IDMessage.MUZE_CONTEXT_REGISTER_ER_ACTION,rResult);
        }
    }

    //写一个网络请求数据的方法
    private RResult zhuche(String name, String paw) {
        HashMap<String, String> pasetr = new HashMap<>();
        pasetr.put("username", name);
        pasetr.put("pwd", paw);
        try {
            String json = NetHttpUtil.doPost(NetWorkCons.REGISTER_URL, pasetr);
            RResult rResult = JSON.parseObject(json, RResult.class);
            return rResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
