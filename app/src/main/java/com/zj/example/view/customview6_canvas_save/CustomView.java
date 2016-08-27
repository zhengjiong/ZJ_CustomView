package com.zj.example.view.customview6_canvas_save;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zj.example.view.R;

/**
 * Created by zj on 2016/8/23.
 */
public class CustomView extends TextView {

    private Paint mPaintBlue;
    private Paint mPaintYellow;
    private Paint mPaintTranslateGreen;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs,  0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBlue.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaintBlue.setStyle(Paint.Style.FILL);

        mPaintYellow = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintYellow.setColor(getResources().getColor(android.R.color.holo_orange_light));
        mPaintYellow.setStyle(Paint.Style.FILL);

        mPaintTranslateGreen = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintTranslateGreen.setColor(getResources().getColor(R.color.translate_red));
        mPaintTranslateGreen.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaintBlue);
        canvas.drawRect(20, 20, getMeasuredWidth() - 20,  getMeasuredHeight() - 20, mPaintYellow);

        //保存当前视图没有被平移时候的状态
        canvas.save();

        //x和y各平移20像素
        canvas.translate(20, 20);

        //平移后绘制文字, 文字就会在里面的颜色里面
        super.onDraw(canvas);

        //还原视图为保存时候的状态(即没有平移的状态)
        canvas.restore();

        //这个时候画一个黑色的背景就会被translate影响到
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaintTranslateGreen);
    }
}
