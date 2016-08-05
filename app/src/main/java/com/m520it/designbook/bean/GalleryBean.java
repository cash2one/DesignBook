package com.m520it.designbook.bean;

import java.util.List;

/**
 * @author 梁家明
 * @time 2016/7/31  17:00
 * @desc ${TODD}
 */
public class GalleryBean {

    /**
     * allRows : 32210
     * data : [{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727225920-7e6a1d18-la.jpg","id":2375483,"name":"家居地中海格调餐厅设计图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727225913-ef020b30-la.jpg","id":2375481,"name":"家居地中海格调厨房设计图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727160428-f4d61d0c-la.jpg","id":2374795,"name":"奢华新古典风格家居卧室设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727154200-34b3bbc7-la.jpg","id":2374700,"name":"简约风格别墅客厅设计图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726153712-974bc426-la.jpg","id":2373209,"name":"混搭风格休闲区装修"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/26/20160726102231-b901c2d3-la.jpg","id":2372712,"name":"温馨混搭风格卧室装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726102223-d329a1df-la.jpg","id":2372710,"name":"简约混搭风格卧室设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/25/20160725164257-028d9feb-la.jpg","id":2372247,"name":"现代混搭风格书房装修"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/25/20160725105017-487990cb-la.jpg","id":2371434,"name":"现代风格厨房装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/24/20160724124306-7e3ee4c3-la.jpg","id":2370830,"name":"温馨北欧风格卧室装饰图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/24/20160724124138-742317eb-la.jpg","id":2370826,"name":"家居北欧格调休闲区设计"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/22/20160722145953-1087c981-la.jpg","id":2368974,"name":"美式风格餐厅设装饰图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722141035-47b9f05f-la.jpg","id":2368764,"name":"温馨美式风格餐厅装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722141024-365bdc6e-la.jpg","id":2368758,"name":"清新美式客厅装修案例"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/22/20160722141022-8fc0b042-la.jpg","id":2368757,"name":"简约美式风格客厅装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/20/20160720101721-fe3f560d-la.jpg","id":2365085,"name":"家居现代风卧室装修"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/19/20160719140548-77ee660a-la.jpg","id":2364128,"name":"家居美式风格卫浴设计案例"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/19/20160719140543-d3a8127f-la.jpg","id":2364125,"name":"家居美式风格卧室装修效果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/28/20160728173213-863d6291-la.jpg","id":2376717,"name":"清雅地中海风书房装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/28/20160728173143-002182ba-la.jpg","id":2376715,"name":"清雅地中海风卫浴装修"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/28/20160728121236-af68544a-la.jpg","id":2376085,"name":"优雅家居混搭卫浴效果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727225956-53b2ff85-la.jpg","id":2375492,"name":"家居地中海格调卧室效果图"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/27/20160727225923-00fb2d06-la.jpg","id":2375484,"name":"家居地中海格调客厅效果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/27/20160727160417-fc7ac625-la.jpg","id":2374789,"name":"新古典风格休闲区装修"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/27/20160727160410-76a20503-la.jpg","id":2374786,"name":"新古典风格别墅餐厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726102213-42ccc034-la.jpg","id":2372707,"name":"时尚混搭风格餐厅装饰图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/26/20160726102210-0812d23b-la.jpg","id":2372706,"name":"混搭风格餐厅装修案例"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/25/20160725104959-1d902e64-la.jpg","id":2371430,"name":"现代风格家居厨房设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/24/20160724124049-3ffe337a-la.jpg","id":2370823,"name":"清新北欧风格客厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/24/20160724101421-24c03140-la.jpg","id":2370574,"name":"现代美式风格厨房装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/20/20160720130817-1d666a22-la.jpg","id":2365604,"name":"美式风格别墅餐厅装饰图"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/20/20160720130806-7c2fac28-la.jpg","id":2365603,"name":"典雅美式风格餐厅设计"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/20/20160720130758-57c878fa-la.jpg","id":2365602,"name":"美式风格家居客厅设计"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/20/20160720130750-52ec7f95-la.jpg","id":2365601,"name":"奢华美式别墅客厅装修效果图"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/20/20160720101636-dfad198b-la.jpg","id":2365079,"name":"简约现代风格餐厅装修"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/20/20160720101605-489a37c1-la.jpg","id":2365071,"name":"现代风格家居客厅设计图"},{"imgUrl":"http://pic1.shejiben.com/case/2016/07/19/20160719140540-deb4d493-la.jpg","id":2364123,"name":"简约美式风格卧室设计图"},{"imgUrl":"http://pic1.shejiben.com/case/2016/07/17/20160717142815-166b41da-la.jpg","id":2361893,"name":"奢华家居美式别墅卧室装饰图"},{"imgUrl":"http://pic.shejiben.com/i/upload/2016/07/28/20160728121233-c87cc34d-la.jpg","id":2376080,"name":"优雅家居混搭书房果图"},{"imgUrl":"http://pic1.shejiben.com/i/upload/2016/07/28/20160728113425-bc23d37c-la.jpg","id":2375877,"name":"时尚家居混搭书房装饰图"}]
     * errorCode : 0
     * errorMsg : success
     * version : 2.0
     */
    private int allRows;
    private List<DataEntity> list;
    private int errorCode;
    private String errorMsg;
    private String version;

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public List<DataEntity> getList() {
        return list;
    }

    public void setList(List<DataEntity> list) {
        this.list = list;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataEntity {
        /**
         * imgUrl : http://pic1.shejiben.com/i/upload/2016/07/27/20160727225920-7e6a1d18-la.jpg
         * id : 2375483
         * name : 家居地中海格调餐厅设计图
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
