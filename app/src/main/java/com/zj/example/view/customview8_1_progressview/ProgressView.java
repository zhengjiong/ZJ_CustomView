package com.zj.example.view.customview8_1_progressview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.zj.example.view.R;

/**
 * Created by zhengjiong
 * date: 2017/7/10 21:28
 */

public class ProgressView extends View implements ValueAnimator.AnimatorUpdateListener {
    private Paint mDrawablePaint;
    private Paint mLinePaint;
    private Paint mTextPaint;

    private BitmapDrawable bitmapDrawable;
    private PorterDuffXfermode mDuffXfermode;
    private int endX;
    private ObjectAnimator mObjectAnimator;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.mipmap.vector_certified);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#f54757"));
        mTextPaint.setTextSize(62);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.parseColor("#f54757"));
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(12);

        mDrawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDrawablePaint.setDither(true);

        mDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        System.out.println("onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        System.out.println("onDetachedFromWindow");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int drawableWidth = bitmapDrawable.getIntrinsicWidth();

        int count = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), mDrawablePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawLine(drawableWidth,
                height / 2,
                width - drawableWidth,
                height / 2,
                mLinePaint
        );
        canvas.drawBitmap(
                bitmapDrawable.getBitmap(),
                0,
                height / 2 - drawableWidth / 2,
                mDrawablePaint
        );
        canvas.drawBitmap(
                bitmapDrawable.getBitmap(),
                width / 2 - drawableWidth / 2,
                height / 2 - bitmapDrawable.getIntrinsicHeight() / 2,
                mDrawablePaint
        );
        canvas.drawBitmap(
                bitmapDrawable.getBitmap(),
                width - drawableWidth,
                height / 2 - bitmapDrawable.getIntrinsicHeight() / 2,
                mDrawablePaint
        );
        String text = "测试1";
        float textWidth = mTextPaint.measureText(text);
        canvas.drawText(text, 0, height / 2 + bitmapDrawable.getIntrinsicHeight(), mTextPaint);
        canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + bitmapDrawable.getIntrinsicHeight(), mTextPaint);
        canvas.drawText(text, width - textWidth, height / 2 + bitmapDrawable.getIntrinsicHeight(), mTextPaint);

        mDrawablePaint.setXfermode(mDuffXfermode);

        mDrawablePaint.setColor(Color.parseColor("#00b1b1"));
        canvas.drawRect(new RectF(0, 0, endX, height), mDrawablePaint);

        mDrawablePaint.setXfermode(null);
        canvas.restoreToCount(count);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("onSizeChanged");
        mObjectAnimator = ObjectAnimator.ofInt(this, "endX", 0, w);
        mObjectAnimator.addUpdateListener(this);
        mObjectAnimator.setInterpolator(new DecelerateInterpolator());
        mObjectAnimator.setDuration(5000);
        mObjectAnimator.setPropertyName("endX");
        mObjectAnimator.start();
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

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        invalidate();
    }
}
