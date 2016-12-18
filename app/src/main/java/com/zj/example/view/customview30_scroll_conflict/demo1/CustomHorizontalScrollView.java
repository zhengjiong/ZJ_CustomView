package com.zj.example.view.customview30_scroll_conflict.demo1;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * 因为HorizontalScrollView默认onInterceptTouchEvent在move的时候会返回true,
 * 所以onTouch的move事件会被HorizontalScrollview消费掉,通过重写onInterceptTouchEvent方法,在
 * viewpager没有滑动到最后一页的时候不拦截,从而解决滑动冲突问题
 * <p>
 * CreateTime:16/12/16  13:50
 *
 * @author 郑炯
 * @version 1.0
 */

public class CustomHorizontalScrollView extends HorizontalScrollView {
    private static final String TAG = CustomHorizontalScrollView.class.getSimpleName();

    private float downX;
    private float downY;

    public CustomHorizontalScrollView(Context context) {
        super(context);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean result = super.dispatchTouchEvent(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN " + result);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE " + result);
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP " + result);
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * 因为HorizontalScrollView默认onInterceptTouchEvent会返回false,
     * 而且Viewpager默认onTouchEvent会返回true, 所以点击事件会被内部的Viewpager消费掉,
     * scrollView就不能滚动了
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean b = super.onTouchEvent(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent ACTION_DOWN " + b);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent ACTION_MOVE " + b);
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent ACTION_UP " + b);
                break;
            default:
                break;
        }

        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                float xDistance = event.getX() - downX;
                ViewPager viewPager = (ViewPager) ((ViewGroup) getChildAt(0)).getChildAt(0);
                int currentIndex = viewPager.getCurrentItem();
                //System.out.println("xDistance = " + xDistance);

                //判断是否左右滑动
                if (Math.abs(xDistance) * 0.5 > Math.abs(event.getY() - downY)) {
                    /**
                     * 滑动到最左端,并且viewpager还没有滑动到最后一页,就不拦截
                     * getScrollX == 0 代表已经滑动到最左端,
                     * 然后判断viewpager还没有滑动到最后一页,
                     * 就不拦截(return false),交给子view处理
                     */
                    if (getScrollX() == 0 && currentIndex < viewPager.getAdapter().getCount() - 1) {
                        return false;
                    }

                    /**
                     * 滑动到最左端并且viewpager是最后一张,而且是从左向右滑,就不拦截
                     * xDistance > 0代表从左向右滑,
                     * xDistance > Math.abs(event.getY() - downY)代表左右滑动
                     * getScrollX == 0 代表已经滑动到最左端
                     */
                    if (xDistance > 0 && getScrollX() == 0 && currentIndex == viewPager.getAdapter().getCount() - 1) {
                        return false;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            default:

        }
        return super.onInterceptTouchEvent(event);
        /*boolean result = super.onInterceptTouchEvent(event);
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onInterceptTouchEvent ACTION_DOWN " + result);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onInterceptTouchEvent ACTION_MOVE " + result);
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onInterceptTouchEvent ACTION_UP " + result);
                break;
            default:
                break;
        }

        return true;*/
    }
}
