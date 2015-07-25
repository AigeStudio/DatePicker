package cn.aigestudio.datepicker.bizs.languages;

import java.util.Locale;

/**
 * 语言对象抽象父类
 * DatePicker暂且支持中文和英文两种显示语言
 * 如果你需要定义更多的语言可以新建自己的语言类并继承Language重写其方法即可
 * 同时你需要在Language的单例方法{@link #getInstance()}的分支语句中添加自己的语言类判断
 *
 * The abstract of language.
 * The current language only two support chinese and english in DatePicker.
 * If you need more language you want,you can define your own language class and extends Language
 * override all method.
 * Also you must add a judge of your language in branching statement of single case method{@link #getInstance()}
 *
 * @author AigeStudio 2015-03-26
 */
public abstract class DPLManager {
    private static DPLManager sLanguage;

    /**
     * 获取日历语言管理器
     *
     * Get DatePicker language manager
     *
     * @return 日历语言管理器 DatePicker language manager
     */
    public static DPLManager getInstance() {
        if (null == sLanguage) {
            String locale = Locale.getDefault().getLanguage().toLowerCase();
            if (locale.equals("zh")) {
                sLanguage = new CN();
            } else {
                sLanguage = new EN();
            }
        }
        return sLanguage;
    }

    /**
     * 月份标题显示
     *
     * Titles of month
     *
     * @return 长度为12的月份标题数组 Array in 12 length of month titles
     */
    public abstract String[] titleMonth();

    /**
     * 确定按钮文本
     *
     * Text of ensure button
     *
     * @return Text of ensure button
     */
    public abstract String titleEnsure();

    /**
     * 公元前文本
     *
     * Text of B.C.
     *
     * @return Text of B.C.
     */
    public abstract String titleBC();

    /**
     * 星期标题显示
     *
     * Titles of week
     *
     * @return 长度为7的星期标题数组 Array in 7 length of week titles
     */
    public abstract String[] titleWeek();
}
