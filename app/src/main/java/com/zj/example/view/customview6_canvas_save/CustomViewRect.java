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
            canvas.drawRect(-300, -300, 300, 300, mPaint);
            /**
             * 画布缩放(基于坐标0,0来缩放, 如果要指定缩放坐标可以使用:
             *
             * canvas.scale(0.9f, 0.9f, 20, 20);
             *
             * 前两个参数是相同的分别为x轴和y轴的缩放比例。而第二种方法比前一种多了两个参数，用来控制缩放中心位置的。
             *
             * 取值范围(n)	说明
             * [-∞, -1)	先根据缩放中心放大n倍，再根据中心轴进行翻转
             * -1	根据缩放中心轴进行翻转
             * (-1, 0)	先根据缩放中心缩小到n，再根据中心轴进行翻转
             * 0	不会显示，若sx为0，则宽度为0，不会显示，sy同理
             * (0, 1)	根据缩放中心缩小到n
             * 1	没有变化
             * (1, +∞)	根据缩放中心放大n倍
             */
            canvas.scale(0.9f, 0.9f);

        }
    }
}
