package cn.aigestudio.datepicker.bizs.decors;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * 月历装饰物类
 * Decor of Calendar
 *
 * @author AigeStudio 2015-07-22
 * @author AigeStudio 2015-10-29
 *         为每一个装饰物的绘制方法增加一个含参data的重载方法
 *         Add a parameter for each method.
 */
public class DPDecor {
    /**
     * 绘制当前日期区域左上角的装饰物
     * Draw decor on Top left of current date area
     *
     * @param canvas 绘制图形的画布 Canvas of image drew
     * @param rect   可以绘制的区域范围 Area you can draw
     * @param paint  画笔对象 Paint
     * @param data   日期
     */
    public void drawDecorTL(Canvas canvas, Rect rect, Paint paint, String data) {
        drawDecorTL(canvas, rect, paint);
    }

    /**
     * @see #drawDecorTL(Canvas, Rect, Paint, String)
     */
    public void drawDecorTL(Canvas canvas, Rect rect, Paint paint) {

    }

    /**
     * 绘制当前日期区域顶部的装饰物
     * Draw decor on top of current date area
     *
     * @param canvas 绘制图形的画布 Canvas of image drew
     * @param rect   可以绘制的区域范围 Area you can draw
     * @param paint  画笔对象 Paint
     * @param data   日期
     */
    public void drawDecorT(Canvas canvas, Rect rect, Paint paint, String data) {
        drawDecorT(canvas, rect, paint);
    }

    /**
     * @see #drawDecorT(Canvas, Rect, Paint, String)
     */
    public void drawDecorT(Canvas canvas, Rect rect, Paint paint) {

    }

    /**
     * 绘制当前日期区域右上角的装饰物
     * Draw decor on Top right of current date area
     *
     * @param canvas 绘制图形的画布 Canvas of image drew
     * @param rect   可以绘制的区域范围 Area you can draw
     * @param paint  画笔对象 Paint
     * @param data   日期
     */
    public void drawDecorTR(Canvas canvas, Rect rect, Paint paint, String data) {
        drawDecorTR(canvas, rect, paint);
    }

    /**
     * @see #drawDecorTR(Canvas, Rect, Paint, String)
     */
    public void drawDecorTR(Canvas canvas, Rect rect, Paint paint) {

    }

    /**
     * 绘制当前日期区域左边的装饰物
     * Draw decor on left of current date area
     *
     * @param canvas 绘制图形的画布 Canvas of image drew
     * @param rect   可以绘制的区域范围 Area you can draw
     * @param paint  画笔对象 Paint
     * @param data   日期
     */
    public void drawDecorL(Canvas canvas, Rect rect, Paint paint, String data) {
        drawDecorL(canvas, rect, paint);
    }

    /**
     * @see #drawDecorL(Canvas, Rect, Paint, String)
     */
    public void drawDecorL(Canvas canvas, Rect rect, Paint paint) {

    }

    /**
     * 绘制当前日期区域右边的装饰物
     * Draw decor on right of current date area
     *
     * @param canvas 绘制图形的画布 Canvas of image drew
     * @param rect   可以绘制的区域范围 Area you can draw
     * @param paint  画笔对象 Paint
     * @param data   日期
     */
    public void drawDecorR(Canvas canvas, Rect rect, Paint paint, String data) {
        drawDecorR(canvas, rect, paint);
    }

    /**
     * @see #drawDecorR(Canvas, Rect, Paint, String)
     */
    public void drawDecorR(Canvas canvas, Rect rect, Paint paint) {

    }

    /**
     * 绘制当前日期区域背景的装饰物
     * Draw decor of background of current date area
     *
     * @param canvas 绘制图形的画布 Canvas of image drew
     * @param rect   可以绘制的区域范围 Area you can draw
     * @param paint  画笔对象 Paint
     * @param data   日期
     */
    public void drawDecorBG(Canvas canvas, Rect rect, Paint paint, String data) {
        drawDecorBG(canvas, rect, paint);
    }

    /**
     * @see #drawDecorBG(Canvas, Rect, Paint, String)
     */
    public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {

    }
}
