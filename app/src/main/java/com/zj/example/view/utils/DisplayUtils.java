package com.zj.example.view.utils;

import android.content.Context;

/**
 * Title: DisplayUtils
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/12/12  16:34
 *
 * @author 郑炯
 * @version 1.0
 */
public class DisplayUtils {

    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }
}
