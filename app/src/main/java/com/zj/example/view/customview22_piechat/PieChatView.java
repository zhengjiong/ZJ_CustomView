package com.zj.example.view.customview22_piechat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Title: PieChatView
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/11/11  10:56
 *
 * @author 郑炯
 * @version 1.0
 */
public class PieChatView extends View {
    private Paint mPaint;

    public PieChatView(Context context) {
        this(context, null);
    }

    public PieChatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * startAngle  // 开始角度
         * sweepAngle  // 扫过角度(增加的角度)
         * useCenter   // 是否使用中心
         */
        RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        mPaint.setColor(Color.parseColor("#f92b45"));
        canvas.drawArc(rectF, 0, 30, true, mPaint);
        mPaint.setColor(Color.parseColor("#cafbba"));
        canvas.drawArc(rectF, 30, 60, true, mPaint);//30代码起始角度, 60代表的是增加的角度, 最终角度应该是30+60=90度
        mPaint.setColor(Color.parseColor("#637f8d"));
        canvas.drawArc(rectF, 90, 90, true, mPaint);
        mPaint.setColor(Color.parseColor("#fabf43"));
        canvas.drawArc(rectF, 180, 40, true, mPaint);
        mPaint.setColor(Color.parseColor("#45495d"));
        canvas.drawArc(rectF, 220, 40, true, mPaint);
        mPaint.setColor(Color.parseColor("#95fbc9"));
        canvas.drawArc(rectF, 260, 100, true, mPaint);
    }
}
