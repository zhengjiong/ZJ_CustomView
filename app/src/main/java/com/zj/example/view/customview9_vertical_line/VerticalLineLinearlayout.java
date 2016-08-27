package com.zj.example.view.customview9_vertical_line;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.zj.example.view.R;


/**
 * Description: 左边有竖线的Linearlayout
 * Copyright:Copyright(c)2016
 * CreateTime:16/8/19  16:34
 *
 * @author 郑炯
 * @version 1.0
 */
public class VerticalLineLinearlayout extends LinearLayout {

    private Paint paint;
    private int leftMargin;


    public VerticalLineLinearlayout(Context context) {
        this(context, null);
    }

    public VerticalLineLinearlayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalLineLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalLineLinearLayout, 0, defStyleAttr);
        leftMargin = typedArray.getDimensionPixelSize(R.styleable.VerticalLineLinearLayout_leftMargin, (int) TypedValue.complexToDimension(27, getResources().getDisplayMetrics()));
        typedArray.recycle();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#cccccc"));
        paint.setStyle(Paint.Style.FILL);

        int strokeWidth = (int) TypedValue.complexToDimension(2, getResources().getDisplayMetrics());

        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int childCount = getChildCount();
        if (childCount <= 1) return;

        int childTotalHeight = 0;
        int childHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            childHeight = view.getMeasuredHeight();
            childTotalHeight += childHeight;
        }
        int startX = getPaddingLeft() + leftMargin;

        canvas.drawLine(
                startX,
                childHeight / 2, startX, childTotalHeight - (childHeight / 2), paint);
    }
}
