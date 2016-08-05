package com.m520it.designbook.bean;

/**
 * @author JJ
 * @time 2016/7/30 0030  14:51
 * @desc ${TODD}
 */

import java.io.Serializable;

/**
 * 专题
 */
public  class JiexiBean implements Serializable{
    private static final long serialVersionUID = -1356691492751073363L;
    private int id;
    private String imgTitle1;//主标题
    private String imgTitle2;//副标题
    private String imgUrl;
    private int needLogin;
    private String title;//空""

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgTitle1() {
        return imgTitle1;
    }

    public void setImgTitle1(String imgTitle1) {
        this.imgTitle1 = imgTitle1;
    }

    public String getImgTitle2() {
        return imgTitle2;
    }

    public void setImgTitle2(String imgTitle2) {
        this.imgTitle2 = imgTitle2;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    @Override
    public String toString() {
        return "JiexiBean{" +
                "id=" + id +
                ", imgTitle1='" + imgTitle1 + '\'' +
                ", imgTitle2='" + imgTitle2 + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", needLogin=" + needLogin +
                ", title='" + title + '\'' +
                '}';
    }
}
