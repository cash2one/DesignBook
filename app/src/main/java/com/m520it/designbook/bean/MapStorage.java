package com.m520it.designbook.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 梁家明
 * @time 2016/7/30  19:42
 * @desc ${TODD}
 */
public class MapStorage implements Serializable {

    /**
     * allRows : 32163
     * data : [{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/28/20160728133453-8958a958-la.jpg","id":2376195,"name":"古典欧式风卧室设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727163858-40fbd79c-la.jpg","id":2375119,"name":"现代风格卫浴设计图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727160436-bef0b685-la.jpg","id":2374800,"name":"清新新古典风格卫浴装饰图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726102146-0eaad118-la.jpg","id":2372699,"name":"混搭风格厨房布局效果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/23/20160723100315-3153a01a-la.jpg","id":2369592,"name":"家装简约美式风格厨房装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722151612-7b183094-la.jpg","id":2369021,"name":"家居混搭风格餐厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722143853-95d34aef-la.jpg","id":2368886,"name":"北欧风格家居客厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/18/20160718102717-4d2711ac-la.jpg","id":2362471,"name":"现代简约风格餐厅装饰图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/18/20160718102706-ff273a16-la.jpg","id":2362470,"name":"时尚现代风格餐厅效果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/18/20160718102543-8b191a90-la.jpg","id":2362464,"name":"现代风格家居客厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/17/20160717142637-f649c559-la.jpg","id":2361871,"name":"家居美式风格别墅客厅设计图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/16/20160716112312-c1d458a8-la.jpg","id":2360364,"name":"清新现代风格卧室装修"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/14/20160714182320-d79e7c34-la.jpg","id":2358832,"name":"美式风格卧室设计图欣赏"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/14/20160714094037-8f5791a3-la.jpg","id":2357803,"name":"家居美式风格奢华卧室装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/28/20160728133451-bc8aea1e-la.jpg","id":2376194,"name":"古典欧式风卫浴设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/28/20160728133449-daee0d9a-la.jpg","id":2376193,"name":"古典欧式风书房设计"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/27/20160727145420-02b55659-la.jpg","id":2374615,"name":"北欧风格家居书房设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726165302-f7016755-la.jpg","id":2373559,"name":"奢华新古典风格客厅装饰图"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/26/20160726164242-e254fe39-la.jpg","id":2373498,"name":"家装行动风格客厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726153712-ac3d9160-la.jpg","id":2373208,"name":"混搭风格卫浴装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726103012-a43fcac2-la.jpg","id":2372776,"name":"时尚美式风格卫浴装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722153644-babed759-la.jpg","id":2369113,"name":"美式风格厨房橱柜设计图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722151552-110f047c-la.jpg","id":2369018,"name":"混搭风格家居餐厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722143913-7f30fcc1-la.jpg","id":2368896,"name":"家居北欧风格厨房设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722143845-4f8837bf-la.jpg","id":2368882,"name":"清新北欧风格餐厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/17/20160717142622-9728130f-la.jpg","id":2361869,"name":"奢华美式别墅客厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/16/20160716112304-1fc02b65-la.jpg","id":2360360,"name":"现代风格二居室餐厅设计"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/16/20160716112251-24253de7-la.jpg","id":2360354,"name":"时尚现代风格家居客厅装修效果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/15/20160715171234-3db0701b-la.jpg","id":2359877,"name":"美式风格餐厅装饰图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/14/20160714182311-2a38fc49-la.jpg","id":2358827,"name":"美式风格卧室设计案例"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/14/20160714182305-f3948449-la.jpg","id":2358825,"name":"时尚美式风格卧室装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/28/20160728173244-c4fa9e85-la.jpg","id":2376719,"name":"清雅地中海风儿童房装修"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/28/20160728133446-d4552cae-la.jpg","id":2376192,"name":"古典欧式风休闲区设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/28/20160728133442-2f45afa0-la.jpg","id":2376190,"name":"古典欧式风餐厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/28/20160728121226-dcd0986e-la.jpg","id":2376076,"name":"优雅家居混搭楼梯效果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727160424-fe4665b5-la.jpg","id":2374792,"name":"奢华新古典风格别墅书房装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726165130-8b47f66d-la.jpg","id":2373555,"name":"新古典风格家居餐厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726164841-39ab6b50-la.jpg","id":2373547,"name":"新古典风格卧室装修案例"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/26/20160726153700-9cf7ebd7-la.jpg","id":2373199,"name":"混搭风格壁炉设计效果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/25/20160725164311-926e1246-la.jpg","id":2372252,"name":"简约混搭风格卫浴装修"}]
     * errorCode : 0
     * errorMsg : success
     * version : 2.0
     */
    private int allRows;
    private List<DataEntity> data;
    private int errorCode;
    private String errorMsg;
    private String version;

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getAllRows() {
        return allRows;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getVersion() {
        return version;
    }

    public static class DataEntity {
        /**
         * imgUrl : http://pic.shejiben.com/i/upload/2016/07/28/20160728133453-8958a958-la.jpg
         * id : 2376195
         * name : 古典欧式风卧室设计
         */
        private String imgUrl;
        private int id;
        private String name;

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
