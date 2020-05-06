package com.engine.util;

public class Vector2 {

    private double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 sum(Vector2 vector2) {
        return new Vector2(x + vector2.x, y + vector2.y);
    }

    public Vector2 multiply(double factor) {
        return new Vector2(x * factor, y * factor);
    }

    public Vector2 rounded() {
        return new Vector2(Math.round(x), Math.round(y));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
