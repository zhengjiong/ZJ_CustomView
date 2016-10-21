package com.zj.example.view.customview20_touchmove_gesturedetector;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Description: 自己判断是左右滑动还是上下滑动
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/10/21  15:23
 * GestureDetectorTestView
 * @author 郑炯
 * @version 1.0
 */
public class TouchDirectionView extends View {

    private float actionDownX;
    private float actionDownY;

    private int mTouchSlop;

    public TouchDirectionView(Context context) {
        this(context, null);
    }

    public TouchDirectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchDirectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        String action = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                actionDownX = event.getX();
                actionDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                float xDiff = Math.abs(Math.abs(event.getX()) - Math.abs(actionDownX));
                float yDiff = Math.abs(Math.abs(event.getY()) - Math.abs(actionDownY));
                if (xDiff > mTouchSlop && xDiff * 0.5f > yDiff) {
                    System.out.println("左右滑动 xDiff=" + xDiff + " ,yDiff=" + yDiff);
                } else if (yDiff > mTouchSlop && yDiff * 0.5f > xDiff) {
                    System.out.println("上下滑动 xDiff=" + xDiff + " ,yDiff=" + yDiff);
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                actionDownX = 0;
                actionDownY = 0;
                break;
        }
        boolean result = super.onTouchEvent(event);
        //System.out.println(action + " result=" + result);

        return result;
    }
}
