package com.m520it.designbook.bean;

/**
 * @author JJ
 * @time 2016/7/29 0029  22:14
 * @desc 省份bean
 */
public class ProvinceBean {
    private String provinceName;
    private long provinceID;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public long getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(long provinceID) {
        this.provinceID = provinceID;
    }

    @Override
    public String toString() {
        return "ProvinceBean{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceID=" + provinceID +
                '}';
    }
}
