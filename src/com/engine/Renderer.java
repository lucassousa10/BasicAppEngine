package com.engine;

import com.engine.gfx.Font;
import com.engine.gfx.RawMSFont;
import com.engine.gfx.Image;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

@SuppressWarnings("DuplicatedCode")
public class Renderer {

    private int pixelWidth, pixelHeight;
    private int[] mainPixelData;
    private int clearColor = Color.BLACK.getRGB();

    public Renderer(Engine e) {
        pixelWidth = e.getWidth();
        pixelHeight = e.getHeight();
        mainPixelData = (
                (DataBufferInt) e.getWindow()
                        .getImage()
                        .getRaster()
                        .getDataBuffer()).getData();
    }

    /**
     * Clears entire screen with the {@code clearColor} value.
     */
    public void clear() {
        Arrays.fill(mainPixelData, clearColor);
    }

    /**
     * Defines the color of the pixel in the coordinates {@code x} and {@code y} of the the application screen with the color of {@code value}.
     *
     * @param x x coordinate of the pixel to set.
     * @param y y coordinate of the pixel to set.
     * @param value color of the desired pixel.
     */
    public boolean setPixel(int x, int y, int value) {
        if ((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffff00ff) {
            return false;
        }

        mainPixelData[x + y * pixelWidth] = value;
        return true;
    }


    public int getPixel(int x, int y) {
        if ((x < 0 || x >= pixelWidth) || (y < 0 || y >= pixelHeight)) {
            return -1;
        }

        return mainPixelData[x + y * pixelWidth];
    }

    /**
     * Draws the specified text starting in {@code x} and {@code y} coordinates. The color of the text is set using the {@code color} param.
     *
     * Also, this method requires a font object (through the {@code font} param) to draw pixels properly.
     *
     * @see com.engine.gfx.Font
     *
     * @param text the text to be drawn, in a String object
     * @param x x coordinate to start drawing the text
     * @param y y coordinate to start drawing the text
     * @param color the color of the text
     * @param font the font the text should have.
     */
    public void drawText(String text, int x, int y, int color, Font font) {
        int offset = 0;

        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i);
            for (int yy = 0; yy < font.getFontImage().getHeight(); yy++) {
                for (int xx = 0; xx < font.getWidths()[unicode]; xx++) {
                    if (font.getFontImage().getImagePixelData()[(xx + font.getOffsets()[unicode]) + yy * font.getFontImage().getWidth()] == 0xffffffff) {
                        setPixel(xx + x + offset, yy + y, color);
                    }
                }
            }
            offset += font.getWidths()[unicode];
        }
    }

    //TODO: line breaking...
    public void drawText(String text, int offX, int offY, float scale, int color) {
        int nextCharacterOffsetX = 0;
        for (char c : text.toUpperCase().toCharArray()) {
            RawMSFont.renderChar(c, nextCharacterOffsetX + offX, offY, scale, color, this);
            nextCharacterOffsetX += RawMSFont.CHAR_WIDTH * scale;
        }
    }

    //todo
    public void floodFillRect(int x, int y, int w, int h, int color){
        int prevColor = getPixel(x, y);
        drawRect(x, y, w, h, color);
        ffRect(x + 1, y + 1, prevColor, color);
    }

    //todo
    private void ffRect(int x, int y, int prevColor, int nextColor) {
        if (getPixel(x, y) != prevColor || !setPixel(x, y, nextColor)) {
            return;
        }

        ffRect(x + 1, y, prevColor, nextColor);
        ffRect(x - 1, y, prevColor, nextColor);
        ffRect(x, y + 1, prevColor, nextColor);
        ffRect(x, y - 1, prevColor, nextColor);
    }

    /**
     * Draws (not filling, only bordering) a rectangle polygon shape using the respective params.
     *
     * @param x x coordinate to start drawing the rectangle
     * @param y y coordinate to start drawing the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param color color of edges/border of the rectangle
     */
    public void drawRect(int x, int y, int width, int height, int color) {
        if (x < -width) return;
        if (y < -height) return;
        if (x >= pixelWidth) return;
        if (y >= pixelHeight) return;

        int newX = 0;
        int newY = 0;
        int newWidth = width;
        int newHeight = height;

        if (x < 0) newX -= x;
        if (y < 0) newY -= y;
        if (newWidth + x >= pixelWidth) newWidth -= newWidth + x - pixelWidth;
        if (newHeight + y >= pixelHeight) newHeight -= newHeight + y - pixelHeight;

        for (int xx = newX; xx < newWidth; xx++) {
            setPixel(xx + x, y, color);
            setPixel(xx + x, y + height, color);
        }

        for (int yy = newY; yy < newHeight + 1; yy++) {
            setPixel(x, yy + y, color);
            setPixel(x + width, yy + y, color);
        }
    }

    //algorithm source: https://rosettacode.org/wiki/Bitmap/Bresenham%27s_line_algorithm#Java
    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        // delta of exact value and rounded value of the dependent variable
        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                setPixel(x, y, color);
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                setPixel(x, y, color);
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
    }

    //algorithm source: https://rosettacode.org/wiki/Bitmap/B%C3%A9zier_curves/Cubic#Kotlin
    public void drawLine2(int x0, int y0, int x1, int y1, int color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int xx = x0;
        int yy = y0;
        int e1 = (dx > dy ? dx : -dy) / 2;
        int e2;
        while (true) {
            setPixel(xx, yy, color);
            if (xx == x1 && yy == y1) break;
            e2 = e1;
            if (e2 > -dx) {
                e1 -= dy;
                xx += sx;
            }
            if (e2 < dy) {
                e1 += dx;
                yy += sy;
            }
        }
    }

    public void fillRect(int offX, int offY, int width, int height, int color) {
        if (offX < -width) return;
        if (offY < -height) return;
        if (offX >= pixelWidth) return;
        if (offY >= pixelHeight) return;

        int newX = 0;
        int newY = 0;
        int newWidth = width;
        int newHeight = height;

        if (offX < 0) newX -= offX;
        if (offY < 0) newY -= offY;
        if (newWidth + offX >= pixelWidth) newWidth -= newWidth + offX - pixelWidth;
        if (newHeight + offY >= pixelHeight) newHeight -= newHeight + offY - pixelHeight;

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixel(x + offX, y + offY, color);
            }
        }
    }

    public void fillBorderedRect(int offX, int offY, int width, int height, int fillColor, int borderColor) {
        this.fillRect(offX, offY, width, height, fillColor);
        this.drawRect(offX, offY, width, height, borderColor);
    }

    public void drawImage(Image image, int offX, int offY) {
        if (offX < -image.getWidth()) return;
        if (offY < -image.getHeight()) return;
        if (offX >= pixelWidth) return;
        if (offY >= pixelHeight) return;

        int newX = 0;
        int newY = 0;
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();

        if (offX < 0) newX -= offX;
        if (offY < 0) newY -= offY;
        if (newWidth + offX >= pixelWidth) newWidth -= newWidth + offX - pixelWidth;
        if (newHeight + offY >= pixelHeight) newHeight -= newHeight + offY - pixelHeight;

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixel(x + offX, y + offY, image.getImagePixelData()[x + y * image.getWidth()]);
            }
        }
    }

    public void drawPath(Path2D.Double path){

    }

    //algorithm source: https://rosettacode.org/wiki/Bitmap/B%C3%A9zier_curves/Cubic#Kotlin
    public void drawCubicCurve(int resolution, Point... args) {
        //create points references
        Point[] points = new Point[resolution + 1];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }

        //find and plot the points
        for (int i = 0; i <= resolution; i++) {
            double t = ((double) i) / resolution;
            double u = 1.0 - t;
            double a = u * u * u;
            double b = 3.0 * t * u * u;
            double c = 3.0 * t * t * u;
            double d = t * t * t;
            points[i].x = (int) ((a * args[0].x) + (b * args[1].x) + (c * args[2].x) + (d * args[3].x));
            points[i].y = (int) ((a * args[0].y) + (b * args[1].y) + (c * args[2].y) + (d * args[3].y));
            setPixel(points[i].x, points[i].y, Color.WHITE.getRGB());
        }

        //draw segments between each plotted points
        for (int i = 0; i < points.length - 1; i++) {
            Point a = points[i];
            Point b = points[i + 1];
            drawLine2(a.x, a.y, b.x, b.y, Color.WHITE.getRGB());
        }
    }

    public void drawQuadraticCurve(int resolution, Point... args) {
        //create points references
        Point[] points = new Point[resolution + 1];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }

        //find and plot the points
        for (int i = 0; i <= resolution; i++) {
            double t = ((double) i) / resolution;
            double u = 1.0 - t;
            double a = u * u;
            double b = 2.0 * t * u;
            double c = t * t;
            points[i].x = (int) ((a * args[0].x) + (b * args[1].x) + (c * args[2].x));
            points[i].y = (int) ((a * args[0].y) + (b * args[1].y) + (c * args[2].y));
            setPixel(points[i].x, points[i].y, Color.WHITE.getRGB());
        }

        //draw segments between each plotted points
        for (int i = 0; i < points.length - 1; i++) {
            Point a = points[i];
            Point b = points[i + 1];
            drawLine2(a.x, a.y, b.x, b.y, Color.WHITE.getRGB());
        }
    }

    public int getClearColor() {
        return clearColor;
    }

    public void setClearColor(int clearColor) {
        this.clearColor = clearColor;
    }
}
