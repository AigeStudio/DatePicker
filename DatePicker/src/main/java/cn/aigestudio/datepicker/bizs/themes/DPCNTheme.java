package cn.aigestudio.datepicker.bizs.themes;

/**
 * 天朝日历主题类
 * 其他国家的主题不需要这样去处理
 * 
 * Theme of china
 * You don't need this class for other countries
 *
 * @author AigeStudio 2015-06-17
 */
public class DPCNTheme extends DPBaseTheme {
    /**
     * 农历文本颜色
     * 
     * Color of Lunar text
     *
     * @return 16进制颜色值 hex color
     */
    public int colorL() {
        return 0xEE888888;
    }

    /**
     * 补休日期背景颜色
     * 
     * Color of Deferred background
     *
     * @return 16进制颜色值 hex color
     */
    public int colorDeferred() {
        return 0x50B48172;
    }
}
