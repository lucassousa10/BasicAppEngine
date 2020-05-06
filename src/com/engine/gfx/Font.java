package com.engine.gfx;

public class Font {

    private Image fontImage;
    private int[] offsets;
    private int[] widths;

    public Font(String path) {
        fontImage = new Image(path);
        offsets = new int[256];
        widths = new int[256];

        int unicode = 0;

        for (int i = 0; i < fontImage.getWidth(); i++) {
            if (fontImage.getImagePixelData()[i] == 0xff0000ff) {
                offsets[unicode] = i;
            }

            if (fontImage.getImagePixelData()[i] == 0xffffff00) {
                widths[unicode] = i - offsets[unicode];
                unicode++;
            }
        }
    }

    public static Font standard() {
        return new Font("/com/engine/gfx/jbmono24.png");
    }

    public Image getFontImage() {
        return fontImage;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public int[] getWidths() {
        return widths;
    }
}
