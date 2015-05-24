package cn.aigestudio.datepicker.utils;

import android.content.Context;

/**
 * 系统操作工具类
 * Util of system.
 *
 * @author AigeStudio 2015-03-26
 */
public final class SystemUtil {
    public static String getCurrentLocale(Context context) {
        return context.getResources().getConfiguration().locale.getCountry();
    }
}
