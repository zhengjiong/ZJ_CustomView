package com.zj.example.view.customview19_touchmove_gesturedetector;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description: 使用GestureDetector判断是左右滑动还是上下滑动
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/10/21  15:23
 * GestureDetectorTestView
 * @author 郑炯
 * @version 1.0
 */
public class GestureDetectorTestView extends View {

    private GestureDetectorCompat mGestureDetector;

    public GestureDetectorTestView(Context context) {
        this(context, null);
    }

    public GestureDetectorTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureDetectorTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        //使用GestureDetector判断是左右滑动还是上下滑动
        mGestureDetector = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

                if (Math.abs(distanceY) > Math.abs(distanceX)) {
                    System.out.println("上下滑动 distanceX=" + distanceX + " ,distanceY=" + distanceY);
                    return true;
                } else if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    System.out.println("左右滑动 distanceX=" + distanceX + " ,distanceY=" + distanceY);
                    return true;
                }
                return false;
            }

        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        boolean result = super.onTouchEvent(event);

        String action = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                break;
        }

        //System.out.println(action + " result=" + result);

        return result;
    }
}
