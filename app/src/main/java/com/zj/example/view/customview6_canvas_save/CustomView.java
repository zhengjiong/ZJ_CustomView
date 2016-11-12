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

    /**
     * translate是坐标系的移动，可以为图形绘制选择一个合适的坐标系。
     * 请注意，位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动
     *
     * 缩放的中心默认为坐标原点,而缩放中心轴就是坐标轴,
     * 当缩放比例为负数的时候会根据缩放中心轴进行翻转
     * 参考http://www.gcssloop.com/customview/Canvas_Convert
     *
     */
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaintBlue);
        canvas.drawRect(50, 50, getMeasuredWidth() - 50,  getMeasuredHeight() - 50, mPaintYellow);

        //保存当前视图没有被平移时候的状态
        canvas.save();

        //x和y各平移50像素
        canvas.translate(50, 50);

        //平移后绘制文字, 文字就会在里面的颜色里面
        super.onDraw(canvas);

        //还原视图为保存时候的状态(即没有平移的状态)
        canvas.restore();

        //这个时候画一个黑色的背景就会被translate影响到
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaintTranslateGreen);
    }
}
