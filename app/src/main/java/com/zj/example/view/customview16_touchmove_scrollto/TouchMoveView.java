package com.zj.example.view.customview16_touchmove_scrollto;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Title: TouchMoveView
 * Description:
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/28  20:19
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
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = currentX;
                lastY = currentY;

                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = currentX - lastX;
                int offsetY = currentY - lastY;

                /**
                 * 因为scrollBy和scrollTo移动的是View中的内容而不是View本事,如果是ViewGroup就是移动内部的子View
                 * 所以必须要getParent,让ParentView来移动子View。
                 *
                 * 如果将offsetX和offsetY设置为正数,那么子View将向坐标轴的负方向移动, 如果负数则将向
                 * 坐标轴的正方向移动,所以要使用-offsetX, -offsetY
                 */
                //((View) getParent()).scrollBy(-offsetX, -offsetY);
                View parent = (View) getParent();
                parent.scrollTo(parent.getScrollX() - offsetX, parent.getScrollY() - offsetY);
                break;
        }
        return true;
    }
}
