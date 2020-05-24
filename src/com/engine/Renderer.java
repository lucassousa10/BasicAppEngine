package com.engine;

import com.engine.gfx.Shape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Renderer {

    public Graphics g;
    public Graphics2D g2d;
    private final BufferedImage img;
    private final int imageWidth;
    private final int imageHeight;

    public int[] pixelData;

    public Renderer(Engine engine) {
        img = engine.getWindow().getImage();

        pixelData = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

        g = img.getGraphics();
        g2d = img.createGraphics();
        imageWidth = engine.getWindow().getImage().getWidth();
        imageHeight = engine.getWindow().getImage().getHeight();
    }

    public void clear() {
        Arrays.fill(pixelData, 0);
    }

    /**
     * Basic call wrap to main Graphics2D to draw a Shape.
     * Note that this Shape is not {@code java.awt.Shape},
     * but {@code com.engine.gfx.Shape}.
     *
     * The Shape class of this project inherits {@code Path2D.Float},
     * for this reason a instance of {@code com.engine.gfx.Shape} can
     * be drawn by a Graphics2D element.
     *
     * @param shape shape to be draw by the default graphics
     * @param color color of the drawing
     *
     * @see Shape
     */
    public void drawShape(Shape shape, Color color) {
        g2d.setColor(color);
        g2d.draw(shape);
    }

    public void fillShape(Shape shape, Color color) {
        g2d.setColor(color);
        g2d.fill(shape);
    }

    /**
     * This allows set pixel directly to graphics image without use
     * the Graphics2D class. This attempts to be faster than use
     * builtin in graphics methods, however, this requires custom
     * implementation of any kind of rendering in a pixel-by-pixel
     * plotting way.
     *
     * @param x the x coordinate of the pixel to set
     * @param y the y coordinate of the pixel to set
     * @param value the color the pixel should have. This is treated as a ARGB color.
     * @return true if the pixel was drawn, false if the coordinates wasn't in the screen bounds.
     */
    public boolean setPixel(int x, int y, int value) {
        if ((x < 0 || x >= imageWidth || y < 0 || y >= imageHeight) || value == 0xffff00ff) {
            return false;
        }

        pixelData[x + y * imageWidth] = value;
        return true;
    }

    public int getPixel(int x, int y) {
        if ((x < 0 || x >= imageWidth) || (y < 0 || y >= imageHeight)) {
            return -1;
        }

        return pixelData[x + y * imageWidth];
    }
}
