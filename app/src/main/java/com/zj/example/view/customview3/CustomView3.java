package com.zj.example.view.customview3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.zj.example.view.R;

/**
 * http://blog.csdn.net/u012123160/article/details/44814557
 * http://www.cnblogs.com/angeldevil/p/3479431.html#two.one
 *
 *
 *
 * 自定义控件第三个构造参数的用法
 * create by zhengjiong
 * Date: 2015-05-25
 * Time: 21:02
 */
public class CustomView3 extends View{
    private String tag = "CustomView1";
    private String mContent;
    private Paint mPaint;
    private Rect mRect;
    private int mTxtColor;

    public CustomView3(Context context) {
        this(context, null);
    }

    public CustomView3(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.custom_view);
    }

    public CustomView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         *
         * 结论

         　　从前面的说明可以得到以下结论：

         1.要为自定义View自定义属性，可以通过declare-styleable声明需要的属性
         2.为了在Theme设置View的默认样式，可以同时定义一个format为reference的属性attr(比如:R.attr.custom_view)如下:
         <attr name="custom_view" format="reference"/>

         在定义Theme时为这个attribute指定一个Style(如下面的style:)，并且在View的第二个构造函数中将R.attr.custom_view 作为第三个参数调用第三个构造函数,
         <style name="Theme_CustomView3Activity" parent="Theme.AppCompat.Light.DarkActionBar">
            <item name="custom_view">@style/CustomViewStyle1</item>
         </style>

         3.可以定义一个Style作为Theme中没有定义attr_a时View属性的默认值。
         4.可以在Theme中直接为属性赋值，但优先级最低
         5.当defStyleAttr（即View的构造函数的第三个参数）不为0且在Theme中有为这个attr赋值时，defStyleRes（obtainStyledAttributes的第四个参数指定）不起作用
         6.属性值定义的优先级：xml>style>Theme中的默认Sytle>默认Style（通过obtainStyledAttributes的第四个参数指定）>在Theme中直接指定属性值
         *
         * http://blog.csdn.net/u012123160/article/details/44814557
         *
         * 通过这个函数来获得`TypeArray`，里面包含属性值。
         - set是属性值的集合；
         - attrs是在xml声明的属性值集合，通过`R.styleable.xxxx`来获得，个人理解就是通过这个属性来知道，xml文件中可能会自定义的一些属性的名称、类型情况；
         - 第三个属性defStyleAttr是一个引用，是在Theme中设置的引用，在代码中就是位于`attrs.xml`文件中的`<attr name="CustomViewSytle" format="reference"/>`这句话。
         - 而在文件`style.xml`的`<Theme>`标签中，设置了这个引用的值为`<item name="CustomViewSytle">@style/CustomizeStyle</item>`。
         - 如果在当在layout xml中和style中都没有为View指定属性时，会从Theme中这个attribute指向的Style中查找相应的属性值。如果这个参数传入0表示不向Theme中搜索默认值；
         - 第四个参数，这个也是指向一个Style的资源ID，但是仅在`defStyleAttr`为0或`defStyleAttr`不为0但`<Theme>`中没有为`defStyleAttr`属性赋值时起作用。
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView3, defStyleAttr, R.style.CustomViewStyle2);
        mTxtColor= typedArray.getColor(R.styleable.CustomView3_txtcolor, Color.BLACK);

        /**
         * 這裡需要用TypedValue把6sp轉換成px,
         * 如果用getDimension或者getDimensionPixelSize不轉換的話都只會返回6,
         *
         * 除非把6定義在dimens中,如果定義在dimens中不能直接寫R.dimen.txtsize,不然會返回錯誤的值
         * 要寫成getResources().getDimension(),或者getdimensionPixelSize(R.dimen.txtsize)
         */
        float txtSize = typedArray.getDimension(
                R.styleable.CustomView3_txtsize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6.0f, getResources().getDisplayMetrics())
                //getResources().getDimension(R.dimen.txtsize)//18
        );

        mContent = typedArray.getString(R.styleable.CustomView3_txtcontent);

        typedArray.recycle();

        //18sp轉換為px, test1 = 54;
        float test1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 18, getResources().getDisplayMetrics());

        Log.i(tag, "color =" + mTxtColor + " ,txtSize=" + txtSize + " content=" + mContent);

        mRect = new Rect();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(txtSize);
        mPaint.getTextBounds(mContent, 0, mContent.length(), mRect);
        Log.i(tag, "bound width=" + mRect.width() + " ,height=" + mRect.height());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);//畫背景


        mPaint.setColor(mTxtColor);
        canvas.drawText(
                mContent,
                getMeasuredWidth() / 2 - mRect.width() / 2,//讓文字水平居中
                getMeasuredHeight() / 2 + mRect.height() / 2,//讓文字錘子居中
                mPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int resolveWidth = View.resolveSize(width, widthMeasureSpec);
        int defaultWidth = View.getDefaultSize(width, widthMeasureSpec);

        int resolveHeight = View.resolveSize(height, heightMeasureSpec);
        int defaultHeight = View.getDefaultSize(height, heightMeasureSpec);

        //onMeasure width=1080 ,resolveWidth=1080 ,defaultWidth=1080
        Log.i(tag, "onMeasure width=" + width + " ,resolveWidth=" + resolveWidth + " ,defaultWidth=" + defaultWidth);

        //onMeasure height=300 ,resolveHeight=300 ,defaultHeight=300
        Log.i(tag, "onMeasure height=" + height + " ,resolveHeight=" + resolveHeight + " ,defaultHeight=" + defaultHeight);*/

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthResult = getWidthResult(width, widthMode);
        int heightResult = getHeightResult(height, heightMode);

        setMeasuredDimension(widthResult, heightResult);
    }

    private int getWidthResult(int width, int widthMode) {
        int widthResult = 0;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                widthResult = width;
                Log.i("zj", "getWidthResult MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                Log.i("zj", "getWidthResult MeasureSpec.AT_MOST");
                widthResult = getPaddingLeft() + getPaddingRight() + mRect.width();
                break;
        }
        return widthResult;
    }

    private int getHeightResult(int height, int heightMode) {
        int heightResult = 0;
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                heightResult = height;
                Log.i("zj", "getHeightResult MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                Log.i("zj", "getHeightResult MeasureSpec.AT_MOST");
                heightResult = getPaddingTop() + getPaddingBottom() + mRect.height();
                break;
        }
        return heightResult;
    }
}