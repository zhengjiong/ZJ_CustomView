package com.zj.example.view.customview21_stroke_style;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zj on 2016/9/1.
 */
public class StrokeStyleView extends View {
    private Paint paint;


    public StrokeStyleView(Context context) {
        this(context, null);
    }

    public StrokeStyleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StrokeStyleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);//防止图像抖动, 画面会更加柔和
        paint.setStyle(Paint.Style.STROKE);

        /**
         * 会影响始末端
         *
         * 该方法用来设置我们画笔的 笔触风格 ，比如：ROUND，表示是圆角的笔触。那么什么叫笔触呢，
         * 其实很简单，就像我们现实世界中的笔，如果你用圆珠笔在纸上戳一点，那么这个点一定是个圆，
         * 即便很小，它代表了笔的笔触形状，如果我们把一支铅笔笔尖削成方形的，
         * 那么画出来的线条会是一条弯曲的“矩形”，这就是笔触的意思。除了ROUND，
         * Paint.Cap还提供了另外两种类型：SQUARE和BUTT
         */
        paint.setStrokeCap(Paint.Cap.SQUARE);

        /**
         * ROUND:让画的线圆滑
         * 设置结合处的样子，Miter:结合处为锐角， Round:结合处为圆弧：BEVEL：结合处为直线。
         *
         * 这个方法用于设置接合处的形态，就像你用代码画了一条线，
         * 但是这条线其实是由无数条小线拼接成的，拼接处的形状就由该方法指定。可选参数是：BEVEL，MITER，ROUND。
         */
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int paddingLeft = dp2px(16);
        int paddingRight = dp2px(16);

        int startY = dp2px(16);

        /**
         * 当画笔样式（style）为STROKE或FILL_OR_STROKE时(空心样式时)，设置笔刷的粗细度。
         */
        paint.setStrokeWidth(dp2px(30));
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(paddingLeft, startY, getMeasuredWidth() - paddingLeft, startY, paint);

        startY += dp2px(40);
        paint.setStrokeWidth(dp2px(30));
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(paddingLeft, startY, getMeasuredWidth() - paddingLeft, startY, paint);


        paint.setStrokeWidth(dp2px(8));
        paint.setStrokeCap(Paint.Cap.ROUND);//矩形拐弯的地方会是圆角的
        startY += dp2px(40);
        canvas.drawRect(paddingLeft, startY, getMeasuredWidth() - paddingLeft, startY + dp2px(100), paint);
    }

    int dp2px(int dp){
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5);
    }
}
