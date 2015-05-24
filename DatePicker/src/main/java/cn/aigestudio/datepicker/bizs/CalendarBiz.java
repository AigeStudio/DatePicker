package cn.aigestudio.datepicker.bizs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aigestudio.datepicker.entities.Gregorian;
import cn.aigestudio.datepicker.entities.Lunar;

/**
 * 日历业务对象
 * The biz of calendar
 *
 * @author AigeStudio 2015-03-23
 */
public class CalendarBiz implements IBO{
    private static final int[] LUNAR_YEAR_INFO = {1887, 0x1694, 0x16aa, 0x4ad5,
            0xab6, 0xc4b7, 0x4ae, 0xa56, 0xb52a, 0x1d2a, 0xd54, 0x75aa, 0x156a,
            0x1096d, 0x95c, 0x14ae, 0xaa4d, 0x1a4c, 0x1b2a, 0x8d55, 0xad4,
            0x135a, 0x495d, 0x95c, 0xd49b, 0x149a, 0x1a4a, 0xbaa5, 0x16a8,
            0x1ad4, 0x52da, 0x12b6, 0xe937, 0x92e, 0x1496, 0xb64b, 0xd4a,
            0xda8, 0x95b5, 0x56c, 0x12ae, 0x492f, 0x92e, 0xcc96, 0x1a94,
            0x1d4a, 0xada9, 0xb5a, 0x56c, 0x726e, 0x125c, 0xf92d, 0x192a,
            0x1a94, 0xdb4a, 0x16aa, 0xad4, 0x955b, 0x4ba, 0x125a, 0x592b,
            0x152a, 0xf695, 0xd94, 0x16aa, 0xaab5, 0x9b4, 0x14b6, 0x6a57,
            0xa56, 0x1152a, 0x1d2a, 0xd54, 0xd5aa, 0x156a, 0x96c, 0x94ae,
            0x14ae, 0xa4c, 0x7d26, 0x1b2a, 0xeb55, 0xad4, 0x12da, 0xa95d,
            0x95a, 0x149a, 0x9a4d, 0x1a4a, 0x11aa5, 0x16a8, 0x16d4, 0xd2da,
            0x12b6, 0x936, 0x9497, 0x1496, 0x1564b, 0xd4a, 0xda8, 0xd5b4,
            0x156c, 0x12ae, 0xa92f, 0x92e, 0xc96, 0x6d4a, 0x1d4a, 0x10d65,
            0xb58, 0x156c, 0xb26d, 0x125c, 0x192c, 0x9a95, 0x1a94, 0x1b4a,
            0x4b55, 0xad4, 0xf55b, 0x4ba, 0x125a, 0xb92b, 0x152a, 0x1694,
            0x96aa, 0x15aa, 0x12ab5, 0x974, 0x14b6, 0xca57, 0xa56, 0x1526,
            0x8e95, 0xd54, 0x15aa, 0x49b5, 0x96c, 0xd4ae, 0x149c, 0x1a4c,
            0xbd26, 0x1aa6, 0xb54, 0x6d6a, 0x12da, 0x1695d, 0x95a, 0x149a,
            0xda4b, 0x1a4a, 0x1aa4, 0xbb54, 0x16b4, 0xada, 0x495b, 0x936,
            0xf497, 0x1496, 0x154a, 0xb6a5, 0xda4, 0x15b4, 0x6ab6, 0x126e,
            0x1092f, 0x92e, 0xc96, 0xcd4a, 0x1d4a, 0xd64, 0x956c, 0x155c,
            0x125c, 0x792e, 0x192c, 0xfa95, 0x1a94, 0x1b4a, 0xab55, 0xad4,
            0x14da, 0x8a5d, 0xa5a, 0x1152b, 0x152a, 0x1694, 0xd6aa, 0x15aa,
            0xab4, 0x94ba, 0x14b6, 0xa56, 0x7527, 0xd26, 0xee53, 0xd54, 0x15aa,
            0xa9b5, 0x96c, 0x14ae, 0x8a4e, 0x1a4c, 0x11d26, 0x1aa4, 0x1b54,
            0xcd6a, 0xada, 0x95c, 0x949d, 0x149a, 0x1a2a, 0x5b25, 0x1aa4,
            0xfb52, 0x16b4, 0xaba, 0xa95b, 0x936, 0x1496, 0x9a4b, 0x154a,
            0x136a5, 0xda4, 0x15ac};
    private static final int[] GREGORIAN_YEAR_INFO = {1887, 0xec04c, 0xec23f, 0xec435, 0xec649,
            0xec83e, 0xeca51, 0xecc46, 0xece3a, 0xed04d, 0xed242, 0xed436,
            0xed64a, 0xed83f, 0xeda53, 0xedc48, 0xede3d, 0xee050, 0xee244,
            0xee439, 0xee64d, 0xee842, 0xeea36, 0xeec4a, 0xeee3e, 0xef052,
            0xef246, 0xef43a, 0xef64e, 0xef843, 0xefa37, 0xefc4b, 0xefe41,
            0xf0054, 0xf0248, 0xf043c, 0xf0650, 0xf0845, 0xf0a38, 0xf0c4d,
            0xf0e42, 0xf1037, 0xf124a, 0xf143e, 0xf1651, 0xf1846, 0xf1a3a,
            0xf1c4e, 0xf1e44, 0xf2038, 0xf224b, 0xf243f, 0xf2653, 0xf2848,
            0xf2a3b, 0xf2c4f, 0xf2e45, 0xf3039, 0xf324d, 0xf3442, 0xf3636,
            0xf384a, 0xf3a3d, 0xf3c51, 0xf3e46, 0xf403b, 0xf424e, 0xf4443,
            0xf4638, 0xf484c, 0xf4a3f, 0xf4c52, 0xf4e48, 0xf503c, 0xf524f,
            0xf5445, 0xf5639, 0xf584d, 0xf5a42, 0xf5c35, 0xf5e49, 0xf603e,
            0xf6251, 0xf6446, 0xf663b, 0xf684f, 0xf6a43, 0xf6c37, 0xf6e4b,
            0xf703f, 0xf7252, 0xf7447, 0xf763c, 0xf7850, 0xf7a45, 0xf7c39,
            0xf7e4d, 0xf8042, 0xf8254, 0xf8449, 0xf863d, 0xf8851, 0xf8a46,
            0xf8c3b, 0xf8e4f, 0xf9044, 0xf9237, 0xf944a, 0xf963f, 0xf9853,
            0xf9a47, 0xf9c3c, 0xf9e50, 0xfa045, 0xfa238, 0xfa44c, 0xfa641,
            0xfa836, 0xfaa49, 0xfac3d, 0xfae52, 0xfb047, 0xfb23a, 0xfb44e,
            0xfb643, 0xfb837, 0xfba4a, 0xfbc3f, 0xfbe53, 0xfc048, 0xfc23c,
            0xfc450, 0xfc645, 0xfc839, 0xfca4c, 0xfcc41, 0xfce36, 0xfd04a,
            0xfd23d, 0xfd451, 0xfd646, 0xfd83a, 0xfda4d, 0xfdc43, 0xfde37,
            0xfe04b, 0xfe23f, 0xfe453, 0xfe648, 0xfe83c, 0xfea4f, 0xfec44,
            0xfee38, 0xff04c, 0xff241, 0xff436, 0xff64a, 0xff83e, 0xffa51,
            0xffc46, 0xffe3a, 0x10004e, 0x100242, 0x100437, 0x10064b, 0x100841,
            0x100a53, 0x100c48, 0x100e3c, 0x10104f, 0x101244, 0x101438,
            0x10164c, 0x101842, 0x101a35, 0x101c49, 0x101e3d, 0x102051,
            0x102245, 0x10243a, 0x10264e, 0x102843, 0x102a37, 0x102c4b,
            0x102e3f, 0x103053, 0x103247, 0x10343b, 0x10364f, 0x103845,
            0x103a38, 0x103c4c, 0x103e42, 0x104036, 0x104249, 0x10443d,
            0x104651, 0x104846, 0x104a3a, 0x104c4e, 0x104e43, 0x105038,
            0x10524a, 0x10543e, 0x105652, 0x105847, 0x105a3b, 0x105c4f,
            0x105e45, 0x106039, 0x10624c, 0x106441, 0x106635, 0x106849,
            0x106a3d, 0x106c51, 0x106e47, 0x10703c, 0x10724f, 0x107444,
            0x107638, 0x10784c, 0x107a3f, 0x107c53, 0x107e48};
    private static final long[] TERM_INFO = new long[]{0, 21208, 42467, 63836, 85337, 107014,
            128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989,
            308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224,
            483532, 504758};

    private static final String[] LUNAR_CAPITAL_NUM = {"零", "一", "二", "三", "四", "五", "六", "七", "八",
            "九"};
    private static final String[] LUNAR_HEADER = {"初", "十", "廿", "卅", "正", "腊", "冬", "闰"};
    private static final String[] GREGORIAN_FESTIVAL = {"元旦 ", "情人节 ", "妇女节 ", "植树节 ", "消权日 ", "愚人节 ",
            "劳动节 ", "青年节 ", "儿童节 ", "建党节 ", "建军节 ", "教师节 ", "国庆节 ", "万圣节 ", "圣诞节 "};
    private static final String[] LUNAR_FESTIVAL = {"春节 ", "元宵节 ", "端午节 ", "乞巧节 ", "中秋节 ", "重阳节 ", "腊八节 "};
    private static final String[] SOLAR_TERM = {"小寒 ", "大寒 ", "立春 ", "雨水 ", "惊蛰 ", "春分 ",
            "清明 ", "谷雨 ", "立夏 ", "小满 ", "芒种 ", "夏至 ", "小暑 ", "大暑 ", "立秋 ", "处暑 ", "白露 ",
            "秋分 ", "寒露 ", "霜降 ", "立冬 ", "小雪 ", "大雪 ", "冬至 "};

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

    private Map<Integer, List<String>> solarTermIdCreated = new HashMap<>();
    private Map<Integer, List<String>> solarTermNameCreated = new HashMap<>();

    private Map<Integer, String[][]> gregorianCreated = new HashMap<>();
    private Map<Integer, String[][]> lunarCreated = new HashMap<>();

    public CalendarBiz(int index, int year, int month) {
        buildSolarTerm(year);
        gregorianCreated.put(index, buildGregorian(year, month));
    }

    private int getBitInt(int data, int length, int shift) {
        return (data & (((1 << length) - 1) << shift)) >> shift;
    }

    private long gregorianToInt(int y, int m, int d) {
        m = (m + 9) % 12;
        y = y - m / 10;
        return 365 * y + y / 4 - y / 100 + y / 400 + (m * 306 + 5) / 10 + (d - 1);
    }

    private Gregorian gregorianFromInt(long g) {
        long y = (10000 * g + 14780) / 3652425;
        long ddd = g - (365 * y + y / 4 - y / 100 + y / 400);
        if (ddd < 0) {
            y--;
            ddd = g - (365 * y + y / 4 - y / 100 + y / 400);
        }
        long mi = (100 * ddd + 52) / 3060;
        long mm = (mi + 2) % 12 + 1;
        y = y + (mi + 2) / 12;
        long dd = ddd - (mi * 306 + 5) / 10 + 1;
        Gregorian gregorian = new Gregorian();
        gregorian.year = (int) y;
        gregorian.month = (int) mm;
        gregorian.day = (int) dd;
        return gregorian;
    }

    private Gregorian lunarToGregorian(Lunar lunar) {
        int days = LUNAR_YEAR_INFO[lunar.year - LUNAR_YEAR_INFO[0]];
        int leap = getBitInt(days, 4, 13);
        int offset = 0;
        int loopEnd = leap;
        if (!lunar.isLeap) {
            if (lunar.month <= leap || leap == 0) {
                loopEnd = lunar.month - 1;
            } else {
                loopEnd = lunar.month;
            }
        }
        for (int i = 0; i < loopEnd; i++) {
            offset += getBitInt(days, 1, 12 - i) == 1 ? 30 : 29;
        }
        offset += lunar.day;

        int gregorianToLunar = GREGORIAN_YEAR_INFO[lunar.year - GREGORIAN_YEAR_INFO[0]];

        int y = getBitInt(gregorianToLunar, 12, 9);
        int m = getBitInt(gregorianToLunar, 4, 5);
        int d = getBitInt(gregorianToLunar, 5, 0);

        return gregorianFromInt(gregorianToInt(y, m, d) + offset - 1);
    }

    private Lunar gregorianToLunar(Gregorian gregorian) {
        Lunar lunar = new Lunar();
        int index = gregorian.year - GREGORIAN_YEAR_INFO[0];
        int data = (gregorian.year << 9) | (gregorian.month << 5)
                | (gregorian.day);
        int gregorianToLunar = 0;
        if (GREGORIAN_YEAR_INFO[index] > data) {
            index--;
        }
        gregorianToLunar = GREGORIAN_YEAR_INFO[index];
        int y = getBitInt(gregorianToLunar, 12, 9);
        int m = getBitInt(gregorianToLunar, 4, 5);
        int d = getBitInt(gregorianToLunar, 5, 0);
        long offset = gregorianToInt(gregorian.year, gregorian.month,
                gregorian.day) - gregorianToInt(y, m, d);

        int days = LUNAR_YEAR_INFO[index];
        int leap = getBitInt(days, 4, 13);

        int lunarY = index + GREGORIAN_YEAR_INFO[0];
        int lunarM = 1;
        int lunarD = 1;
        offset += 1;

        for (int i = 0; i < 13; i++) {
            int dm = getBitInt(days, 1, 12 - i) == 1 ? 30 : 29;
            if (offset > dm) {
                lunarM++;
                offset -= dm;
            } else {
                break;
            }
        }
        lunarD = (int) (offset);
        lunar.year = lunarY;
        lunar.month = lunarM;
        lunar.isLeap = false;
        if (leap != 0 && lunarM > leap) {
            lunar.month = lunarM - 1;
            if (lunarM == leap + 1) {
                lunar.isLeap = true;
            }
        }
        lunar.day = lunarD;
        return lunar;
    }

    private int computeTerm(int y, int n) throws Exception {
        if (y == 2009 && n == 2) {
            TERM_INFO[n] = 43467;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900, 0, 6, 2, 5);
        Date Year1900 = calendar.getTime();
        long millis = (long) ((31556925974.7 * (y - 1900) + TERM_INFO[n] * 60000) + Year1900.getTime());
        calendar.setTimeInMillis(millis);
        return calendar.get(Calendar.DATE);
    }

    public void buildSolarTerm(int year) {

        if (null != solarTermIdCreated.get(year) && null != solarTermNameCreated.get(year)) {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);

        List<String> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for (int month = 0; month < 12; month++) {
            //noinspection ResourceType
            calendar.set(Calendar.MONDAY, month);
            int firstTerm = 0;
            int secondTerm = 0;
            try {
                firstTerm = computeTerm(year, month * 2);
                secondTerm = computeTerm(year, month * 2 + 1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            calendar.set(Calendar.DATE, firstTerm);
            ids.add(SDF.format(calendar.getTime()));
            names.add(SOLAR_TERM[month * 2]);

            calendar.set(Calendar.DATE, secondTerm);
            ids.add(SDF.format(calendar.getTime()));
            names.add(SOLAR_TERM[month * 2 + 1]);
        }

        solarTermIdCreated.put(year, ids);
        solarTermNameCreated.put(year, names);
    }

    public String gregorianToLunar(int year, int month, int day) {
        Gregorian gregorian = new Gregorian();
        gregorian.year = year;
        gregorian.month = month;
        gregorian.day = day;
        Lunar lunar = gregorianToLunar(gregorian);
        String result = String.valueOf(lunar.day);
        char[] c = result.toCharArray();
        if (c.length == 1) {
            for (int i = 1; i < 10; i++) {
                if (c[0] == String.valueOf(i).charAt(0)) {
                    result = LUNAR_HEADER[0] + LUNAR_CAPITAL_NUM[i];
                }
            }
        } else {
            if (c[0] == '1') {
                if (c[1] == '0') {
                    result = LUNAR_HEADER[0] + LUNAR_HEADER[1];
                } else {
                    for (int i = 1; i < 10; i++) {
                        if (c[1] == String.valueOf(i).charAt(0)) {
                            result = LUNAR_HEADER[1] + LUNAR_CAPITAL_NUM[i];
                        }
                    }
                }
            } else if (c[0] == '2') {
                if (c[1] == '0') {
                    result = LUNAR_HEADER[2] + LUNAR_HEADER[1];
                } else {
                    for (int i = 1; i < 10; i++) {
                        if (c[1] == String.valueOf(i).charAt(0)) {
                            result = LUNAR_HEADER[2] + LUNAR_CAPITAL_NUM[i];
                        }
                    }
                }
            } else {
                if (c[1] == '0') {
                    result = LUNAR_HEADER[3] + LUNAR_HEADER[1];
                } else {
                    for (int i = 1; i < 10; i++) {
                        if (c[1] == String.valueOf(i).charAt(0)) {
                            result = LUNAR_HEADER[3] + LUNAR_CAPITAL_NUM[i];
                        }
                    }
                }
            }
        }

        List<String> ids = solarTermIdCreated.get(year);
        List<String> names = solarTermNameCreated.get(year);

        String id = "";

        if (month < 10) {
            if (day < 10) {
                id = year + "" + 0 + "" + month + "" + 0 + "" + day;
            } else {
                id = year + "" + 0 + "" + month + "" + day;
            }
        } else {
            if (day < 10) {
                id = year + "" + month + "" + 0 + "" + day;
            } else {
                id = year + "" + month + "" + day;
            }
        }

        if (ids.contains(id)) {
            result = names.get(ids.indexOf(id));
        }

        int lunarMonth = lunar.month;
        int lunarDay = lunar.day;

        if (lunarMonth == 1 && lunarDay == 1) {
            result = LUNAR_FESTIVAL[0];
        } else if (lunarMonth == 1 && lunarDay == 15) {
            result = LUNAR_FESTIVAL[1];
        } else if (lunarMonth == 5 && lunarDay == 5) {
            result = LUNAR_FESTIVAL[2];
        } else if (lunarMonth == 7 && lunarDay == 7) {
            result = LUNAR_FESTIVAL[3];
        } else if (lunarMonth == 8 && lunarDay == 15) {
            result = LUNAR_FESTIVAL[4];
        } else if (lunarMonth == 9 && lunarDay == 9) {
            result = LUNAR_FESTIVAL[5];
        } else if (lunarMonth == 12 && lunarDay == 8) {
            result = LUNAR_FESTIVAL[6];
        }

        if (month == 1 && day == 1) {
            result = GREGORIAN_FESTIVAL[0];
        } else if (month == 2 && day == 14) {
            result = GREGORIAN_FESTIVAL[1];
        } else if (month == 3 && day == 8) {
            result = GREGORIAN_FESTIVAL[2];
        } else if (month == 3 && day == 12) {
            result = GREGORIAN_FESTIVAL[3];
        } else if (month == 3 && day == 15) {
            result = GREGORIAN_FESTIVAL[4];
        } else if (month == 4 && day == 1) {
            result = GREGORIAN_FESTIVAL[5];
        } else if (month == 5 && day == 1) {
            result = GREGORIAN_FESTIVAL[6];
        } else if (month == 5 && day == 4) {
            result = GREGORIAN_FESTIVAL[7];
        } else if (month == 6 && day == 1) {
            result = GREGORIAN_FESTIVAL[8];
        } else if (month == 7 && day == 1) {
            result = GREGORIAN_FESTIVAL[9];
        } else if (month == 8 && day == 1) {
            result = GREGORIAN_FESTIVAL[10];
        } else if (month == 9 && day == 10) {
            result = GREGORIAN_FESTIVAL[11];
        } else if (month == 10 && day == 1) {
            result = GREGORIAN_FESTIVAL[12];
        } else if (month == 10 && day == 31) {
            result = GREGORIAN_FESTIVAL[13];
        } else if (month == 12 && day == 25) {
            result = GREGORIAN_FESTIVAL[14];
        }
        return result;
    }

    public String[][] buildGregorian(int year, int month) {
        String a[][] = new String[6][7];
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);

        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int day = 0;

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = 30;
        }
        if (month == 2) {
            if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                day = 29;
            } else {
                day = 28;
            }
        }

        int nextDay = 1;
        for (int k = 0; k < 6; k++) {
            if (k == 0) {
                for (int j = week; j < 7; j++) {
                    a[k][j] = "" + nextDay;
                    nextDay++;
                }
            } else {
                for (int j = 0; j < 7 && nextDay <= day; j++) {
                    a[k][j] = "" + nextDay;
                    nextDay++;
                }
            }
        }
        return a;
    }

    public Map<Integer, String[][]> getGregorianCreated() {
        return gregorianCreated;
    }

    public Map<Integer, String[][]> getLunarCreated() {
        return lunarCreated;
    }
}
