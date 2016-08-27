package com.zj.example.view.customview2_circleview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by zhengjiong on 15/10/31.
 */
public class MeasureUtils {

    public static int[] getScreenSize(Context context) {
        return new int[]{
                context.getResources().getDisplayMetrics().widthPixels,
                context.getResources().getDisplayMetrics().heightPixels};
    }

    public static int[] getScreenSize2(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);

        return new int[]{
                displayMetrics.widthPixels,
                displayMetrics.heightPixels
        };
    }
}
