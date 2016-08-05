package com.m520it.designbook.bean;

/**
 * @author JJ
 * @time 2016/8/1 0001  15:29
 * @desc ${TODD}
 */
/**
 * 设计师信息bean
 */
public class MemberInfoBean {
    private String facePic;//icon
    private int goodlevel;//等级
    private String goodlevelCN;//""
    private int identity;//0
    private String nick;//名字
    private int rz;//0
    private String rzCN;//""
    private int uid;//uid

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    public int getGoodlevel() {
        return goodlevel;
    }

    public void setGoodlevel(int goodlevel) {
        this.goodlevel = goodlevel;
    }

    public String getGoodlevelCN() {
        return goodlevelCN;
    }

    public void setGoodlevelCN(String goodlevelCN) {
        this.goodlevelCN = goodlevelCN;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getRz() {
        return rz;
    }

    public void setRz(int rz) {
        this.rz = rz;
    }

    public String getRzCN() {
        return rzCN;
    }

    public void setRzCN(String rzCN) {
        this.rzCN = rzCN;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "MemberInfoBean{" +
                "facePic='" + facePic + '\'' +
                ", goodlevel=" + goodlevel +
                ", goodlevelCN='" + goodlevelCN + '\'' +
                ", identity=" + identity +
                ", nick='" + nick + '\'' +
                ", rz=" + rz +
                ", rzCN='" + rzCN + '\'' +
                ", uid=" + uid +
                '}';
    }
}

