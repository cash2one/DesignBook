package com.m520it.designbook.bean;

/**
 * @author 吕朝
 * @version $Rev$
 * @time 2016/8/3 11:58
 * @desc ${TODO}
 * @updateAuthor $Author$
 * @uodateDate $Date$
 */
public class DesignerBean {

    /**
     * goodlevelCN : F知名
     * uid : 490460
     * goodlevel : 5
     * facePic : http://pic.shejiben.com/user/60/sjb_headphoto_490460.jpg?1421072167
     * priceUnit :
     * yuyueNum : 144
     * city : 深圳
     * rz : 1
     * priceRange : 收费面议
     * nick : 王五平
     * qiandanNum : 0
     * shen : 广东
     * rzCN : 个人认证
     * appraiseNum : 0
     */

    private String goodlevelCN;
    private long uid;
    private int goodlevel;
    private String facePic;
    private String priceUnit;
    private int yuyueNum;
    private String city;
    private int rz;
    private String priceRange;
    private String nick;
    private int qiandanNum;
    private String shen;
    private String rzCN;
    private int appraiseNum;

    public String getGoodlevelCN() {
        return goodlevelCN;
    }

    public void setGoodlevelCN(String goodlevelCN) {
        this.goodlevelCN = goodlevelCN;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getGoodlevel() {
        return goodlevel;
    }

    public void setGoodlevel(int goodlevel) {
        this.goodlevel = goodlevel;
    }

    public String getFacePic() {
        return facePic;
    }

    public void setFacePic(String facePic) {
        this.facePic = facePic;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public int getYuyueNum() {
        return yuyueNum;
    }

    public void setYuyueNum(int yuyueNum) {
        this.yuyueNum = yuyueNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRz() {
        return rz;
    }

    public void setRz(int rz) {
        this.rz = rz;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getQiandanNum() {
        return qiandanNum;
    }

    public void setQiandanNum(int qiandanNum) {
        this.qiandanNum = qiandanNum;
    }

    public String getShen() {
        return shen;
    }

    public void setShen(String shen) {
        this.shen = shen;
    }

    public String getRzCN() {
        return rzCN;
    }

    public void setRzCN(String rzCN) {
        this.rzCN = rzCN;
    }

    public int getAppraiseNum() {
        return appraiseNum;
    }

    public void setAppraiseNum(int appraiseNum) {
        this.appraiseNum = appraiseNum;
    }

    @Override
    public String toString() {
        return "DesignerBean{" +
                "goodlevelCN='" + goodlevelCN + '\'' +
                ", uid=" + uid +
                ", goodlevel=" + goodlevel +
                ", facePic='" + facePic + '\'' +
                ", priceUnit='" + priceUnit + '\'' +
                ", yuyueNum=" + yuyueNum +
                ", city='" + city + '\'' +
                ", rz=" + rz +
                ", priceRange='" + priceRange + '\'' +
                ", nick='" + nick + '\'' +
                ", qiandanNum=" + qiandanNum +
                ", shen='" + shen + '\'' +
                ", rzCN='" + rzCN + '\'' +
                ", appraiseNum=" + appraiseNum +
                '}';
    }
}
