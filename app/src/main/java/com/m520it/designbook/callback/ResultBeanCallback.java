package com.m520it.designbook.callback;

import com.m520it.designbook.bean.ResultBean;
import com.m520it.designbook.protocol.IResultBeanListener;
import com.m520it.designbook.utils.LogUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;

/**
 * @author JJ
 * @time 2016/7/30 0030  19:43
 * @desc 针对ResultBean的CallBack, 使用的时候指定泛型
 */

public abstract class ResultBeanCallback<M> extends Callback<ResultBean<M>> {

    private IResultBeanListener<M> mListener;

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        LogUtils.vAdv(e.getLocalizedMessage() + "/" + e.getMessage());
        if (mListener != null) {
            mListener.onError("",id);
        }
    }

    @Override
    public void onResponse(ResultBean<M> response, int id) {
        if (mListener != null) {
            if ("success".equals(response.getErrorMsg())) {
               mListener.onSucceed(response,id);
            } else {
               mListener.onError(response.getErrorMsg(),id);
            }
        }

    }
    public void setResultListener(IResultBeanListener<M> listener) {
        mListener = listener;
    }
}
