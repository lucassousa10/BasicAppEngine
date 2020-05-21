package com.engine.ui;

import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;

import java.awt.*;

public class Button extends AbstractApplication {

    public String text;
    public Rectangle rect;

    public Button(String text) {
        this.text = text;
        rect = new Rectangle();
        rect.width = Style.WIDTH;
        rect.height = Style.HEIGHT;
    }

    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {

    }

    public static class Style {
        public static final int WIDTH = 80;
        public static final int HEIGHT = 20;
        public static final float TEXT_SCALE = 1f;

        public static final int PRIMARY_COLOR = 0xffdbf4ff;
        public static final int BORDER_COLOR = 0xffdbefff;
        public static final int TEXT_COLOR = 0xff000000;
    }
}
