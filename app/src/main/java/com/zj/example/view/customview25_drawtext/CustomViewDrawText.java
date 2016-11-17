package com.zj.example.view.customview25_drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by zj on 2016/11/17.
 */

public class CustomViewDrawText extends View {
    private Paint mPaint;
    public CustomViewDrawText(Context context) {
        this(context, null);
    }

    public CustomViewDrawText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewDrawText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String str = "ABCDE";

        //1.第一类(drawText)
        // 参数分别为 (文本 基线x坐标 基线y 画笔)
        canvas.drawText(str, 200, 500, mPaint);

        // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
        canvas.drawText(str, 1, 3, 200, 600, mPaint);

        //2.第二类(drawPosText),方法已经过时不建议使用
        canvas.drawPosText(
                str,
                new float[]{
                        200, 700,    // 第一个字符位置
                        250, 750,    // 第二个字符位置
                        300, 800,    // ...
                        350, 850,
                        400, 900
                },
                mPaint);

        //3.第三类(drawTextOnPath)
        //这个在path的demo中
    }
}
