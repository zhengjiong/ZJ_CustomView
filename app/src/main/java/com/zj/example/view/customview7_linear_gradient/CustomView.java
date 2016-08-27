package com.zj.example.view.customview7_linear_gradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zj on 2016/8/23.
 */
public class CustomView extends TextView {
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;

    private int mWidth;
    private int mTranslate;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("onSizeChanged width=" + getMeasuredWidth());

        /**
         * LinearGradient需要使用宽度参数, 所以只能在onSizeChanged中设置,onMeasure也可以但是不好,
         * 因为onMeasure会执行多次
         */
        mWidth = getMeasuredWidth();

        if (mWidth != 0){
            mLinearGradient = new LinearGradient(
                    0,
                    0,
                    mWidth,
                    0,
                    new int[]{Color.RED, 0xffffffff, Color.RED},
                    null, Shader.TileMode.CLAMP);

            mPaint.setShader(mLinearGradient);

            mGradientMatrix = new Matrix();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //System.out.println("onDraw mTranslate=" + mTranslate);
        if (mLinearGradient != null) {

            mTranslate += mWidth /20;
            if (mTranslate > 1 * mWidth) {
                mTranslate = -mWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);

            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);

            canvas.drawText("android", 0, 7, 0, getMeasuredHeight() / 2, mPaint);
        }
    }
}
