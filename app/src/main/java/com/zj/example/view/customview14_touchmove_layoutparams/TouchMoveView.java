package com.zj.example.view.customview14_touchmove_layoutparams;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

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
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        System.out.println("onLayout top=" + top);
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

                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();

                int leftMargin = params.leftMargin + offsetX;
                int topMargin = params.topMargin + offsetY;

                params.setMargins(leftMargin, topMargin, params.rightMargin, params.bottomMargin);

                //setLayoutParams内部会调用requestLayout,所以会调用onLayout方法
                setLayoutParams(params);
                break;
        }
        return true;
    }
}
