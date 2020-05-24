package com.engine;

import com.engine.gfx.Shape;
import com.engine.gfx.SvgPathParsing;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
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

    public void drawShape(Shape shape, Color color) {
        g2d.setColor(color);
        g2d.draw(shape);
    }

    public void fillShape(Shape shape, Color color) {
        g2d.setColor(color);
        g2d.fill(shape);
    }

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
