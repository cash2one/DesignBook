package com.m520it.designbook.bean;

/**
 * @author JJ
 * @time 2016/7/29 0029  22:16
 * @desc 城市bean
 */
public class CityBean {
    private String cityName;
    private long cityID;
    private long provinceID;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCityID() {
        return cityID;
    }

    public void setCityID(long cityID) {
        this.cityID = cityID;
    }

    public long getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(long provinceID) {
        this.provinceID = provinceID;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "cityName='" + cityName + '\'' +
                ", cityID=" + cityID +
                ", provinceID='" + provinceID + '\'' +
                '}';
    }
}
