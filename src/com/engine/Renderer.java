package com.engine;

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

    public void drawSvgPathOperations(Path2D path, ArrayList<SvgPathParsing.Operation> operations,
                                      int offX, int offY, float scale, Color color) {
        if (path == null) path = new Path2D.Float();

        if (!operations.isEmpty() && scale > 0) {
            feedPath(path, operations, offX, offY, scale);
            g2d.setColor(color);
            g2d.draw(path);
        }
    }

    public void fillSvgPathOperations(Path2D path, ArrayList<SvgPathParsing.Operation> operations,
                                      int offX, int offY, float scale, Color color) {
        if (path == null) path = new Path2D.Float();

        if (!operations.isEmpty() && scale > 0) {
            feedPath(path, operations, offX, offY, scale);
            g2d.setColor(color);
            g2d.fill(path);
        }
    }

    private void feedPath(Path2D path, ArrayList<SvgPathParsing.Operation> operations, int offX, int offY, float scale) {
        for (SvgPathParsing.Operation o : operations) {
            switch (o.command) {
                case "M":
                    path.moveTo(
                            (o.args.get(0) + offX) * scale,
                            (o.args.get(1) + offY) * scale);
                    break;
                case "C":
                    path.curveTo(
                            (o.args.get(0) + offX) * scale,
                            (o.args.get(1) + offY) * scale,
                            (o.args.get(2) + offX) * scale,
                            (o.args.get(3) + offY) * scale,
                            (o.args.get(4) + offX) * scale,
                            (o.args.get(5) + offY) * scale);
                    break;
                case "Q":
                    path.quadTo(
                            (o.args.get(0) + offX) * scale,
                            (o.args.get(1) + offY) * scale,
                            (o.args.get(2) + offX) * scale,
                            (o.args.get(3) + offY) * scale);
                    break;
                case "Z":
                    path.closePath();
            }
        }
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
