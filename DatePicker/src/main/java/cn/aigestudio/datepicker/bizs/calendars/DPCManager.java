package cn.aigestudio.datepicker.bizs.calendars;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.aigestudio.datepicker.entities.DPInfo;
import cn.aigestudio.datepicker.views.DatePicker;

/**
 * 日期管理器
 * The manager of date picker.
 *
 * @author AigeStudio 2015-06-12
 */
public final class DPCManager {
    private static final HashMap<Integer, HashMap<Integer, DPInfo[][]>> DATE_CACHE = new HashMap<>();

    private static final HashMap<String, Set<String>> DECOR_CACHE_BG = new HashMap<>();
    private static final HashMap<String, Set<String>> DECOR_CACHE_TL = new HashMap<>();
    private static final HashMap<String, Set<String>> DECOR_CACHE_T = new HashMap<>();
    private static final HashMap<String, Set<String>> DECOR_CACHE_TR = new HashMap<>();
    private static final HashMap<String, Set<String>> DECOR_CACHE_L = new HashMap<>();
    private static final HashMap<String, Set<String>> DECOR_CACHE_R = new HashMap<>();

    private static DPCManager sManager;

    private DPCalendar c;

    private DPCManager() {
        // 默认显示为中文日历
        String locale = Locale.getDefault().getCountry().toLowerCase();
        if (locale.equals("cn")) {
            initCalendar(new DPCNCalendar());
        } else {
            initCalendar(new DPUSCalendar());
        }
    }

    /**
     * 获取月历管理器
     * Get calendar manager
     *
     * @return 月历管理器
     */
    public static DPCManager getInstance() {
        if (null == sManager) {
            sManager = new DPCManager();
        }
        return sManager;
    }

    /**
     * 初始化日历对象
     * <p/>
     * Initialization Calendar
     *
     * @param c ...
     */
    public void initCalendar(DPCalendar c) {
        this.c = c;
    }

    /**
     * 设置有背景标识物的日期
     * <p/>
     * Set date which has decor of background
     *
     * @param date 日期列表 List of date
     */
    public void setDecorBG(List<String> date) {
        setDecor(date, DECOR_CACHE_BG);
    }

    /**
     * 设置左上角有标识物的日期
     * <p/>
     * Set date which has decor on Top left
     *
     * @param date 日期列表 List of date
     */
    public void setDecorTL(List<String> date) {
        setDecor(date, DECOR_CACHE_TL);
    }

    /**
     * 设置顶部有标识物的日期
     * <p/>
     * Set date which has decor on Top
     *
     * @param date 日期列表 List of date
     */
    public void setDecorT(List<String> date) {
        setDecor(date, DECOR_CACHE_T);
    }

    /**
     * 设置右上角有标识物的日期
     * <p/>
     * Set date which has decor on Top right
     *
     * @param date 日期列表 List of date
     */
    public void setDecorTR(List<String> date) {
        setDecor(date, DECOR_CACHE_TR);
    }

    /**
     * 设置左边有标识物的日期
     * <p/>
     * Set date which has decor on left
     *
     * @param date 日期列表 List of date
     */
    public void setDecorL(List<String> date) {
        setDecor(date, DECOR_CACHE_L);
    }

    /**
     * 设置右上角有标识物的日期
     * <p/>
     * Set date which has decor on right
     *
     * @param date 日期列表 List of date
     */
    public void setDecorR(List<String> date) {
        setDecor(date, DECOR_CACHE_R);
    }

    /**
     * 获取指定年月的日历对象数组
     *
     * @param year  公历年
     * @param month 公历月
     * @return 日历对象数组 该数组长度恒为6x7 如果某个下标对应无数据则填充为null
     */
    public DPInfo[][] obtainDPInfo(int year, int month) {
        HashMap<Integer, DPInfo[][]> dataOfYear = DATE_CACHE.get(year);
        if (null != dataOfYear && dataOfYear.size() != 0) {
            DPInfo[][] dataOfMonth = dataOfYear.get(month);
            if (dataOfMonth != null) {
                return dataOfMonth;
            }
            dataOfMonth = buildDPInfo(year, month);
            dataOfYear.put(month, dataOfMonth);
            return dataOfMonth;
        }
        if (null == dataOfYear) dataOfYear = new HashMap<>();
        DPInfo[][] dataOfMonth = buildDPInfo(year, month);
        dataOfYear.put((month), dataOfMonth);
        DATE_CACHE.put(year, dataOfYear);
        return dataOfMonth;
    }

    private void setDecor(List<String> date, HashMap<String, Set<String>> cache) {
        for (String str : date) {
            int index = str.lastIndexOf("-");
            String key = str.substring(0, index).replace("-", ":");
            Set<String> days = cache.get(key);
            if (null == days) {
                days = new HashSet<>();
            }
            days.add(str.substring(index + 1, str.length()));
            cache.put(key, days);
        }
    }

    private DPInfo[][] buildDPInfo(int year, int month) {
        DPInfo[][] info = new DPInfo[6][7];

        String[][] strG = c.buildMonthG(year, month);
        String[][] strF = c.buildMonthFestival(year, month);

        Set<String> strHoliday = c.buildMonthHoliday(year, month);
        Set<String> strWeekend = c.buildMonthWeekend(year, month);

        Set<String> decorBG = DECOR_CACHE_BG.get(year + ":" + month);
        Set<String> decorTL = DECOR_CACHE_TL.get(year + ":" + month);
        Set<String> decorT = DECOR_CACHE_T.get(year + ":" + month);
        Set<String> decorTR = DECOR_CACHE_TR.get(year + ":" + month);
        Set<String> decorL = DECOR_CACHE_L.get(year + ":" + month);
        Set<String> decorR = DECOR_CACHE_R.get(year + ":" + month);
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < info[i].length; j++) {
                DPInfo tmp = new DPInfo();
                tmp.strG = strG[i][j];
                if (c instanceof DPCNCalendar) {
                    tmp.strF = strF[i][j].replace("F", "");
                } else {
                    tmp.strF = strF[i][j];
                }
                if (!TextUtils.isEmpty(tmp.strG) && strHoliday.contains(tmp.strG))
                    tmp.isHoliday = true;
                if (!TextUtils.isEmpty(tmp.strG)) tmp.isToday =
                        c.isToday(year, month, Integer.valueOf(tmp.strG));
                if (strWeekend.contains(tmp.strG)) tmp.isWeekend = true;
                if (c instanceof DPCNCalendar) {
                    if (!TextUtils.isEmpty(tmp.strG)) tmp.isSolarTerms =
                            ((DPCNCalendar) c).isSolarTerm(year, month, Integer.valueOf(tmp.strG));
                    if (!TextUtils.isEmpty(strF[i][j]) && strF[i][j].endsWith("F"))
                        tmp.isFestival = true;
                    if (!TextUtils.isEmpty(tmp.strG))
                        tmp.isDeferred = ((DPCNCalendar) c)
                                .isDeferred(year, month, Integer.valueOf(tmp.strG));
                } else {
                    tmp.isFestival = !TextUtils.isEmpty(strF[i][j]);
                }
                if (null != decorBG && decorBG.contains(tmp.strG)) tmp.isDecorBG = true;
                if (null != decorTL && decorTL.contains(tmp.strG)) tmp.isDecorTL = true;
                if (null != decorT && decorT.contains(tmp.strG)) tmp.isDecorT = true;
                if (null != decorTR && decorTR.contains(tmp.strG)) tmp.isDecorTR = true;
                if (null != decorL && decorL.contains(tmp.strG)) tmp.isDecorL = true;
                if (null != decorR && decorR.contains(tmp.strG)) tmp.isDecorR = true;
                info[i][j] = tmp;
            }
        }
        return info;
    }
}
