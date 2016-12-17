package com.zj.example.view.customview0_touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.util.Log;
import android.widget.TextView;

/**
 * Title: MyButton
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/12/15  16:54
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomView extends TextView {
    private static final String TAG = CustomView.class.getSimpleName();

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 安卓为了保证所有的事件都是被一个 View 消费的，对第一次的事件( ACTION_DOWN )进行了特殊判断，
     * View 只有消费了 ACTION_DOWN 事件，才能接收到后续的事件(可点击控件会默认消费所有事件)，
     * 并且会将后续所有事件传递过来，不会再传递给其他 View，除非上层 View 进行了拦截。
     * 如果上层 View 拦截了当前正在处理的事件，会收到一个 ACTION_CANCEL，表示当前事件已经结束，后续事件不会再传递过来。
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean b = super.onTouchEvent(event);
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent ACTION_DOWN " + b);
                /**
                 * actionDown返回false后, 后续的move和up事件将不会再接收到了
                 */
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent ACTION_MOVE" + b);
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent ACTION_UP" + b);
                break;
            default:
                break;
        }

        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
