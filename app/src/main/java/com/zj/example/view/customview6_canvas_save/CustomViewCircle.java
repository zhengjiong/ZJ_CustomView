package com.zj.example.view.customview6_canvas_save;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zj on 2016/11/14.
 */

public class CustomViewCircle extends View{
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomViewCircle(Context context) {
        this(context, null);
    }

    public CustomViewCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //把坐标移动到view的中心
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

        canvas.drawCircle(0, 0, 180, mPaint);
        canvas.drawCircle(0, 0, 170, mPaint);

        for (int i = 0; i <= 360; i=i+30) {
            canvas.drawLine(0, 180, 0, 170, mPaint);

            //旋转30度,默认旋转中心是坐标原点
            canvas.rotate(30);
        }
    }
}
