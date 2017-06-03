package com.zj.example.view.customview30_scroll_conflict.demo2;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 为HorizontalScrollView默认onInterceptTouchEvent在move的时候会返回true,所以onTouch的move
 * 事件会被HorizontalScrollview消费掉,通过重写CustomViewPager的dispatchTouchEvent方法,
 * 在viewpager没有滑动到最后一页的时候不让父View不拦截(parent.requestDisallowInterceptTouchEvent(true),
 * 从而解决滑动冲突问题
 *
 * CreateTime:16/12/12  16:17
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomViewPager extends ViewPager {
    private static final String TAG = CustomViewPager.class.getSimpleName();

    private float downX;
    private float downY;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        CustomHorizontalScrollView scrollView = (CustomHorizontalScrollView) getParent().getParent();
        int action = event.getAction();
        //boolean result = super.dispatchTouchEvent(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //Log.e(TAG, "dispatchTouchEvent ACTION_DOWN " + result);

                //如果scrollview已经滚动了就还是让Scrollview拦截
                if (scrollView.getScrollX() != 0) {
                    //让父控件重新拦截
                    scrollView.requestDisallowInterceptTouchEvent(false);
                } else {
                    //让父控件不拦截
                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.e(TAG, "dispatchTouchEvent ACTION_MOVE " + result);
                float currentX = event.getX();
                float currentY = event.getY();


                //判断是否水平滑动
                if (Math.abs(currentX - downX) * 0.5 > Math.abs(currentY - downY)) {

                    int currentIndex = getCurrentItem();
                    int itemCount = getAdapter().getCount();

                    //判断HorizontalScrollViews是不是已经滚动到最左边,然后是不是从右向左滑动,并且Viewpager已经滑到了最后一张,,才让HorizontalScrollView可以滑动
                    if (scrollView.getScrollX() == 0 && currentX - downX < 0 && currentIndex == itemCount - 1) {
                        scrollView.requestDisallowInterceptTouchEvent(false);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //Log.e(TAG, "dispatchTouchEvent ACTION_UP " + result);
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean result = super.onInterceptTouchEvent(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //Log.e(TAG, "onInterceptTouchEvent ACTION_DOWN " + result);
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.e(TAG, "onInterceptTouchEvent ACTION_MOVE " + result);
                break;
            case MotionEvent.ACTION_UP:
                //Log.e(TAG, "onInterceptTouchEvent ACTION_UP " + result);
                break;
            default:
                break;
        }

        return result;
    }
}
