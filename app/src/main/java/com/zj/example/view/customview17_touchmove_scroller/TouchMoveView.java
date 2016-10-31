package com.zj.example.view.customview17_touchmove_scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Description: 使用Scroller
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/9/4  15:49
 *
 * @author 郑炯
 * @version 1.0
 */
public class TouchMoveView extends View {

    private int lastX;
    private int lastY;

    private Scroller scroller;

    public TouchMoveView(Context context) {
        this(context, null);
    }

    public TouchMoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchMoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        scroller = new Scroller(getContext());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //startScroll不会调用onLayout方法
        System.out.println("onLayout top=" + top);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            int finalX = scroller.getFinalX();
            int finalY = scroller.getFinalY();

            View parent = (View) getParent();
            parent.scrollTo(parent.getScrollX() - finalX, parent.getScrollY() - finalY);

            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int) event.getX();
                int currentY = (int) event.getY();

                int offsetX = currentX - lastX;
                int offsetY = currentY - lastY;

                View parent = (View) getParent();
                System.out.println(" offsetX=" + offsetX + "  ,parent.getScrollX()=" + parent.getScrollX());

                scroller.startScroll(scroller.getFinalX(), scroller.getFinalY(), offsetX, offsetY);

                invalidate();
                break;
            default:

        }

        return true;
    }
}
