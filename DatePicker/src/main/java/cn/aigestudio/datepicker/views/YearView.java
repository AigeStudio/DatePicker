package cn.aigestudio.datepicker.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Region;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 年视图
 * 该部分功能将会在2.0.0之前被启用
 * View of years
 * The part of this will be available before 2.0.0
 *
 * @author AigeStudio 2015-05-21
 */
public class YearView extends View {
    private static final String[][] MONTHS = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"},
            {"9", "10", "11", "12"}};

    private TextPaint mTextPaint;

    private int sizeBase;
    private int textSizeMonth;

    private Region[][] mRegion = new Region[3][4];

    public YearView(Context context) {
        this(context, null);
    }

    public YearView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YearView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);

        setMeasuredDimension(width, (int) (width * 3F / 4F));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        sizeBase = w;

        textSizeMonth = (int) (sizeBase / 10F);
        mTextPaint.setTextSize(textSizeMonth);

        int sizeCell = (int) (sizeBase / 4F);

        for (int i = 0; i < mRegion.length; i++) {
            for (int j = 0; j < mRegion[i].length; j++) {
                Region region = new Region();
                region.set((j * sizeCell), (i * sizeCell), sizeCell + (j * sizeCell), sizeCell + (i * sizeCell));
                mRegion[i][j] = region;
            }
        }
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mRegion.length; i++) {
            for (int j = 0; j < mRegion[i].length; j++) {
                canvas.drawText(MONTHS[i][j], mRegion[i][j].getBounds().centerX(),
                        mRegion[i][j].getBounds().centerY(), mTextPaint);
            }
        }
    }
}
