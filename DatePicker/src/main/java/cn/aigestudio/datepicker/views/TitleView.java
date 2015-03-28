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
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.aigestudio.datepicker.entities.Language;
import cn.aigestudio.datepicker.interfaces.OnDateSelected;

/**
 * 日历选择器的标题视图
 * <p/>
 * Title of DatePicker{@link cn.aigestudio.datepicker.views.DatePicker}.
 *
 * @author AigeStudio https://github.com/AigeStudio
 * @version 1.0.0 beta
 * @since 2015/3/26
 */
public class TitleView extends LinearLayout implements CalendarView.OnPageChange, CalendarView.OnSizeChanged {
    private String[] monthTitles;
    private TextView tvYear, tvMonth, tvComfirm;
    private OnDateSelected mOnDateSelected;
    private CalendarView mCalendarView;

    public TitleView(Context context) {
        super(context);
        setColor(0xFFE95344);
        setOrientation(HORIZONTAL);

        monthTitles = Language.getLanguage(context).monthTitles();

        LayoutParams llParams = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);

        tvYear = new TextView(context);
        tvYear.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
        tvYear.setTextColor(Color.WHITE);

        tvMonth = new TextView(context);
        tvMonth.setGravity(Gravity.CENTER);
        tvMonth.setTextColor(Color.WHITE);

        tvComfirm = new TextView(context);
        tvComfirm.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        tvComfirm.setText(Language.getLanguage(context).ensureTitle());
        tvComfirm.setTextColor(Color.WHITE);
        tvComfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnDateSelected && null != mCalendarView)
                    mOnDateSelected.selected(mCalendarView.getDateSelected());
            }
        });

        addView(tvYear, llParams);
        addView(tvMonth, llParams);
        addView(tvComfirm, llParams);
    }

    public void setOnDateSelected(OnDateSelected onDateSelected, CalendarView calendarView) {
        mOnDateSelected = onDateSelected;
        mCalendarView = calendarView;
    }

    public void setColor(int color) {
        setBackgroundColor(color);
    }

    @Override
    public void onMonthChange(int month) {
        tvMonth.setText(monthTitles[month - 1]);
    }

    @Override
    public void onYearChange(int year) {
        tvYear.setText(String.valueOf(year));
    }

    @Override
    public void onSizeChanged(int size) {
        int padding = (int) (size * 1F / 50F);
        int textSizeSmall = (int) (size * 1F / 25F);
        int textSizeLarge = (int) (size * 1F / 18F);

        tvYear.setPadding(padding, padding, 0, padding);
        tvYear.getPaint().setTextSize(textSizeSmall);

        tvMonth.setPadding(0, padding, 0, padding);
        tvMonth.getPaint().setTextSize(textSizeLarge);

        tvComfirm.setPadding(0, padding, padding, padding);
        tvComfirm.getPaint().setTextSize(textSizeSmall);
    }
}
