package cn.aigestudio.datepicker.entities;

import android.graphics.drawable.ShapeDrawable;

/**
 * 背景圆实体对象
 * Entity of background circle.
 *
 * @author AigeStudio 2015-05-22
 */
public class BGCircle {
    private float x, y;
    private int radius;

    private ShapeDrawable shape;

    public BGCircle(ShapeDrawable shape) {
        this.shape = shape;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public ShapeDrawable getShape() {
        return shape;
    }

    public void setShape(ShapeDrawable shape) {
        this.shape = shape;
    }
}
