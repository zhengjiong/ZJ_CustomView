package com.zj.example.view.customview13_touchmove_offsetLeftAndRight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Title: TouchMoveView
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
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


                offsetLeftAndRight(offsetX);

                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
    }
}
