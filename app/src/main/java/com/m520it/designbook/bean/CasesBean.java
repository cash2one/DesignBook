package com.m520it.designbook.bean;

import java.io.Serializable;

/**
 * @author JJ
 * @time 2016/7/30 0030  14:49
 * @desc ${TODD}
 */
public class CasesBean implements Serializable{
    private static final long serialVersionUID = -6415431435397451002L;
    private String areaName;
    private int counts;
    private String facePic;
    private int hits;
    private int id;
    private String imgUrl;
    private String name;
    private String nick;
    private String priceName;
    private String spaceName;
    private String styleName;
    private int uid;

    @Override
    public String toString() {
        return "CasesBean{" +
                "areaName='" + areaName + '\'' +
                ", counts=" + counts +
                ", facePic='" + facePic + '\'' +
                ", hits=" + hits +
                ", id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", priceName='" + priceName + '\'' +
                ", spaceName='" + spaceName + '\'' +
                ", styleName='" + styleName + '\'' +
                ", uid=" + uid +
                '}';
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
