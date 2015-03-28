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
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.List;

import cn.aigestudio.datepicker.entities.CN;
import cn.aigestudio.datepicker.entities.EN;
import cn.aigestudio.datepicker.entities.Language;
import cn.aigestudio.datepicker.interfaces.IPick;
import cn.aigestudio.datepicker.interfaces.OnDateSelected;
import cn.aigestudio.datepicker.utils.SystemUtil;

/**
 * 日历选择器
 * <p/>
 * DatePicker.
 *
 * @author AigeStudio https://github.com/AigeStudio
 * @version 1.0.0 beta
 * @since 2015/3/26
 */
public class DatePicker extends LinearLayout implements IPick {
    private CalendarView mCalendarView;
    private TitleView mTitleView;

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);
        setOrientation(VERTICAL);

        LayoutParams llParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        mTitleView = new TitleView(context);
        addView(mTitleView, llParams);

        mCalendarView = new CalendarView(context);
        mCalendarView.setOnPageChange(mTitleView);
        mCalendarView.setOnSizeChanged(mTitleView);
        addView(mCalendarView, llParams);
    }

    @Override
    public void setOnDateSelected(OnDateSelected onDateSelected) {
        mTitleView.setOnDateSelected(onDateSelected, mCalendarView);
    }

    @Override
    public void setColor(int color) {
        mTitleView.setColor(color);
        mCalendarView.setColor(color);
    }

    @Override
    public void isLunarDisplay(boolean display) {
        mCalendarView.setLunarShow(display);
    }
}
