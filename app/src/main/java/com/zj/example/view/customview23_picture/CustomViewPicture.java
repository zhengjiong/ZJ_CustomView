package com.zj.example.view.customview23_picture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Title: CustomViewPicture
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/11/14  17:35
 *
 * @author 郑炯
 * @version 1.0
 */
public class CustomViewPicture extends View {

    private Picture mPicture1;
    private Picture mPicture2;
    private Picture mPicture3;

    public CustomViewPicture(Context context) {
        this(context, null);
    }

    public CustomViewPicture(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewPicture(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 我们把Canvas绘制点，线，矩形等诸多操作用Picture录制下来，
         * 下次需要的时候拿来就能用，使用Picture相比于再次调用绘图API，
         * 开销是比较小的，也就是说对于重复的操作可以更加省时省力。
         *
         * PS：你可以把Picture看作是一个录制Canvas操作的录像机。
         * 注意：在使用Picture之前请关闭硬件加速，以免引起不必要的问题，如何关闭请参考这里
         */
        initPicture();
        initPicture2();
        initPicture3();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 1.使用Picture提供的draw方法绘制:
         * mPicture.draw(canvas);// 将Picture中的内容绘制在Canvas上
         * PS：这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用。
         * 一般使用canvas.drawPicture
         */
        mPicture1.draw(canvas);
        //2.使用Canvas提供的drawPicture方法绘制
        canvas.drawPicture(mPicture2, new Rect(200, 0, 200 + mPicture2.getWidth(), mPicture2.getHeight()));
        //这里如果设置成宽度只有100, 绘制出来的图形就会被压缩
        canvas.drawPicture(mPicture1, new Rect(300 + 200, 0, 500 + 100, 200));

        //3.将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
        PictureDrawable pictureDrawable = new PictureDrawable(mPicture3);
        pictureDrawable.setBounds(0, 0, pictureDrawable.getIntrinsicWidth(), pictureDrawable.getIntrinsicHeight());
        pictureDrawable.draw(canvas);
    }

    /**
     * 上下左右没有空白的圆圈
     */
    private void initPicture3(){
        mPicture3 = new Picture();
        Canvas canvas = mPicture3.beginRecording(698+98, 100+98);//因为下面translate了698, 加上半径98
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        canvas.translate(698, 100);

        canvas.drawCircle(0, 0, 98, paint);

        mPicture3.endRecording();
    }

    /**
     * picture中的圆形上下左右有50px的空白
     */
    private void initPicture2() {
        mPicture2 = new Picture();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);

        //开始录制 (接收返回值Canvas), 只录制300,300范围以内的区域
        Canvas canvas = mPicture2.beginRecording(300, 300);
        //移动到中心
        canvas.translate(150, 150);
        //画一个半径为100的圆
        canvas.drawCircle(0, 0, 100, paint);
        //结束录制
        mPicture2.endRecording();

    }

    /**
     * 上下左右没有空白的圆形
     */
    private void initPicture() {
        mPicture1 = new Picture();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        //开始录制 (接收返回值Canvas), 只录制200,200范围以内的区域
        Canvas canvas = mPicture1.beginRecording(200, 200);
        //移动到中心
        canvas.translate(100, 100);
        //画一个半径为100的圆
        canvas.drawCircle(0, 0, 100, paint);
        //结束录制
        mPicture1.endRecording();

    }
}
