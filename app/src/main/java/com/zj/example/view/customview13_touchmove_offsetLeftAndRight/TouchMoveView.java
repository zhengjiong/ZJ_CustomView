package com.zj.example.view.customview13_touchmove_offsetLeftAndRight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Title: TouchMoveView
 * Description:
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/28  11:38
 *
 * @author 郑炯
 * @version 1.0
 */
public class TouchMoveView extends View {
    private int lastX;
    private int lastY;

    public TouchMoveView(Context context) {
        super(context);
    }

    public TouchMoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchMoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        /**
         * 当前触摸点坐标
         */
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();

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

                //offset不会调用onLayout方法
                offsetLeftAndRight(offsetX);

                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //offset不会调用onLayout方法
        System.out.println("onLayout top=" + top);
    }
}
