package com.zj.example.view.customview28_vertical_scroll_textview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

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
public class VerticalScrollLayout extends LinearLayout{
    private Scroller mScroller;
    private List<String> mTexts = new ArrayList<>();

    public VerticalScrollLayout(Context context) {
        this(context, null);
    }

    public VerticalScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScroller = new Scroller(context);

        mTexts.add("AAAAAAAAAAAAAAAAAAAAAAAAA");
        mTexts.add("BBBBBBBBBBBBBBBBBBBBBBBBB");
        mTexts.add("CCCCCCCCCCCCCCCCCCCCCCCCC");
        mTexts.add("DDDDDDDDDDDDDDDDDDDDDDDDD");
        mTexts.add("EEEEEEEEEEEEEEEEEEEEEEEEE");
        mTexts.add("FFFFFFFFFFFFFFFFFFFFFFFFF");
        mTexts.add("GGGGGGGGGGGGGGGGGGGGGGGGG");
        setOrientation(VERTICAL);
        init();
    }

    private void init() {
        if (mTexts == null) {
            return;
        }
        for (int i = 0; i < mTexts.size(); i++) {
            String text = mTexts.get(i);

            TextView textView = new TextView(getContext());
            textView.setTag(i);
            textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(getContext(), 30)));
            textView.setText(text);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setBackgroundColor(Color.parseColor("#dcf987"));
            addView(textView);

            if (i == 2) break;
        }
        if (mTexts.size() > 1) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScroller.startScroll(0, mScroller.getFinalY(), 0, DisplayUtils.dp2px(getContext(), 30), 2000);
                    invalidate();
                }
            }, 1000);
        }

    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        } else {
            if (!mScroller.isFinished() || (mScroller.getStartY() == mScroller.getFinalY())) return;
            mScroller.abortAnimation();
            View child = getChildAt(getChildCount() - 1);
            Object tag = child.getTag();
            int index = 0;
            if (tag == null) {
                return;
            }
            index = Integer.parseInt(tag.toString());
            if (index == mTexts.size() - 1) {
                index = 0;
            } else {
                index++;
            }
            //removeView(getChildAt(0));
            TextView textView = new TextView(getContext());
            textView.setTag(index);
            textView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(getContext(), 30)));
            textView.setText(mTexts.get(index));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setBackgroundColor(Color.parseColor("#dcf987"));
            addView(textView);

            mScroller.startScroll(0, mScroller.getFinalY(), 0, DisplayUtils.dp2px(getContext(), 30), 2000);
            invalidate();
        }
    }
}
