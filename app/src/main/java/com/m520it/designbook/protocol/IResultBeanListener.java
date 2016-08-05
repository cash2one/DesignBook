package com.m520it.designbook.protocol;

import com.m520it.designbook.bean.ResultBean;

/**
 * @author JJ
 * @time 2016/7/30 0030  19:48
 * @desc ResultBeanCallBack专用监听回调
 */
public interface IResultBeanListener<M> {
    void onError(String errorMsg, int id);
    void onSucceed(ResultBean<M> response, int id);
}
