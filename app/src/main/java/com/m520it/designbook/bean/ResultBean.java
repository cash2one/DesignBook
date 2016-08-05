package com.m520it.designbook.bean;

import java.io.Serializable;

/**
 * @author JJ
 * @time 2016/7/30 0030  11:15
 * @desc 案例请求获取的json
 */
public class ResultBean<T> implements Serializable{

    private static final long serialVersionUID = 5904731199153283687L;
    private int allRows;//用途不明
    private T data;//数据
    private int errorCode;//错误码
    //如果请求错误?
    private String errorMsg;//错误信息/成功
    private String version;//版本

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "allRows=" + allRows +
                ", data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
