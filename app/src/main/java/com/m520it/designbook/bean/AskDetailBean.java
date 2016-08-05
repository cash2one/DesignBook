package com.m520it.designbook.bean;

import java.util.List;

/**
 * @desc 问答界面的 里面的每个问答详情bean
 * Created by dragon on 2016/7/30.
 */
public class AskDetailBean {
    /**
     * version : 2.0
     * errorCode : 0
     * errorMsg : success
     * allRows : 3
     * data : {"askInfo":{"id":556612,"title":"这个背景墙上的画 是怎么做上去的呢？墙纸？墙布？谢谢","desc":"","createTime":"14小时前","comments":3,"hits":29,"pics":["http://pic1.shejiben.com/case/2016/03/25/20160325185604-39cb513b-xl.jpg"],"uid":6487804,"nick":"iTt","facePic":"http://img.shejiben.com/headphoto/5.jpg","shareUrl":"http://ask.shejiben.com/discussions/556612.html?from=app"},"commentList":[{"answerId":1539145,"uid":6951907,"nick":"ustenjoy","identity":0,"facePic":"http://img.shejiben.com/headphoto/8.jpg","content":"可以用壁纸，还有一个方法就是彩绘","createTime":"2016-07-30 15:24","imgUrl":"","praiseNum":0,"toUid":0,"toNick":""},{"answerId":1539136,"uid":3238669,"nick":"罗西-6sense装饰设计","identity":1,"facePic":"http://pic.shejiben.com/user/69/sjb_headphoto_3238669.jpg?1467884555","content":"您好，仿真丝壁纸。","createTime":"2016-07-30 15:19","imgUrl":"","praiseNum":0,"toUid":814681,"toNick":"王迪"},{"answerId":1538711,"uid":814681,"nick":"王迪","identity":1,"facePic":"http://pic.shejiben.com/user/81/headphoto_814681.jpg?1410859145","content":"壁纸壁布都可以做出来的","createTime":"2016-07-30 10:20","imgUrl":"","praiseNum":0,"toUid":0,"toNick":""}]}
     */

    private String errorMsg;//错误信息 如果是success 表示成功
    private int allRows; //评论数
    /**
     * askInfo : {"id":556612,"title":"这个背景墙上的画 是怎么做上去的呢？墙纸？墙布？谢谢","desc":"","createTime":"14小时前","comments":3,"hits":29,"pics":["http://pic1.shejiben.com/case/2016/03/25/20160325185604-39cb513b-xl.jpg"],"uid":6487804,"nick":"iTt","facePic":"http://img.shejiben.com/headphoto/5.jpg","shareUrl":"http://ask.shejiben.com/discussions/556612.html?from=app"}
     * commentList : [{"answerId":1539145,"uid":6951907,"nick":"ustenjoy","identity":0,"facePic":"http://img.shejiben.com/headphoto/8.jpg","content":"可以用壁纸，还有一个方法就是彩绘","createTime":"2016-07-30 15:24","imgUrl":"","praiseNum":0,"toUid":0,"toNick":""},{"answerId":1539136,"uid":3238669,"nick":"罗西-6sense装饰设计","identity":1,"facePic":"http://pic.shejiben.com/user/69/sjb_headphoto_3238669.jpg?1467884555","content":"您好，仿真丝壁纸。","createTime":"2016-07-30 15:19","imgUrl":"","praiseNum":0,"toUid":814681,"toNick":"王迪"},{"answerId":1538711,"uid":814681,"nick":"王迪","identity":1,"facePic":"http://pic.shejiben.com/user/81/headphoto_814681.jpg?1410859145","content":"壁纸壁布都可以做出来的","createTime":"2016-07-30 10:20","imgUrl":"","praiseNum":0,"toUid":0,"toNick":""}]
     */

    private DataBean data;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {
        /**
         * id : 556612
         * title : 这个背景墙上的画 是怎么做上去的呢？墙纸？墙布？谢谢
         * desc :
         * createTime : 14小时前
         * comments : 3
         * hits : 29
         * pics : ["http://pic1.shejiben.com/case/2016/03/25/20160325185604-39cb513b-xl.jpg"]
         * uid : 6487804
         * nick : iTt
         * facePic : http://img.shejiben.com/headphoto/5.jpg
         * shareUrl : http://ask.shejiben.com/discussions/556612.html?from=app
         */

        private AskInfoBean askInfo;//问题的一些信息
        /**
         * answerId : 1539145
         * uid : 6951907
         * nick : ustenjoy
         * identity : 0
         * facePic : http://img.shejiben.com/headphoto/8.jpg
         * content : 可以用壁纸，还有一个方法就是彩绘
         * createTime : 2016-07-30 15:24
         * imgUrl :
         * praiseNum : 0
         * toUid : 0
         * toNick :
         */

        private List<CommentListBean> commentList;//所有评论的信息

        public AskInfoBean getAskInfo() {
            return askInfo;
        }

        public void setAskInfo(AskInfoBean askInfo) {
            this.askInfo = askInfo;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class AskInfoBean {
            private int id;//问题id
            private String title;//问题名字
            private String desc;//问题表示
            private String createTime;//创建时间
            private int comments;//评论
            private int hits;//阅读量
            private int uid;//不了解
            private String nick;//提问者昵称
            private String facePic;//我猜是用户头像
            private String shareUrl;//分享
            private List<String> pics;//问题的图片显示


            @Override
            public String toString() {
                return "AskInfoBean{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", desc='" + desc + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", comments=" + comments +
                        ", hits=" + hits +
                        ", uid=" + uid +
                        ", nick='" + nick + '\'' +
                        ", facePic='" + facePic + '\'' +
                        ", shareUrl='" + shareUrl + '\'' +
                        ", pics=" + pics +
                        '}';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public int getHits() {
                return hits;
            }

            public void setHits(int hits) {
                this.hits = hits;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getFacePic() {
                return facePic;
            }

            public void setFacePic(String facePic) {
                this.facePic = facePic;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public List<String> getPics() {
                return pics;
            }

            public void setPics(List<String> pics) {
                this.pics = pics;
            }
        }

        public static class CommentListBean {
            private int answerId;//回答者id
            private int uid;
            private String nick;//昵称
            private int identity;//0是普通人员 1是设计师
            private String facePic;//用户头像
            private String content;//答案
            private String createTime;//创建时间
            private String imgUrl;
            private int praiseNum;//点赞数
            private int toUid;
            private String toNick;//回复的是谁

            public int getAnswerId() {
                return answerId;
            }

            public void setAnswerId(int answerId) {
                this.answerId = answerId;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public int getIdentity() {
                return identity;
            }

            public void setIdentity(int identity) {
                this.identity = identity;
            }

            public String getFacePic() {
                return facePic;
            }

            public void setFacePic(String facePic) {
                this.facePic = facePic;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(int praiseNum) {
                this.praiseNum = praiseNum;
            }

            public int getToUid() {
                return toUid;
            }

            public void setToUid(int toUid) {
                this.toUid = toUid;
            }

            public String getToNick() {
                return toNick;
            }

            public void setToNick(String toNick) {
                this.toNick = toNick;
            }

            @Override
            public String toString() {
                return "CommentListBean{" +
                        "answerId=" + answerId +
                        ", uid=" + uid +
                        ", nick='" + nick + '\'' +
                        ", identity=" + identity +
                        ", facePic='" + facePic + '\'' +
                        ", content='" + content + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", imgUrl='" + imgUrl + '\'' +
                        ", praiseNum=" + praiseNum +
                        ", toUid=" + toUid +
                        ", toNick='" + toNick + '\'' +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "AskDetailBean{" +
                "errorMsg='" + errorMsg + '\'' +
                ", allRows=" + allRows +
                ", data=" + data +
                '}';
    }
}
