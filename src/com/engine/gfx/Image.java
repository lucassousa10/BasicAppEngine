package com.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Image {

    private int width, height;
    private int[] imagePixelData;

    public Image(String path) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(Image.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert img != null;
        width = img.getWidth();
        height = img.getHeight();
        imagePixelData = img.getRGB(0, 0, width, height, null, 0, width);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getImagePixelData() {
        return imagePixelData;
    }
}
