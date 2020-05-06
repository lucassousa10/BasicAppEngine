package com.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private Engine engine;

    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy strategy;
    private Graphics g;

    public Window(Engine engine) {
        this.engine = engine;
        image = new BufferedImage(engine.getWidth(), engine.getHeight(), BufferedImage.TYPE_INT_RGB);

        canvas = new Canvas();
        int canvasWidth = (int) (engine.getWidth() * engine.getScale());
        int canvasHeight = (int) (engine.getHeight() * engine.getScale());
        Dimension d = new Dimension(canvasWidth, canvasHeight);

        frame = new JFrame("teste hehehehe"); //should receive a good title from param...
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);

        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);

        canvas.setSize(d);
        canvas.setPreferredSize(d);
        canvas.setMaximumSize(d);
        canvas.setMinimumSize(d);
        frame.pack();
        frame.setLocationRelativeTo(null);

        strategy = canvas.getBufferStrategy();
        g = strategy.getDrawGraphics();
    }

    public void render() {
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        strategy.show();
    }

    public BufferedImage getImage() {
        return image;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
