package com.zj.example.view.customview12_touchmove_getx;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Title: TouchMoveView
 * Description:
 *
 * getTop:获取View自身的顶边到其父布局顶边的距离
 * getLeft:获取View自身的左边到其父布局左边的距离
 * getRight:获取View自身的右边到其父布局左边的距离
 * getBottom:获取View自身底边到其父布局的顶边距离
 *
 * MotionEvent提供的方法:
 * getX:获取点击事件距离控件左边的距离,即视图坐标
 * getY:获取点击事件距离控件顶边的距离,即视图坐标
 *
 * getRawX:获取点击事件距离整个屏幕左边的距离, 即绝对坐标
 * getRawY:获取点击事件距离整个屏幕顶边的距离, 即绝对坐标
 *
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/28  08:57
 *
 * @author 郑炯
 * @version 1.0
 */
public class TouchMoveView extends View {


    private int lastX;
    private int lastY;
    private int width;
    private int height;

    public TouchMoveView(Context context) {
        this(context, null);
    }

    public TouchMoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchMoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = getMeasuredWidth();
        height = getMeasuredHeight();
        System.out.println("onSizeChanged width=" + width);
        System.out.println("onSizeChanged height=" + height);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        int currentX = (int) event.getX();
        int currentY = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:

                int offsetX = currentX - lastX;
                int offsetY = currentY - lastY;

                int left = getLeft() + offsetX;
                int right = getRight() + offsetX;
                int top = getTop() + offsetY;
                int bottom = getBottom() + offsetY;

                if (left < 0) {
                    left = 0;
                    right = left + width;
                } else if (right > ((View) getParent()).getMeasuredWidth()) {
                    right = ((View) getParent()).getMeasuredWidth();
                    left = right - width;
                }

                if (top < 0) {
                    top = 0;
                    bottom = top + height;
                } else if (bottom > ((View) getParent()).getMeasuredHeight()) {
                    bottom = ((View) getParent()).getMeasuredHeight();
                    top = bottom - height;
                }

                layout(left, top, right, bottom);
                break;
        }
        return true;
    }
}
