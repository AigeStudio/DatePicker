package cn.aigestudio.datepicker.utils;

import android.content.Context;

/**
 * 测量工具类
 * Util of measure.
 *
 * @author AigeStudio 2015-03-26
 */
public final class MeasureUtil {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
