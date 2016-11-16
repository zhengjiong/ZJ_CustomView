package com.zj.example.view.customview24_drawbitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.zj.example.view.R;

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
public class CustomViewBitmap extends View {
    private Bitmap mBitmap;

    public CustomViewBitmap(Context context) {
        this(context, null);
    }

    public CustomViewBitmap(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewBitmap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.cheese_2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);

        /**
         * 图片左上角位置默认为坐标原点,
         * ps:坐标原点为左上角
         */
        //方法1
        canvas.drawBitmap(mBitmap, new Matrix(), new Paint());

        /**
         * 绘制时指定了图片左上角的坐标(距离坐标原点的距离)：
         * ps:此处指定的是与坐标原点的距离，并非是与屏幕顶部和左侧的距离
         */
        //方法2
        canvas.drawBitmap(mBitmap, 50, mBitmap.getHeight() + 50, new Paint());


        //把坐标移动到控件中心
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

        //用src指定了图片绘制部分的区域, src如果比bitmap的宽度小, 图片将会被裁减
        Rect src = new Rect(0, 0, mBitmap.getWidth() / 2, mBitmap.getHeight());

        //用dst指定了绘制在屏幕上的绘制区域(left为x坐标, top为y坐标,原点在已经移动后的屏幕中心), dst如果宽度比src的小, 图片会自动被缩放
        //x坐标水平 移动bitmap的宽度(mBitmap.getWidth())
        Rect dst = new Rect(mBitmap.getWidth(), 0, mBitmap.getWidth() / 2, mBitmap.getHeight());
        //方法3
        canvas.drawBitmap(mBitmap, src, dst, new Paint());
    }

}
