package com.zj.example.view.customview6_canvas_save;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Title: CustomViewLine
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/11/11  16:53
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomViewLine extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomViewLine(Context context) {
        this(context, null);
    }

    public CustomViewLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //画背景色
        canvas.drawColor(getContext().getResources().getColor(android.R.color.black));

        //保存没有旋转之前的画布
        canvas.save();

        //旋转30度
        canvas.rotate(30);

        //画一条与水平线, 但是已经旋转了画布所以是一条30度的线
        canvas.drawLine(0, 0, 1000, 0, mPaint);

        //还原到没有旋转的画布
        canvas.restore();

        //画一条水平线
        canvas.drawLine(0, 0, 1000, 0, mPaint);
    }
}
