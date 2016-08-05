package com.m520it.designbook.bean;

/**
 * @author 慕泽
 * @time 2016/7/11  12:43
 * @desc 封装个人信息  然后保存 整个应用的全局变量
 */

public class RLoginResult {
    private long id;//id
    private String userIcon;//用户名
    private int userLevel;//等级
    private String userName;
    private int waitPayCount;
    private int waitReceiveCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWaitPayCount() {
        return waitPayCount;
    }

    public void setWaitPayCount(int waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public int getWaitReceiveCount() {
        return waitReceiveCount;
    }

    public void setWaitReceiveCount(int waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }

    @Override
    public String toString() {
        return "RLoginResult{" +
                "id=" + id +
                ", userIcon='" + userIcon + '\'' +
                ", userLevel=" + userLevel +
                ", userName='" + userName + '\'' +
                ", waitPayCount=" + waitPayCount +
                ", waitReceiveCount=" + waitReceiveCount +
                '}';
    }
}
