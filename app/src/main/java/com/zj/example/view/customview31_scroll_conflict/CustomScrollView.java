package com.zj.example.view.customview31_scroll_conflict;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.zj.example.view.customview30_scroll_conflict.demo1.CustomHorizontalScrollView;

/**
 * 因为ScrollView默认onInterceptTouchEvent在move的时候会返回true,
 * 所以onTouch的move事件会被ScrollView消费掉,通过重写onInterceptTouchEvent方法,在
 * 的时候不拦截,从而解决滑动冲突问题
 * Created by zj on 2017/5/31.
 */

public class CustomScrollView extends ScrollView {
    private static final String TAG = CustomScrollView.class.getSimpleName();

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
       boolean result = super.onInterceptTouchEvent(ev);
        switch (ev.getAction())
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

        return result;
    }
}
