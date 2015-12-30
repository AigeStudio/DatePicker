package cn.aigestudio.datepicker.bizs.calendars;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * 月历抽象父类
 * 继承该类可以实现自己的日历对象
 * <p/>
 * Abstract class of Calendar
 *
 * @author AigeStudio 2015-06-15
 */
public abstract class DPCalendar {
    protected final Calendar c = Calendar.getInstance();

    /**
     * 获取某年某月的节日数组
     * <p/>
     * Build the festival date array of given year and month
     *
     * @param year  某年
     * @param month 某月
     * @return 该月节日数组
     */
    public abstract String[][] buildMonthFestival(int year, int month);

    /**
     * 获取某年某月的假期数组
     * <p/>
     * Build the holiday date array of given year and month
     *
     * @param year  某年
     * @param month 某月
     * @return 该月假期数组
     */
    public abstract Set<String> buildMonthHoliday(int year, int month);

    /**
     * 判断某年是否为闰年
     *
     * @param year ...
     * @return true表示闰年
     */
    public boolean isLeapYear(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    /**
     * 判断给定日期是否为今天
     *
     * @param year  某年
     * @param month 某月
     * @param day   某天
     * @return ...
     */
    public boolean isToday(int year, int month, int day) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(year, month - 1, day);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) &&
                (c1.get(Calendar.MONTH) == (c2.get(Calendar.MONTH))) &&
                (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 生成某年某月的公历天数数组
     * 数组为6x7的二维数组因为一个月的周数永远不会超过六周
     * 天数填充对应相应的二维数组下标
     * 如果某个数组下标中没有对应天数那么则填充一个空字符串
     *
     * @param year  某年
     * @param month 某月
     * @return 某年某月的公历天数数组
     */
    public String[][] buildMonthG(int year, int month) {
        c.clear();
        String tmp[][] = new String[6][7];
        c.set(year, month - 1, 1);

        int daysInMonth = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 ||
                month == 12) {
            daysInMonth = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else if (month == 2) {
            if (isLeapYear(year)) {
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        }
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        int day = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tmp[i][j] = "";
                if (i == 0 && j >= dayOfWeek) {
                    tmp[i][j] = "" + day;
                    day++;
                } else if (i > 0 && day <= daysInMonth) {
                    tmp[i][j] = "" + day;
                    day++;
                }
            }
        }
        return tmp;
    }

    /**
     * 生成某年某月的周末日期集合
     *
     * @param year  某年
     * @param month 某月
     * @return 某年某月的周末日期集合
     */
    public Set<String> buildMonthWeekend(int year, int month) {
        Set<String> set = new HashSet<>();
        c.clear();
        c.set(year, month - 1, 1);
        do {
            int day = c.get(Calendar.DAY_OF_WEEK);
            if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
                set.add(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
            }
            c.add(Calendar.DAY_OF_YEAR, 1);
        } while (c.get(Calendar.MONTH) == month - 1);
        return set;
    }

    protected long GToNum(int year, int month, int day) {
        month = (month + 9) % 12;
        year = year - month / 10;
        return 365 * year + year / 4 - year / 100 + year / 400 + (month * 306 + 5) / 10 + (day - 1);
    }

    protected int getBitInt(int data, int length, int shift) {
        return (data & (((1 << length) - 1) << shift)) >> shift;
    }
}
