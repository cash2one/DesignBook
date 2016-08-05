package com.m520it.designbook.config;

/**
 * @author JJ
 * @time 2016/7/30 0030  11:11
 * @desc 存放网络访问url
 */
public class NetWorkCons {
    //案例fragment请求地址
//    public static final String URL_CASE = "http://www.shejiben.com/mobile/index.php?to8to_token=" + "7025378_c70c6ba00e3b5685afd1c1db6c5370dd&uid=7025378&action=index&module=cases&";
   // public static final String URL_ASK = "http://www.shejiben.com/mobile/index.php?pageSize=30&uid=0&action=index&module=ask&page=1&%S&";
    public static final String BASE_URL_ASK = "http://www.shejiben.com/mobile/index.php?pageSize=30&uid=0&action=index&module=ask&page=1&askType=";
    public static final String URL_ASK_ACTIVE = BASE_URL_ASK+"active&";
    public static final String URL_ASK_NEW = BASE_URL_ASK+"new&";
    public static final String URL_ASK_WAIT = BASE_URL_ASK+"wait&";

    public static final String URL_CASE = "http://www.shejiben.com/mobile/index.php?to8to_token=" +
            "7025378_523fc5a3a3e71f3765d454a744c6a684&uid=7025378&action=index&module=cases&";
    /**
     * 专题activity
     */
    public static final String URL_SUBJECT = "http://www.shejiben.com/mobile/index.php?";
    public static final String URL_BASE="http://www.shejiben.com/mobile/index.php?";


    public static  final String Test="http://www.shejiben.com/mobile/index.php?pageSize=30&uid=0&action=index&module=ask&page=1&askType=active&";

    public static final String GALLERY_URL = "http://www.shejiben.com/mobile/index.php?uid=0&module=works&style=0&page=1&pageSize=40&action=list&space=0&kind=home&";
//    http://www.shejiben.com/mobile/index.php?pageSize=30&uid=0&action=index&module=ask&page=1&askType=active&
    private static  final String ASK_DETAIL_URL = "http://www.shejiben.com/mobile/index.php?" +
            "pageSize=30&id=%S&uid=0&action=detail&module=ask&page=1&";
    private static  final String GALLERY_DETAIL_URL = "http://www.shejiben.com/mobile/index.php?id=%S&uid=0&action=detail&module=works&askNum=5&colNum=5&";

    public static final String CHANGE_URL = "http://mall.520it.com/reset";
    public static final String LOGIN_URL = "http://mall.520it.com/login";
    public static final String REGISTER_URL = "http://mall.520it.com/regist";
    public static final String TOUXIANG_URL = "http://img.shejiben.com/headphoto/129.jpg";//头像
//发现findFragment,,
    public static  final String SEARCH_DESIGNER_URL = "http://www.shejiben.com/mobile/index.php";//?pageSize=30&uid=0&action=list&module=sjs&

    public static final String TIJIAOXUQIU_URL = "http://m.shejiben.com/appZb?";//提交需求


    /**
     * 传入一个id 拿到对应的问题内容
     * @param id 问题详情对饮的id
     * @return
     */
    public static String getAskDetailUrl(String id){
        return  ASK_DETAIL_URL.replace("%S",id);
    }
    public static String getGalleryDetailUrl(String id){
        return  GALLERY_DETAIL_URL.replace("%S",id);
    }
//    public static String getSearchDesignerUrl(int page){
//        return  SEARCH_DESIGNER_URL.replace("%S",page+"");
//    }
//-------------------

}
