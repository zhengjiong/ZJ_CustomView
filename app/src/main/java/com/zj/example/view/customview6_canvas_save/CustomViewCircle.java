package com.zj.example.view.customview6_canvas_save;

import android.content.Context;
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
        mPaint.setStrokeWidth(10);

        TODO: 2016/11/14
    }
}
