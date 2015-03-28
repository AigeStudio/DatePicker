/*
 Copyright 2014-2015 AigeStudio(https://github.com/AigeStudio)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package cn.aigestudio.datepicker.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.text.TextPaint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aigestudio.datepicker.business.CalendarBiz;
import cn.aigestudio.datepicker.utils.LogUtil;

/**
 * 日历视图
 * <p/>
 * CalendarView.
 *
 * @author AigeStudio https://github.com/AigeStudio
 * @version 1.0.0 beta
 * @since 2015/3/26
 */
public class CalendarView extends View {
    private static final float RATIO_TEXT_SIZE_GREGORIAN = 1 / 20F, RATIO_TEXT_SIZE_LUNAR = 1 / 35F;

    private float circleRadius;
    private float textSizeGregorian, textSizeLunar;
    private float offsetYLunar;

    private int sizeBase;
    private int monthIndex;
    private int lastMonth, currentMonth, nextMonth;
    private int lastYear, currentYear, nextYear;
    private int color = 0xFFE95344;

    private boolean isLunarShow = true;

    private Paint mPaint, mTextPaint;
    private Scroller mScroller;
    private GestureDetector mGestureDetector;
    private OnPageChange mPageChange;
    private OnSizeChanged mSizeChanged;
    private CalendarBiz mCalendarBiz;

    private Region[][] mRegion = new Region[6][7];

    private List<String> dateSelected = new ArrayList<>();

    private Map<Integer, List<Region>> calendarRegion = new HashMap<>();

    public interface OnPageChange {
        void onMonthChange(int month);

        void onYearChange(int year);
    }

    public interface OnSizeChanged {
        void onSizeChanged(int size);
    }

    public CalendarView(Context context) {
        super(context);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.DEV_KERN_TEXT_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mScroller = new Scroller(context);

        Calendar calendar = Calendar.getInstance();

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;

        computeDate();

        mCalendarBiz = new CalendarBiz(monthIndex, currentYear, currentMonth);

        mGestureDetector = new GestureDetector(new CalendarGestureListener());

        buildCalendarRegion();
    }

    private void buildCalendarRegion() {
        if (!calendarRegion.containsKey(monthIndex)) {
            List<Region> regions = new ArrayList<>();
            calendarRegion.put(monthIndex, regions);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    private void defineContainRegion(int x, int y) {
        for (int i = 0; i < mRegion.length; i++) {
            for (int j = 0; j < mRegion[i].length; j++) {
                Region region = mRegion[i][j];
                if (null == mCalendarBiz.getGregorianCreated().get(monthIndex)[i][j]) {
                    continue;
                }

                if (region.contains(x, y)) {
                    List<Region> regions = calendarRegion.get(monthIndex);
                    if (regions.contains(region)) {
                        regions.remove(region);
                    } else {
                        regions.add(region);
                    }

                    String date = currentYear + "-" + currentMonth + "-" + mCalendarBiz.getGregorianCreated().get(monthIndex)[i][j];
                    if (dateSelected.contains(date)) {
                        dateSelected.remove(date);
                    } else {
                        dateSelected.add(date);
                    }
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int result = 0;

        String[][] currentGregorian = mCalendarBiz.getGregorianCreated().get(monthIndex);

        Configuration newConfig = getResources().getConfiguration();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            result = Math.max(width, height);
            if (height > width) {
                result = height;
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            result = Math.min(width, height);
            if (width > height) {
                result = width;
            }
        }

        if (null == currentGregorian[4][0]) {
            setMeasuredDimension(result, (int) (result * 4 / 7F));
        } else if (null == currentGregorian[5][0]) {
            setMeasuredDimension(result, (int) (result * 5 / 7F));
        } else {
            setMeasuredDimension(result, (int) (result * 6 / 7F));
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        sizeBase = w;

        if (null != mSizeChanged) {
            mSizeChanged.onSizeChanged(sizeBase);
        }

        int sizeCell = (int) (sizeBase / 7F);
        circleRadius = sizeCell / 2F;

        textSizeGregorian = sizeBase * RATIO_TEXT_SIZE_GREGORIAN;
        mTextPaint.setTextSize(textSizeGregorian);
        float gregorianH = mTextPaint.getFontMetrics().bottom - mTextPaint.getFontMetrics().top;
        textSizeLunar = sizeBase * RATIO_TEXT_SIZE_LUNAR;

        mTextPaint.setTextSize(textSizeLunar);
        float lunarH = mTextPaint.getFontMetrics().bottom - mTextPaint.getFontMetrics().top;
        offsetYLunar = (((Math.abs(mTextPaint.ascent() + mTextPaint.descent())) / 2) + lunarH / 2 + gregorianH / 2)
                * 3F / 4F;

        for (int i = 0; i < mRegion.length; i++) {
            for (int j = 0; j < mRegion[i].length; j++) {
                Region region = new Region();
                region.set((j * sizeCell), (i * sizeCell), sizeCell + (j * sizeCell), sizeCell + (i * sizeCell));
                mRegion[i][j] = region;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Region region : calendarRegion.get(monthIndex)) {
            mPaint.setColor(0xFFDCDCDC);
            canvas.drawCircle(region.getBounds().centerX() + monthIndex * sizeBase, region.getBounds().centerY(), circleRadius,
                    mPaint);
        }

        drawCalendar(canvas, (monthIndex - 1) * sizeBase, lastYear, lastMonth);
        drawCalendar(canvas, monthIndex * sizeBase, currentYear, currentMonth);
        drawCalendar(canvas, (monthIndex + 1) * sizeBase, nextYear, nextMonth);
    }

    private void drawCalendar(Canvas canvas, float offsetX, int year, int month) {
        canvas.save();
        canvas.translate(offsetX, 0);

        int current = (int) (offsetX / sizeBase);

        mTextPaint.setTextSize(textSizeGregorian);
        mTextPaint.setColor(Color.BLACK);

        String[][] gregorianCurrent = mCalendarBiz.getGregorianCreated().get(current);
        if (null == gregorianCurrent) {
            gregorianCurrent = mCalendarBiz.buildGregorian(year, month);
        }

        for (int i = 0; i < gregorianCurrent.length; i++) {
            for (int j = 0; j < gregorianCurrent[i].length; j++) {
                String str = gregorianCurrent[i][j];
                if (null == str) {
                    str = "";
                }
                canvas.drawText(str, mRegion[i][j].getBounds().centerX(),
                        mRegion[i][j].getBounds().centerY(), mTextPaint);
            }
        }

        if (isLunarShow) {
            String[][] lunarCurrent = mCalendarBiz.getLunarCreated().get(current);
            if (null == lunarCurrent) {
                lunarCurrent = gregorianToLunar(gregorianCurrent, year, month);
            }
            mTextPaint.setTextSize(textSizeLunar);
            for (int i = 0; i < lunarCurrent.length; i++) {
                for (int j = 0; j < lunarCurrent[i].length; j++) {
                    String str = lunarCurrent[i][j];
                    if (str.contains(" ")) {
                        str.trim();
                        mTextPaint.setColor(color);
                    } else {
                        mTextPaint.setColor(Color.GRAY);
                    }
                    canvas.drawText(str, mRegion[i][j].getBounds().centerX(), mRegion[i][j].getBounds().centerY() +
                            offsetYLunar, mTextPaint);
                }
            }
            mCalendarBiz.getLunarCreated().put(current, lunarCurrent);
        }

        mCalendarBiz.getGregorianCreated().put(current, gregorianCurrent);
        canvas.restore();
    }

    private String[][] gregorianToLunar(String[][] gregorian, int year, int month) {
        String[][] lunar = new String[6][7];
        for (int i = 0; i < gregorian.length; i++) {
            for (int j = 0; j < gregorian[i].length; j++) {
                String str = gregorian[i][j];
                if (null == str) {
                    str = "";
                } else {
                    str = mCalendarBiz.gregorianToLunar(year, month, Integer.valueOf(str));
                }
                lunar[i][j] = str;
            }
        }
        return lunar;
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        } else {
            requestLayout();
        }
    }

    private void computeDate() {
        nextYear = lastYear = currentYear;
        nextMonth = currentMonth + 1;
        lastMonth = currentMonth - 1;

        if (null != mPageChange) {
            mPageChange.onYearChange(currentYear);
        }

        if (currentMonth == 12) {
            nextYear++;
            mCalendarBiz.buildSolarTerm(nextYear);
            if (null != mPageChange) {
                mPageChange.onYearChange(nextYear);
            }
            nextMonth = 1;
        }

        if (currentMonth == 1) {
            lastYear--;
            mCalendarBiz.buildSolarTerm(lastYear);
            if (null != mPageChange) {
                mPageChange.onYearChange(lastYear);
            }
            lastMonth = 12;
        }
    }

    public void setOnPageChange(OnPageChange onPageChange) {
        mPageChange = onPageChange;
        if (null != mPageChange) {
            mPageChange.onYearChange(currentYear);
            mPageChange.onMonthChange(currentMonth);
        }
    }

    public synchronized void setLunarShow(boolean isLunarShow) {
        this.isLunarShow = isLunarShow;
        invalidate();
    }

    public void setOnSizeChanged(OnSizeChanged onSizeChanged) {
        mSizeChanged = onSizeChanged;
    }

    public List<String> getDateSelected() {
        return dateSelected;
    }

    public synchronized void setColor(int color) {
        this.color = color;
        invalidate();
    }

    private class CalendarGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float dis = e1.getX() - e2.getX();
            if (dis > 0) {
                monthIndex++;
                currentMonth = (currentMonth + 1) % 13;
                if (currentMonth == 0) {
                    currentMonth = 1;
                    currentYear++;
                    mCalendarBiz.buildSolarTerm(currentYear);
                }

                computeDate();

                if (null != mPageChange) {
                    mPageChange.onMonthChange(currentMonth);
                    mPageChange.onYearChange(currentYear);
                }

                buildCalendarRegion();
                CalendarView.this.smoothScrollBy(sizeBase, 0);
            } else {
                monthIndex--;
                currentMonth = (currentMonth - 1) % 12;
                if (currentMonth == 0) {
                    currentMonth = 12;
                    currentYear--;
                    mCalendarBiz.buildSolarTerm(currentYear);
                }

                computeDate();

                if (null != mPageChange) {
                    mPageChange.onMonthChange(currentMonth);
                    mPageChange.onYearChange(currentYear);
                }

                buildCalendarRegion();
                CalendarView.this.smoothScrollBy(-sizeBase, 0);
            }
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            defineContainRegion((int) e.getX(), (int) e.getY());

            invalidate();
            return false;
        }
    }
}
