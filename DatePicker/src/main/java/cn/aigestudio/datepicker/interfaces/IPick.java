package cn.aigestudio.datepicker.interfaces;

/**
 * 日历控件方法接口
 * 所有可供调用的方法都会在此定义
 * The interface of DatePicker{@link cn.aigestudio.datepicker.views.DatePicker}
 * All of method you can invoke will be defined here.
 *
 * @author AigeStudio 2015-03-26
 */
public interface IPick {
    /**
     * 设置日期选择后的回调接口
     * Set the callback interface when date picked.
     *
     * @param onDateSelected 日期选择的回调接口(The callback interface when date picked)
     */
    void setOnDateSelected(OnDateSelected onDateSelected);

    /**
     * 设置日历的主色调
     * 日历由黑白灰三色加一主色调构成，该方法用来设置其主色调
     * Set the primary colour of DatePicker{@link cn.aigestudio.datepicker.views.DatePicker}
     * By default,DatePicker's tone consists of four colors black-white-gray and primary colour,
     * you can use this method set the primary colour.
     *
     * @param color 16位颜色值(Value like 0xFFFFFFFF)
     */
    void setColor(int color);

    /**
     * 设置是否显示农历
     * Set is lunar display.
     *
     * @param display true表示显示农历，false反之(true display lunar,false otherwise)
     */
    void isLunarDisplay(boolean display);
}
