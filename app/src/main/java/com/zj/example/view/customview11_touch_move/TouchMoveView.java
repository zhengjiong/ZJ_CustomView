package com.zj.example.view.customview11_touch_move;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Title: TouchMoveView
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
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

                //设置当前view的位置
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
