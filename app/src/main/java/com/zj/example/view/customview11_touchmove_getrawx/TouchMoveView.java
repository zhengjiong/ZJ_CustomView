package com.zj.example.view.customview11_touchmove_getrawx;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Title: TouchMoveView
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
 * Description:使用getRaw方式(绝对坐标系)
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/27  21:58
 *
 * @author 郑炯
 * @version 1.0
 */
public class TouchMoveView extends View {


    private int lastX;
    private int lastY;

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
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        System.out.println("onLayout top=" + top);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        /**
         * 当前触摸点坐标
         */
        int currentX = (int) event.getRawX();
        int currentY = (int) event.getRawY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                /**
                 * 计算偏移量
                 */
                int offsetX = currentX - lastX;
                int offsetY = currentY - lastY;

                //设置当前view的位置, layout内部会调用onLayout方法
                layout(
                        getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY
                );

                //重新设置初始坐标
                lastX = currentX;
                lastY = currentY;
                break;
            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
    }
}
