package com.zj.example.view.customview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 自定义控件学习demo1: http://blog.csdn.net/aigestudio/article/details/41212583
 * Created by zhengjiong on 15/10/31.
 */
public class CustomView extends View implements Runnable{
    private Paint mPaint;
    private int radio = 250;
    private Context mContext;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();

        mPaint.setAntiAlias(true);

        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#30A0F6"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int[] size = MeasureUtils.getScreenSize(mContext);

        canvas.drawCircle(size[0]/2, size[0]/2, radio, mPaint);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(35);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (radio >= 250) {
                radio = 0;
            } else {
                radio += 8;
            }
            postInvalidate();
        }
    }
}
