package cn.aigestudio.datepicker.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.aigestudio.datepicker.bizs.decors.DPDecor;
import cn.aigestudio.datepicker.bizs.languages.DPLManager;
import cn.aigestudio.datepicker.bizs.themes.DPTManager;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.utils.MeasureUtil;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * DatePicker
 *
 * @author AigeStudio 2015-06-29
 */
public class DatePicker extends LinearLayout {
    private DPTManager mTManager;
    private DPLManager mLManager;

    private MonthView monthView;
    private TextView tvYear, tvMonth;
    private TextView tvEnsure;


    private OnDateSelectedListener onDateSelectedListener;

    /**
     * 日期单选监听器
     */
    public interface OnDatePickedListener {
        void onDatePicked(String date);
    }

    /**
     * 日期多选监听器
     */
    public interface OnDateSelectedListener {
        void onDateSelected(List<String> date);
    }

    public DatePicker(Context context) {
        this(context, null);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTManager = DPTManager.getInstance();
        mLManager = DPLManager.getInstance();

        setOrientation(VERTICAL);

        RelativeLayout rlTitle = new RelativeLayout(context);
        rlTitle.setBackgroundColor(mTManager.colorTitleBG());
        int rlTitlePadding = MeasureUtil.dp2px(context, 10);
        rlTitle.setPadding(rlTitlePadding, rlTitlePadding, rlTitlePadding, rlTitlePadding);

        LinearLayout llWeek = new LinearLayout(context);
        llWeek.setBackgroundColor(mTManager.colorTitleBG());
        llWeek.setOrientation(HORIZONTAL);
        int llWeekPadding = MeasureUtil.dp2px(context, 5);
        llWeek.setPadding(0, llWeekPadding, 0, llWeekPadding);
        LayoutParams lpWeek = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpWeek.weight = 1;

        RelativeLayout.LayoutParams lpYear =
                new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpYear.addRule(RelativeLayout.CENTER_VERTICAL);
        RelativeLayout.LayoutParams lpMonth =
                new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpMonth.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout.LayoutParams lpEnsure =
                new RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpEnsure.addRule(RelativeLayout.CENTER_VERTICAL);
        lpEnsure.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        tvYear = new TextView(context);
        tvYear.setText("2015");
        tvYear.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        tvYear.setTextColor(mTManager.colorTitle());

        tvMonth = new TextView(context);
        tvMonth.setText("六月");
        tvMonth.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        tvMonth.setTextColor(mTManager.colorTitle());

        tvEnsure = new TextView(context);
        tvEnsure.setText(mLManager.titleEnsure());
        tvEnsure.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        tvEnsure.setTextColor(mTManager.colorTitle());
        tvEnsure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onDateSelectedListener) {
                    onDateSelectedListener.onDateSelected(monthView.getDateSelected());
                }
            }
        });

        rlTitle.addView(tvYear, lpYear);
        rlTitle.addView(tvMonth, lpMonth);
        rlTitle.addView(tvEnsure, lpEnsure);

        for (int i = 0; i < mLManager.titleWeek().length; i++) {
            TextView tvWeek = new TextView(context);
            tvWeek.setText(mLManager.titleWeek()[i]);
            tvWeek.setGravity(Gravity.CENTER);
            tvWeek.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
            tvWeek.setTextColor(mTManager.colorTitle());
            llWeek.addView(tvWeek, lpWeek);
        }
        addView(rlTitle, new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        addView(llWeek, new LayoutParams(MATCH_PARENT, WRAP_CONTENT));

        monthView = new MonthView(context);
        monthView.setOnDateChangeListener(new MonthView.OnDateChangeListener() {
            @Override
            public void onMonthChange(int month) {
                tvMonth.setText(mLManager.titleMonth()[month - 1]);
            }

            @Override
            public void onYearChange(int year) {
                String tmp = String.valueOf(year);
                if (tmp.startsWith("-")) {
                    tmp = tmp.replace("-", mLManager.titleBC());
                }
                tvYear.setText(tmp);
            }
        });
        addView(monthView, new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
    }

    /**
     * 设置初始化年月日期
     *
     * @param year  ...
     * @param month ...
     */
    public void setDate(int year, int month) {
        if (month < 1) {
            month = 1;
        }
        if (month > 12) {
            month = 12;
        }
        monthView.setDate(year, month);
    }

    public void setDPDecor(DPDecor decor) {
        monthView.setDPDecor(decor);
    }

    /**
     * 设置日期选择模式
     *
     * @param mode ...
     */
    public void setMode(DPMode mode) {
        if (mode == DPMode.SINGLE) {
            tvEnsure.setVisibility(GONE);
        }
        monthView.setDPMode(mode);
    }

    /**
     * 设置单选监听器
     *
     * @param onDatePickedListener ...
     */
    public void setOnDatePickedListener(OnDatePickedListener onDatePickedListener) {
        if (monthView.getDPMode() != DPMode.SINGLE) {
            throw new RuntimeException(
                    "Current DPMode does not SINGLE! Please call setMode set DPMode to SINGLE!");
        }
        monthView.setOnDatePickedListener(onDatePickedListener);
    }

    /**
     * 设置多选监听器
     *
     * @param onDateSelectedListener ...
     */
    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        if (monthView.getDPMode() != DPMode.MULTIPLE) {
            throw new RuntimeException(
                    "Current DPMode does not MULTIPLE! Please call setMode set DPMode to MULTIPLE!");
        }
        this.onDateSelectedListener = onDateSelectedListener;
    }
}
