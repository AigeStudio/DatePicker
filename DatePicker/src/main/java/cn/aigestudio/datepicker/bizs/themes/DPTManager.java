package cn.aigestudio.datepicker.bizs.themes;

/**
 * 日历主题管理器
 * 在DatePicker被实例化前调用{@link #initCalendar(DPTheme)}方法来初始化一个日历主题对象
 * 
 * DatePicker theme manager
 * Call {@link #initCalendar(DPTheme)} method to initialization a theme before DatePicker instance
 *
 * @author AigeStudio 2015-06-30
 */
public final class DPTManager {
    private static DPTManager sManager;

    private DPTheme theme;// 主题对象

    private DPTManager() {
        initCalendar(new DPCNTheme());
    }

    /**
     * 获取日历主题管理器
     * 
     * Get DatePicker theme manager
     *
     * @return 日历主题管理器 DatePicker theme manager
     */
    public static DPTManager getInstance() {
        if (null == sManager) {
            sManager = new DPTManager();
        }
        return sManager;
    }

    /**
     * 初始化主题对象
     * 
     * Initialization Theme
     *
     * @param theme ...
     */
    public void initCalendar(DPTheme theme) {
        this.theme = theme;
    }

    /**
     * @see DPTheme#colorTitleBG()
     */
    public int colorTitleBG() {
        return theme.colorTitleBG();
    }

    /**
     * @see DPTheme#colorBG()
     */
    public int colorBG() {
        return theme.colorBG();
    }

    /**
     * @see DPTheme#colorBGCircle()
     */
    public int colorBGCircle() {
        return theme.colorBGCircle();
    }

    /**
     * @see DPTheme#colorTitle()
     */
    public int colorTitle() {
        return theme.colorTitle();
    }

    /**
     * @see DPTheme#colorToday()
     */
    public int colorToday() {
        return theme.colorToday();
    }

    /**
     * @see DPTheme#colorG()
     */
    public int colorG() {
        return theme.colorG();
    }

    /**
     * @see DPTheme#colorF()
     */
    public int colorF() {
        return theme.colorF();
    }

    /**
     * @see DPTheme#colorWeekend()
     */
    public int colorWeekend() {
        return theme.colorWeekend();
    }

    /**
     * @see DPTheme#colorHoliday()
     */
    public int colorHoliday() {
        return theme.colorHoliday();
    }

    /**
     * @see DPCNTheme#colorL()
     */
    public int colorL() {
        if (theme instanceof DPCNTheme) {
            return ((DPCNTheme) theme).colorL();
        }
        return 0;
    }

    /**
     * @see DPCNTheme#colorDeferred()
     */
    public int colorDeferred() {
        if (theme instanceof DPCNTheme) {
            return ((DPCNTheme) theme).colorDeferred();
        }
        return 0;
    }
}
