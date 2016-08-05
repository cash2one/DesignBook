package com.m520it.designbook.protocol;

/**
 * @author JJ
 * @time 2016/8/2 0002  1:45
 * @desc MyScrollView下拉和滑动监听接口
 */
public interface IOnScrollChangedListener {

    /**
     * 滑动监听
     * @param x
     * @param y
     * @param oldX
     * @param oldY
     */
    void onScrollChanged(int x, int y, int oldX, int oldY);

}
