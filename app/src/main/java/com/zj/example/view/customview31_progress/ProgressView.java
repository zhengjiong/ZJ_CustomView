package com.zj.example.view.customview31_progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zj.example.view.utils.DisplayUtils;

/**
 * Title: ProgressView
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:17/4/29  16:56
 *
 * @author 郑炯
 * @version 1.0
 */
public class ProgressView extends View {
    private Paint mTextPaint;
    private Paint mBackgroundPaint;
    private int rx;
    private int padding;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rx = DisplayUtils.dp2px(getContext(), 3);
        padding = DisplayUtils.dp2px(getContext(), 3);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#333333"));
        mTextPaint.setTextSize(16);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.parseColor("#a0000000"));
        mBackgroundPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * DisplayUtils.dp2px(getContext(), 5)
         * x方向上的圆角半径。
         * y方向上的圆角半径。
         */

        canvas.drawRoundRect(2, 2, getMeasuredWidth() - 2, getMeasuredHeight() - 2, rx, rx, mBackgroundPaint);
    }
}
