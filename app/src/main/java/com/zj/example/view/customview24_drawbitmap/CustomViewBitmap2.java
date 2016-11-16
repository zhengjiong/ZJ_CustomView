package com.zj.example.view.customview24_drawbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.zj.example.view.R;

/**
 * Title: CustomViewBitmap2
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/11/16  10:48
 *
 * @author 郑炯
 * @version 1.0
 */

public class CustomViewBitmap2 extends View {
    public static final int DELAY_MILLIS = 20;
    private Bitmap mBitmap;
    private Paint mPaint;

    private int index = -1;//图片位置
    private Handler mHandler;
    private boolean isUnCheck;


    public CustomViewBitmap2(Context context) {
        this(context, null);
    }

    public CustomViewBitmap2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewBitmap2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#fb3145"));
        mPaint.setStyle(Paint.Style.FILL);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.tick);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (isUnCheck) {
                    if (index > -1) {
                        index--;

                        invalidate();
                        mHandler.sendEmptyMessageDelayed(0, DELAY_MILLIS);
                    }
                } else {
                    if (index < 12) {
                        index++;

                        invalidate();
                        mHandler.sendEmptyMessageDelayed(0, DELAY_MILLIS);
                    }
                }

            }
        };
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移动坐标原点到View中心
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        //绘制红色的圆圈
        canvas.drawCircle(0, 0, 250, mPaint);

        drawTick(canvas);
    }

    private void drawTick(Canvas canvas) {
        int avgWidth = mBitmap.getWidth() / 13;
        int height = mBitmap.getHeight();

        Rect src = new Rect(avgWidth * index, 0, avgWidth * (index + 1), height);
        Rect dst = new Rect(-avgWidth / 2, -height / 2, avgWidth / 2, height / 2);

        canvas.drawBitmap(mBitmap, src, dst, new Paint(Paint.ANTI_ALIAS_FLAG));
    }

    public void check() {
        isUnCheck = false;
        mHandler.sendEmptyMessageDelayed(0, DELAY_MILLIS);
    }

    public void unCheck() {
        isUnCheck = true;
        mHandler.sendEmptyMessageDelayed(0, DELAY_MILLIS);
    }
}
