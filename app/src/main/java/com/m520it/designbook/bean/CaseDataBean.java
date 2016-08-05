package com.m520it.designbook.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author JJ
 * @time 2016/7/30 0030  14:47
 * @desc ${TODD}
 */
public class CaseDataBean implements Serializable{
    private static final long serialVersionUID = 2071130482692637083L;

    /**
     * 数据
     */
    /**
     * id : 0
     * imgUrl : http://pic.shejiben.com/hot_sjb/394_8273.jpg?1469673681
     * module :
     * needLogin : 0
     * title :
     * url : http://m.shejiben.com/images/list/1561266/
     */

    private List<BannerBean> banner;//广告
    /**
     * areaName : 210㎡
     * counts : 27
     * facePic : http://pic.shejiben.com/user/19/sjb_headphoto_5333219.jpg?1448439935
     * hits : 380
     * id : 3192604
     * imgUrl : http://pic1.shejiben.com/case/2016/07/28/20160728172955-1f7b1caf-h5.jpg
     * name : 伊斯坦布尔阳光天健城
     * nick : 华夏设计院
     * priceName : 90万元
     * spaceName : 别墅
     * styleName : 地中海
     * uid : 5333219
     */

    private List<CasesBean> cases;//案例
    /**
     * id : 1558916
     * imgTitle1 : 床头柜
     * imgTitle2 : 温馨而有创意
     * imgUrl : http://pic1.shejiben.com/hot_sjb/395_6572.jpg?1468378327
     * needLogin : 0
     * title :
     */

    private List<JiexiBean> jiexi;//专题

    public List<BannerBean> getBanner() {
        return banner;
    }


    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<CasesBean> getCases() {
        return cases;
    }

    public void setCases(List<CasesBean> cases) {
        this.cases = cases;
    }

    public List<JiexiBean> getJiexi() {
        return jiexi;
    }

    public void setJiexi(List<JiexiBean> jiexi) {
        this.jiexi = jiexi;
    }

    @Override
    public String toString() {
        return "CaseDataBean{" +
                "banner=" + banner +
                ", cases=" + cases +
                ", jiexi=" + jiexi +
                '}';
    }

}