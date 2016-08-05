package com.m520it.designbook.event;

/**
 * @author JJ
 * @time 2016/7/31 0031  20:00
 * @desc ${TODD}
 */
public class WebClickEvent {
    private String url;

    public WebClickEvent(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
