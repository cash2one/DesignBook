package com.m520it.designbook.bean;

import java.util.List;

/**
 * Created by dragon on 2016/8/1.
 */
    public  class GalleryDataBean {
        private int id;
        private String name;
        private int colNum;
        private int askNum;
        private String imgUrl;
        private String comment;
        private String shareUrl;
        /**
         * uid : 6583826
         * nick : 83039e4f
         * facePic : http://pic.shejiben.com/user/26/sjb_headphoto_6583826.jpg?1463641968
         * feeRand : 100~5005143/5e737c73
         * phoneNum400 : 4006808509
         * extensionNum : 9999
         */

        private MemberInfoBean memberInfo;
        private List<?> colList;
        private List<?> askList;

    @Override
    public String toString() {
        return "GalleryDataBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colNum=" + colNum +
                ", askNum=" + askNum +
                ", imgUrl='" + imgUrl + '\'' +
                ", comment='" + comment + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", memberInfo=" + memberInfo +
                ", colList=" + colList +
                ", askList=" + askList +
                '}';
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getColNum() {
            return colNum;
        }

        public void setColNum(int colNum) {
            this.colNum = colNum;
        }

        public int getAskNum() {
            return askNum;
        }

        public void setAskNum(int askNum) {
            this.askNum = askNum;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public MemberInfoBean getMemberInfo() {
            return memberInfo;
        }

        public void setMemberInfo(MemberInfoBean memberInfo) {
            this.memberInfo = memberInfo;
        }

        public List<?> getColList() {
            return colList;
        }

        public void setColList(List<?> colList) {
            this.colList = colList;
        }

        public List<?> getAskList() {
            return askList;
        }

        public void setAskList(List<?> askList) {
            this.askList = askList;
        }

        public static class MemberInfoBean {
            private int uid;
            private String nick;
            private String facePic;
            private String feeRand;
            private String phoneNum400;
            private String extensionNum;

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

            public String getFeeRand() {
                return feeRand;
            }

            public void setFeeRand(String feeRand) {
                this.feeRand = feeRand;
            }

            public String getPhoneNum400() {
                return phoneNum400;
            }

            public void setPhoneNum400(String phoneNum400) {
                this.phoneNum400 = phoneNum400;
            }

            public String getExtensionNum() {
                return extensionNum;
            }

            public void setExtensionNum(String extensionNum) {
                this.extensionNum = extensionNum;
            }
        }
    }
