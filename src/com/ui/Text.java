package com.ui;

import com.engine.Engine;
import com.engine.Renderer;
import com.engine.entities.ApplicationObject;
import com.engine.gfx.Font;

import java.awt.*;

public class Text extends ApplicationObject {

    private Style style;
    private TextFont textFont;
    private Rectangle rect;

    public Text(String text) {
        this.style = new Style(0xff000000, new Font("/com/ui/fonts/arial12.png"), 12);
        this.textFont = new TextFont(text, this.style.font);
        this.rect = new Rectangle(0, 0, textFont.getTextWidth(), style.fontSize);
    }

    public String getText() {
        return textFont.getText();
    }

    public void setText(String text) {
        textFont.setText(text);
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
        textFont.setFont(style.font);
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        renderer.drawText(textFont.getText(), rect.x, rect.y, style.textColor, style.font);
    }

    public static class Style {

        private int textColor;
        private Font font;
        private int fontSize;

        public Style(int textColor, Font font, int fontSize) {
            this.textColor = textColor;
            this.font = font;
            this.fontSize = fontSize;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public Font getFont() {
            return font;
        }

        public void setFont(Font font) {
            this.font = font;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }
    }
}
