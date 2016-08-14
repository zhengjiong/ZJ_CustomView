package com.zj.example.view.customview1;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zj.example.view.R;

/**
 *
 * Description: 自定义原型带动画的View
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/14  11:39
 *
 * @author 郑炯
 * @version 1.0
 */
public class CircleView extends View{
    private Paint mPaint;
    private int circleSize;
    private float scale;
    private ObjectAnimator animator;


    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);
        circleSize = typedArray.getDimensionPixelSize(R.styleable.CircleView_circle_size, 100);
        int circleColor = typedArray.getColor(R.styleable.CircleView_circle_color, Color.parseColor("#bdf992"));

        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(circleColor);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, circleSize * scale, mPaint);
    }

    public void startAnimation(){
        animator = ObjectAnimator.ofFloat(this, "scale", 1, 2);
        animator.setDuration(1000);

        // 重复模式, RESTART: 重新开始,    REVERSE:恢复初始状态再开始
        animator.setRepeatMode(ObjectAnimator.REVERSE);

        // 重复次数 -1表示无限循环
        animator.setRepeatCount(-1);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //System.out.println("scale=" + scale + ");
                postInvalidate();
            }
        });

        animator.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
