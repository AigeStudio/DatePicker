package cn.aigestudio.datepicker.entities;

/**
 * 中文的默认实现类
 * 如果你想实现更多的语言请参考Language{@link Language}
 * The implementation class of chinese.
 * You can refer to Language{@link Language} if you want to define more language.
 *
 * @author AigeStudio 2015-03-28
 */
public class CN extends Language {
    @Override
    public String[] monthTitles() {
        return new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    }

    @Override
    public String ensureTitle() {
        return "确定";
    }
}
