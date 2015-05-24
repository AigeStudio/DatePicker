package cn.aigestudio.datepicker.entities;

import android.content.Context;

import cn.aigestudio.datepicker.utils.SystemUtil;

/**
 * 语言对象抽象父类
 * DatePicker暂且支持中文和英文两种显示语言
 * 如果你需要定义更多的语言可以新建自己的语言类并继承Language重写其方法即可
 * 同时你需要在Language的单例方法{@link #getLanguage(android.content.Context)}的分支语句中添加自己的语言类判断
 * The abstract of language.
 * The current language only two support chinese and english in DatePicker.
 * If you need more language you want,you can define your own language class and extends Language
 * override all method.
 * Also you must add a judge of your language in branching statement of single case method{@link #getLanguage(android.content.Context)}
 *
 * @author AigeStudio 2015-03-26
 */
public abstract class Language {
    private static Language sLanguage = null;

    public static Language getLanguage(Context context) {
        if (null == sLanguage) {
            String locale = SystemUtil.getCurrentLocale(context);
            if (locale.equals("CN")) {
                sLanguage = new CN();
            } else {
                sLanguage = new EN();
            }
        }
        return sLanguage;
    }

    public abstract String[] monthTitles();

    public abstract String ensureTitle();
}
