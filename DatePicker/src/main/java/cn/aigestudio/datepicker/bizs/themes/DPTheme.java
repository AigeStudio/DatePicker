package cn.aigestudio.datepicker.bizs.themes;

/**
 * 主题抽象类
 * 你可以继承该类定制自己的颜色主题
 * 
 * Abstract class of theme
 * You can extends this class to implement your own theme colors
 *
 * @author AigeStudio 2015-06-30
 */
public abstract class DPTheme {
    /**
     * 月视图背景色
     * 
     * Color of MonthView's background
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorBG();

    /**
     * 背景圆颜色
     * 
     * Color of MonthView's selected circle
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorBGCircle();

    /**
     * 标题栏背景色
     * 
     * Color of TitleBar's background
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorTitleBG();

    /**
     * 标题栏文本颜色
     * 
     * Color of TitleBar text
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorTitle();

    /**
     * 今天的背景色
     * 
     * Color of Today's background
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorToday();

    /**
     * 公历文本颜色
     * 
     * Color of Gregorian text
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorG();

    /**
     * 节日文本颜色
     * 
     * Color of Festival text
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorF();

    /**
     * 周末文本颜色
     * 
     * Color of Weekend text
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorWeekend();

    /**
     * 假期文本颜色
     * 
     * Color of Holiday text
     *
     * @return 16进制颜色值 hex color
     */
    public abstract int colorHoliday();
}
