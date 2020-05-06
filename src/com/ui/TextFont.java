package com.ui;

import com.engine.gfx.Font;

public class TextFont {

    private Font font;
    private String text;
    private int textWidth;

    public TextFont(String text, Font font) {
        this.font = font;
        this.text = text;
        defineTextWidth();
    }

    public void defineTextWidth() {
        textWidth = 0;
        for (int i = 0; i < text.length(); i++) {
            textWidth += font.getWidths()[text.codePointAt(i)];
        }
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
        defineTextWidth();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        defineTextWidth();
    }

    public int getTextWidth() {
        return textWidth;
    }
}
