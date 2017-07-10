package com.zj.example.view.customview8_1_progressview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.zj.example.view.R;

/**
 * Created by zhengjiong
 * date: 2017/7/10 21:28
 */

public class ProgressView extends View {
    private Paint mDrawablePaint;
    private BitmapDrawable bitmapDrawable;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.mipmap.vector_certified);

        mDrawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDrawablePaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int drawableWidth = bitmapDrawable.getIntrinsicWidth();
        canvas.drawBitmap(
                bitmapDrawable.getBitmap(),
                0,
                height / 2 - drawableWidth / 2,
                mDrawablePaint
        );
        canvas.save();
        canvas.translate(width / 2 - drawableWidth / 2, 0);
        canvas.drawBitmap(
                bitmapDrawable.getBitmap(),
                0,
                height / 2 - bitmapDrawable.getIntrinsicHeight() / 2,
                mDrawablePaint
        );
        canvas.restore();
        canvas.translate(width - drawableWidth, 0);
        canvas.drawBitmap(
                bitmapDrawable.getBitmap(),
                0,
                height / 2 - bitmapDrawable.getIntrinsicHeight() / 2,
                mDrawablePaint
        );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int finalHeight = 0;

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        width = View.resolveSize(width, widthMeasureSpec);

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                finalHeight = height;
                break;
            default:
                finalHeight = convertDpToPixel(100) + getPaddingTop() + getPaddingBottom();
                break;
        }

        setMeasuredDimension(width, finalHeight);
    }

    private int convertDpToPixel(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
