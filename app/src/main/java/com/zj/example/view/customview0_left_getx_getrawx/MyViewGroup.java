package com.zj.example.view.customview0_left_getx_getrawx;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by zj on 2016/10/22.
 */

public class MyViewGroup extends FrameLayout {
    private TextView textView;

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textView = (TextView) ((ViewGroup) getChildAt(0)).getChildAt(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //getX指当前点击的点距离view左边的横坐标
        float x = event.getX();
        float y = event.getY();

        //值当前view的左边距离父控件左边的距离
        float left = getLeft();

        //rawX是绝对坐标，是相对于屏幕左上角的横坐标，view本身没有getRawX的方法，这个方法一般在MotionEvent对象里使用。
        float rawX = event.getRawX();
        float rawY = event.getRawY();

        System.out.println("x="+x + " ,y="+y +" ,rawX=" + rawX + " ,rawY="+rawY);
        /**
         * logCat打印出来的值getLocationInWindow和getLocationOnScreen是一样的,
         * 因为window(窗口,并不是父控件) = screen
         *
         * 获取控件在window中的坐标
         * contentView < window = screen
         * 为什么会相等呢，因为此时的Window就是包含状态栏+toolbar+contentView的大小
         * 所以
         */
        int[] locationWindow = new int[2];
        textView.getLocationInWindow(locationWindow);


        //获取在整个屏幕内的绝对坐标，注意这个值是要从屏幕顶端算起，也就是包括了通知栏的高度。
        int[] locationScreen= new int[2];
        textView.getLocationOnScreen(locationScreen);

        System.out.println("locationWindow X=" + locationWindow[0] + " ,locationWindow Y=" + locationWindow[1]);
        System.out.println("locationScreen X=" + locationScreen[0] + " ,locationScreen Y=" + locationScreen[1]);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }
}
