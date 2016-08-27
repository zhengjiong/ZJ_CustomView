package com.zj.example.view.customview10_delivery_status;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zj.example.view.R;


/**
 * Description: 订单物流状态
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/8/19  08:07
 *
 * @author 郑炯
 * @version 1.0
 */
public class DeliveryStatusHorizontalView extends ViewGroup {
    private int mStage = 1;

    private int minHeight;
    private int lineHeight;
    private int textSize;
    private int offsetX;

    private int drawbleWidth;
    private int drawableHeight;

    private Paint mPaintLine;
    private Paint mPaintText;

    private Drawable drawableBlue;
    private Drawable drawableGray;

    private int colorLight;
    private int colorDeep;

    public DeliveryStatusHorizontalView(Context context) {
        this(context, null);
    }

    public DeliveryStatusHorizontalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeliveryStatusHorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DeliveryStatusHorizontalView, defStyleAttr, 0);

        colorLight = typedArray.getColor(R.styleable.DeliveryStatusHorizontalView_line_light, 0xffcccccc);
        colorDeep = typedArray.getColor(R.styleable.DeliveryStatusHorizontalView_line_deep, 0xff3F51B5);

        typedArray.recycle();

        minHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        lineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());


        drawableBlue = ContextCompat.getDrawable(context, R.mipmap.ic_circle_blue);
        drawableGray = ContextCompat.getDrawable(context, R.mipmap.ic_circle_gray);

        drawbleWidth = drawableBlue.getIntrinsicWidth();
        drawableHeight = drawableBlue.getIntrinsicHeight();

        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize(textSize);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintLine.setStrokeWidth(lineHeight);
        mPaintLine.setColor(colorLight);

        addTextView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = minHeight;

        if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(widthMeasureSpec)) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }

        if (MeasureSpec.UNSPECIFIED != MeasureSpec.getMode(heightMeasureSpec)) {
            height = Math.min(MeasureSpec.getSize(heightMeasureSpec), height);
        }
        setMeasuredDimension(width, height + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offsetX = getChildAt(0).getMeasuredWidth() / 2 - drawbleWidth / 2;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(0);
            int top = getMeasuredHeight() - childView.getMeasuredHeight() - getPaddingBottom();
            int bottom = getMeasuredHeight() - getPaddingBottom();

            switch (i) {
                case 0:
                    getChildAt(i).layout(
                            getPaddingLeft(),
                            top,
                            getPaddingLeft() + childView.getMeasuredWidth(),
                            bottom
                    );
                    break;
                case 1:
                    getChildAt(i).layout(
                            getMeasuredWidth() / 2 - childView.getMeasuredWidth() / 2,
                            top,
                            getMeasuredWidth() / 2 + childView.getMeasuredWidth() / 2,
                            bottom
                    );
                    break;

                case 2:
                    getChildAt(i).layout(
                            getMeasuredWidth() - getPaddingRight() - childView.getMeasuredWidth(),
                            top,
                            getMeasuredWidth() - getPaddingRight(),
                            bottom
                    );
                    break;
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLeft("1", canvas);
        drawCenter("2", canvas);
        drawRight("3", canvas);

        if (mStage == 0) {
            mPaintLine.setColor(colorLight);
            drawLine(canvas);
        } else if (mStage == 1) {
            mPaintLine.setColor(colorDeep);
            drawLeftLine(canvas);

            mPaintLine.setColor(colorLight);
            drawRightLine(canvas);
        } else {
            mPaintLine.setColor(colorDeep);
            drawLine(canvas);
        }


    }

    private void drawRightLine(Canvas canvas) {
        canvas.drawLine(
                getMeasuredWidth() / 2 + drawbleWidth / 2,
                getPaddingTop() + drawableHeight / 2,
                getMeasuredWidth() - getPaddingRight() - drawbleWidth - offsetX,
                getPaddingTop() + drawableHeight / 2, mPaintLine
        );
    }

    private void drawLeftLine(Canvas canvas) {
        canvas.drawLine(
                getPaddingLeft() + drawbleWidth + offsetX,
                getPaddingTop() + drawableHeight / 2,
                getMeasuredWidth() / 2 - drawbleWidth / 2,
                getPaddingTop() + drawableHeight / 2, mPaintLine
        );
    }

    private void drawLine(Canvas canvas) {
        int intrinsicWidth = drawableBlue.getIntrinsicWidth();
        int intrinsicHeight = drawableBlue.getIntrinsicHeight();

        canvas.drawLine(
                getPaddingLeft() + intrinsicWidth,
                getPaddingTop() + intrinsicHeight / 2,
                getMeasuredWidth() - getPaddingRight() - intrinsicWidth,
                getPaddingTop() + intrinsicHeight / 2, mPaintLine
        );
    }

    private void drawRight(String number, Canvas canvas) {
        Drawable drawable;

        if (mStage < 2) {
            drawable = drawableGray;
        } else {
            drawable = drawableBlue;
        }

        int left = getMeasuredWidth() - getPaddingRight() - drawable.getIntrinsicWidth() - offsetX;
        Rect rect = new Rect(
                left,
                getPaddingTop(),
                left + drawable.getIntrinsicWidth(),
                getPaddingTop() + drawable.getIntrinsicHeight()
        );

        drawable.setBounds(rect);
        drawable.draw(canvas);

        drawText(number, canvas, rect);
    }

    private void drawCenter(String number, Canvas canvas) {
        int drawableWidth = drawableBlue.getIntrinsicWidth();
        int drawableHeight = drawableBlue.getIntrinsicHeight();
        Drawable drawable;
        if (mStage < 1) {
            drawable = drawableGray;
        } else {
            drawable = drawableBlue;
        }
        int left = getMeasuredWidth() / 2 - drawableWidth / 2;
        Rect rect = new Rect(
                left,
                getPaddingTop(),
                left + drawableWidth,
                getPaddingTop() + drawableHeight);

        drawable.setBounds(rect);
        drawable.draw(canvas);

        drawText(number, canvas, rect);
    }

    private void drawLeft(String number, Canvas canvas) {
        int drawableWidth = drawableBlue.getIntrinsicWidth();
        int drawableHeight = drawableBlue.getIntrinsicHeight();

        int left = getPaddingLeft() + offsetX;

        Rect rect = new Rect(
                left,
                getPaddingTop(),
                left + drawableWidth,
                getPaddingTop() + drawableHeight
        );

        drawableBlue.setBounds(rect);
        drawableBlue.draw(canvas);

        drawText(number, canvas, rect);
    }

    private void drawText(String number, Canvas canvas, Rect rect) {
        Rect rectText = new Rect();
        mPaintText.getTextBounds(number, 0, number.length(), rectText);

        canvas.drawText(number, rect.left + drawableBlue.getIntrinsicWidth() / 2, getPaddingTop() + drawableBlue.getIntrinsicHeight() / 2 + rectText.height() / 2, mPaintText);
    }

    private void addTextView() {
        TextView textView1 = new TextView(getContext());
        textView1.setTextSize(14);
        textView1.setTextColor(0xff666666);
        textView1.setText(getResources().getString(R.string.text_delivery_wait));

        TextView textView2 = new TextView(getContext());
        textView2.setTextSize(14);
        textView2.setTextColor(0xff666666);
        textView2.setText(getResources().getString(R.string.text_delivery_send));

        TextView textView3 = new TextView(getContext());
        textView3.setTextSize(14);
        textView3.setTextColor(0xff666666);
        textView3.setText(getResources().getString(R.string.text_delivery_received));

        addView(textView1);
        addView(textView2);
        addView(textView3);
    }

    /**
     * 当前订单阶段,0:待发货,1:配送中,2:已签收
     */
    public void setStatus(int stage) {
        mStage = stage;
        invalidate();
    }
}
