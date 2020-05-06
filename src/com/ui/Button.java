package com.ui;

import com.engine.Engine;
import com.engine.Input;
import com.engine.Renderer;
import com.engine.entities.ApplicationObject;
import com.engine.gfx.Font;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Basic button widget component with a basic click listener.
 */
public class Button extends ApplicationObject {

    private final TextFont textFont;
    private Style style;
    private Rectangle rect;

    private boolean hovering = false;
    private boolean clicking = false, clicked = false;

    private ClickListener clickListener;

    public Button(String text) {
        super();

        //std sizes
        this.rect = new Rectangle(0, 0, 80, 30);

        //std style
        this.style = new Style(0xffcae1e6, 0xffb8cfd4, 0xff56696e, 0xff91a6ab, 0xff000000, new Font("/com/ui/fonts/arial12.png"), 12);

        textFont = new TextFont(text, style.getFont());
    }

    public void setClickListener(ClickListener listener) {
        this.clickListener = listener;
    }

    public void notifyClickListener() {
        if (clickListener != null) clickListener.onClick(this);
    }

    @Override
    public void update(Engine engine, float deltaTime) {
        hovering = rect.contains(Input.mouseX, Input.mouseY);
        clicking = hovering && Input.isMouseButton(MouseEvent.BUTTON1);
        clicked = hovering && Input.isMouseButtonUp(MouseEvent.BUTTON1);

        if (clicked) notifyClickListener();
    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        renderer.fillRect(rect.x, rect.y, rect.width, rect.height, !clicking ? !hovering ? style.rawBodyColor : style.hoverBodyColor : style.onClickColor);
        renderer.drawRect(rect.x, rect.y, rect.width, rect.height, style.boundsColor);
        renderer.drawText(
                textFont.getText(),
                rect.x + ((rect.width / 2) - (textFont.getTextWidth() / 2)),
                rect.y + ((rect.height / 2) - (style.fontSize / 2)),
                style.textColor,
                style.font);
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
        textFont.setFont(style.getFont());
        textFont.defineTextWidth();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Basic structure to style a button.
     */
    public static class Style {

        private int rawBodyColor;
        private int hoverBodyColor;
        private int boundsColor;
        private int onClickColor;
        private int textColor;

        private Font font;
        private int fontSize;

        public Style(int rawBodyColor, int hoverBodyColor, int boundsColor, int onClickColor, int textColor, Font font, int fontSize) {
            this.rawBodyColor = rawBodyColor;
            this.hoverBodyColor = hoverBodyColor;
            this.boundsColor = boundsColor;
            this.onClickColor = onClickColor;
            this.textColor = textColor;
            this.font = font;
            this.fontSize = fontSize;
        }

        public int getRawBodyColor() {
            return rawBodyColor;
        }

        public void setRawBodyColor(int rawBodyColor) {
            this.rawBodyColor = rawBodyColor;
        }

        public int getHoverBodyColor() {
            return hoverBodyColor;
        }

        public void setHoverBodyColor(int hoverBodyColor) {
            this.hoverBodyColor = hoverBodyColor;
        }

        public int getBoundsColor() {
            return boundsColor;
        }

        public void setBoundsColor(int boundsColor) {
            this.boundsColor = boundsColor;
        }

        public int getOnClickColor() {
            return onClickColor;
        }

        public void setOnClickColor(int onClickColor) {
            this.onClickColor = onClickColor;
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

    /**
     * Interface to intercept and carry implementations of events to be fired at click moment.
     */
    public interface ClickListener {
        void onClick(ApplicationObject relatedObject);
    }
}
