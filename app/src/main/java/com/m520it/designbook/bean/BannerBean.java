package com.m520it.designbook.bean;

/**
 * @author JJ
 * @time 2016/7/30 0030  14:48
 * @desc ${TODD}
 */

import java.io.Serializable;

/**
 * 广告
 */
public class BannerBean implements Serializable{
    private static final long serialVersionUID = 63454850594952393L;
    private int id;//广告的id
    private String imgUrl;
    private String module;//不明,默认空
    private int needLogin;//是否需要登录 0不需要 1需要
    private String title;//标题
    private String url;//跳转链接吧

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getNeedLogin() {
        return needLogin;
    }

    public void setNeedLogin(int needLogin) {
        this.needLogin = needLogin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", module='" + module + '\'' +
                ", needLogin=" + needLogin +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
