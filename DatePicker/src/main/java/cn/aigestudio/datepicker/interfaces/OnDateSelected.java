package cn.aigestudio.datepicker.interfaces;

import java.util.List;

/**
 * 日期选择后的回调接口
 * The callback interface when date picked.
 *
 * @author AigeStudio 2015-03-26
 */
public interface OnDateSelected {
    /**
     * 日期选择后的调用方法
     * 列表中日期字符串的格式为yyyy-MM-dd
     * The method when date picked will be called.
     * The format of string in list like yyyy-MM-dd.
     *
     * @param date 选择的日期列表(list of date picked)
     */
    void selected(List<String> date);
}
