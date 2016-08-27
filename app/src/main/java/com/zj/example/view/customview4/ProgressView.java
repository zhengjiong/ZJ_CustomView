package com.zj.example.view.customview4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * create by zhengjiong
 * Date: 2015-05-30
 * Time: 16:37
 */
public class ProgressView extends View{
    private int strokeWidth;
    private Paint mPaintCircle;
    private Paint mPaintProgress;
    private int mProgress;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        strokeWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                10,
                getContext().getResources().getDisplayMetrics());

        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(strokeWidth);
        mPaintCircle.setColor(0xFF81E733);
        //消除鋸齒
        mPaintCircle.setAntiAlias(true);

        mPaintProgress = new Paint();
        mPaintProgress.setStyle(Paint.Style.STROKE);
        mPaintProgress.setStrokeWidth(strokeWidth);
        mPaintProgress.setColor(0xFFED3463);
        mPaintProgress.setAntiAlias(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgress <= 360) {
                    mProgress+=2;

                    postInvalidate();

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //獲取圓心坐標
        int circleX = getMeasuredWidth()/2;

        //半徑需要減去線的寬度
        int radius = circleX - strokeWidth;

        canvas.drawCircle(circleX, circleX, radius, mPaintCircle);

        /**
         * 圓在正方形中的左上右下邊界
         * left
         * The X coordinate of the left side of the rectangle
         * top
         * The Y coordinate of the top of the rectangle
         * right
         * The X coordinate of the right side of the rectangle
         * bottom
         * The Y coordinate of the bottom of the rectangle
         */
        RectF rectF = new RectF(strokeWidth, strokeWidth, 2*circleX - strokeWidth, 2*circleX - strokeWidth);

        canvas.drawArc(rectF, -90, mProgress, false, mPaintProgress);
        //Log.i("zj", "mProgress = " + mProgress);
    }
}
