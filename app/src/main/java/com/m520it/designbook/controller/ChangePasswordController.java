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
 * @time 2016/8/2  23:48
 * @desc 修改密码控制器
 */

public class ChangePasswordController extends BaseController {
    private Context context;

    public ChangePasswordController(Context mContext) {
        super(mContext);
        this.context = mContext;
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        if (action == IDMessage.MUZE_CONTEXT_CHANGE_ACTION) {
            RResult rResult = changepassword((String) values[0]);
            mListener.onModeChange(IDMessage.MUZE_CONTEXT_CHANGE_ER_ACTION,rResult);
        }
    }

    //请求方法
    private RResult changepassword(String haoma) {
        HashMap<String, String> pasert = new HashMap<>();
        pasert.put("username", haoma);
        try {
            String josn = NetHttpUtil.doPost(NetWorkCons.CHANGE_URL, pasert);
            RResult rResult = JSON.parseObject(josn, RResult.class);
            return rResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
