package com.zj.example.view.customview6_canvas_save;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Title: CustomViewRect
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/11/11  16:53
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomViewRect extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomViewRect(Context context) {
        this(context, null);
    }

    public CustomViewRect(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setColor(getResources().getColor(android.R.color.black));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //把坐标系移动到中心
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

        for (int i = 0; i < 20; i++) {
            canvas.drawRect(-400, -400, 400, 400, mPaint);
            canvas.scale(0.9f, 0.9f);
        }
    }
}
