package com.m520it.designbook.ui;

/**
 * @author JJ
 * @time 2016/8/2 0002  21:44
 * @desc ${TODD}
 */

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.m520it.designbook.protocol.IOnScrollChangedListener;
import com.nineoldandroids.animation.ValueAnimator;


public class ReboundScrollView extends ScrollView {

    private static final String TAG = "ElasticScrollView";

    //移动因子, 是一个百分比, 比如手指移动了100px, 那么View就只移动50px
    //目的是达到一个延迟的效果
    private static final float MOVE_FACTOR = 0.3f;

    //松开手指后, 界面回到正常位置需要的动画时间
    private static final int ANIM_TIME = 300;

    //ScrollView的子View， 也是ScrollView的唯一一个子View
    private View contentView;

    //手指按下时的Y值, 用于在移动时计算移动距离
    //如果按下时不能上拉和下拉， 会在手指移动时更新为当前手指的Y值
    private float startY;

    //用于记录正常的布局位置
    private Rect originalRect = new Rect();

    //手指按下时记录是否可以继续下拉
    private boolean canPullDown = false;

    //在手指滑动的过程中记录是否移动了布局
    private boolean isMoved = false;

    private IOnScrollChangedListener mListener;
    private int mOffset;

    public ReboundScrollView(Context context) {
        super(context);
    }

    public ReboundScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (contentView == null)
            return;

        //ScrollView中的唯一子控件的位置信息, 这个位置信息在整个控件的生命周期中保持不变
        originalRect.set(contentView.getLeft(), contentView.getTop(), contentView
                .getRight(), contentView.getBottom());
    }


    public void setIOnScrollChangedListener(IOnScrollChangedListener listener) {
        mListener = listener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (mListener != null) {
            mListener.onScrollChanged(x, y, oldX, oldY);
        }
    }


    /**
     * 在触摸事件中, 处理上拉和下拉的逻辑
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (contentView == null) {
            return super.dispatchTouchEvent(ev);
        }

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                //判断是否可以上拉和下拉
                canPullDown = isCanPullDown();

                //记录按下时的Y值
                startY = ev.getY();

                break;

            case MotionEvent.ACTION_UP:

                if (!isMoved)
                    break;  //如果没有移动布局， 则跳过执行

                // 开启动画
                ValueAnimator anim = ValueAnimator.ofInt(contentView.getTop(),originalRect.top);

                final int bottom = contentView.getBottom();
                anim.setDuration(ANIM_TIME);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        //动画操作
                        int animatedValue = (int) animation.getAnimatedValue();
                        contentView.layout(originalRect.left, animatedValue,
                                originalRect.right, bottom+animatedValue);

                        if (mListener != null) {
//                            mListener.onScrollPullDown(contentView.getLeft(), contentView.getTop(),
//                                    contentView.getRight(), contentView.getBottom());
                        }
                    }
                });
                anim.start();

                //将标志位设回false
                canPullDown = false;
                isMoved = false;

                break;
            case MotionEvent.ACTION_MOVE:

                //在移动的过程中， 既没有滚动到可以上拉的程度， 也没有滚动到可以下拉的程度
                if (!canPullDown) {
                    startY = ev.getY();
                    canPullDown = isCanPullDown();
                    break;
                }

                //计算手指移动的距离
                float nowY = ev.getY();
                int deltaY = (int) (nowY - startY);

                //是否应该移动布局
                boolean shouldMove =
                        (canPullDown && deltaY > 0);   //可以下拉， 并且手指向下移动

                if (shouldMove) {
                    //计算偏移量
                    mOffset = (int) (deltaY * MOVE_FACTOR);

                    //随着手指的移动而移动布局
                    contentView.layout(originalRect.left, originalRect.top + mOffset,
                            originalRect.right, originalRect.bottom + mOffset);

                    if (mListener != null) {
//                        mListener.onScrollPullDown(contentView.getLeft(), contentView.getTop(),
//                                contentView.getRight(), contentView.getBottom());
                    }

                    isMoved = true;  //记录移动了布局
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    /**
     * 判断是否滚动到顶部
     */
    private boolean isCanPullDown() {
        return getScrollY() == 0 ||
                contentView.getHeight() < getHeight() + getScrollY();
    }


}