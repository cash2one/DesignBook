package com.m520it.designbook.bean;

/**
 * @author JJ
 * @time 2016/8/1 0001  15:30
 * @desc 图片bean
 */
public class ImgBean {
    private String comment;//图片描述
    private int height;//图片高度
    private int id;//图片id
    private String imgUrl;//url
    private String name;//图片名
    private int width;//图片宽度

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "ImgBean{" +
                "comment='" + comment + '\'' +
                ", height=" + height +
                ", id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", width=" + width +
                '}';
    }
}