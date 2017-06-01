package com.zj.example.view.customview31_scroll_conflict;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.baidu.mapapi.map.TextureMapView;

/**
 * 解决scrollview嵌套baidumap方式1:重写scrollview的onInterceptTouchEvent方法。
 *
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
                //Log.e(TAG, "onTouchEvent ACTION_DOWN " + b);
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.e(TAG, "onTouchEvent ACTION_MOVE " + b);
                break;
            case MotionEvent.ACTION_UP:
                //Log.e(TAG, "onTouchEvent ACTION_UP " + b);
                break;
            default:
                break;
        }

        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        Log.i(TAG, "x=" + x + " ,y=" + y);
        View textureMapView = ((ViewGroup) getChildAt(0)).getChildAt(0);
        if (textureMapView instanceof TextureMapView) {
            Rect rect = new Rect();
            //getLocalVisibleRect的作用是获取视图本身可见的坐标区域，坐标以自己的左上角为原点（0，0）
            textureMapView.getLocalVisibleRect(rect);

            Log.i(TAG, "1rect.left=" + rect.left + " ,rect.right=" + rect.right + " ,rect.top=" + rect.top + " ,rect.bottom=" + rect.bottom + " ,height=" + rect.height());
            if (rect.bottom > 0) {
                //重设高度, 因为滑动后top会变
                rect.bottom = rect.height();
                rect.top = 0;
            }
            Log.i(TAG, "2rect.left=" + rect.left + " ,rect.right=" + rect.right + " ,rect.top=" + rect.top + " ,rect.bottom=" + rect.bottom + " ,height=" + rect.height());

            boolean isContains = rect.contains(x, y);
            System.out.println("isContains = " + isContains);

            if (isContains) {
                return false;
            }
        }

        /*switch (ev.getAction()) {
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
        }*/

        return super.onInterceptTouchEvent(ev);
    }
}
