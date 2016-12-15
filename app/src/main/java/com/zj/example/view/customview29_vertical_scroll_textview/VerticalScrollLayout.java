package com.zj.example.view.customview29_vertical_scroll_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Scroller;

import com.zj.example.view.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: VerticalScrollTextView
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/12/14  10:15
 *
 * @author 郑炯
 * @version 1.0
 */
public class VerticalScrollLayout extends View implements View.OnClickListener{
    private final static String KEY_DEFAULT = "key_default";
    private final static String KEY_INDEX = "key_index";
    private final static String KEY_FIRST = "key_first";
    private final static String KEY_DY = "key_dy";
    public static final int DURATION = 2000;
    public static final int DELAY_MILLIS = 0;

    private boolean isFirst = true;
    private int mIndex;
    private int dy;
    private Paint mPaint;
    private Scroller mScroller;
    private List<String> mTexts = new ArrayList<>();

    public OnItemClickListener mOnItemClickListener;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            try {
                mOnItemClickListener.onClick(mTexts.get(mIndex));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnItemClickListener{
        public void onClick(String text);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public VerticalScrollLayout(Context context) {
        this(context, null);
    }

    public VerticalScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, context.getResources().getDisplayMetrics()));

        mScroller = new Scroller(context);

        mTexts.add("AAAAAAAAAAAAAAA");
        mTexts.add("BBBBBBBBBBBBBBBBBBB");
        mTexts.add("CCCCCCCCCCCCCcCCCCCCC");
        /*mTexts.add("DDDDDDDDDDDDDDDDDDDDDDDD");
        mTexts.add("EEEEEEEEEEEEEEEEEEEEeeeEEEEEEE");
        mTexts.add("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        mTexts.add("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");*/
        init();
    }

    private void init() {

    }

    public void setTexts(List<String> texts) {
        mTexts.clear();
        mTexts.addAll(texts);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTexts == null || mTexts.size() == 0) {
            return;
        }
        String text = mTexts.get(mIndex);
        final Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        final int baseLineY = DisplayUtils.dp2px(getContext(), 30) / 2 - rect.top / 2;

        canvas.drawText(text, 30, baseLineY - dy, mPaint);


        if (mTexts.size() > 1) {
            if (mIndex < mTexts.size() - 1) {
                text = mTexts.get(mIndex + 1);
            } else {
                text = mTexts.get(0);
            }
            canvas.drawText(text, 30, baseLineY - dy + DisplayUtils.dp2px(getContext(), 30), mPaint);
        }

        if (isFirst && mTexts.size() > 1) {
            isFirst = false;
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScroller.startScroll(0, 0, 0, DisplayUtils.dp2px(getContext(), 30), DURATION);
                    invalidate();
                }
            }, 1000);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            dy = mScroller.getCurrY();
            if (mScroller.getCurrY() != mScroller.getFinalY()) {
                invalidate();
            } else {
                startScroll();
            }
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());
        bundle.putInt(KEY_INDEX, mIndex);
        bundle.putBoolean(KEY_FIRST, isFirst);
        bundle.putInt(KEY_DY, dy);

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mIndex = bundle.getInt(KEY_INDEX, 0);
            isFirst = bundle.getBoolean(KEY_FIRST, true);
            dy = bundle.getInt(KEY_DY, 0);
            super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private void startScroll() {
        mScroller.abortAnimation();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mScroller.isFinished() || (mScroller.getStartY() == mScroller.getFinalY())) return;
                if (mIndex == mTexts.size() - 1) {
                    mIndex = 0;
                } else {
                    mIndex++;
                }

                mScroller.startScroll(0, 0, 0, DisplayUtils.dp2px(getContext(), 30), DURATION);
                invalidate();
            }
        }, DELAY_MILLIS);
    }
}
