package com.engine;

@SuppressWarnings("FieldCanBeLocal")
public class Engine implements Runnable {

    public static final int RATE = 60;

    private int width;
    private int height;

    private float scale;

    int frames = 0, updates = 0;

    private Window window;
    private Renderer renderer;
    private AbstractApplication game;
    private Input input;

    private boolean isRunning;

    public Engine(AbstractApplication game) {
        this.game = game;
        this.setScale(1f);
    }

    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        Thread thread = new Thread(this);
        thread.run(); //should call @{code start()} instead?
    }

    @Override
    public void run() {
        isRunning = true;

        final double ns = 1e9 / RATE;
        long now;
        long auxTimer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        float delta = 0;

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }

            //render here
            renderGame();

            //helper to measure rates per second elapsed
            if (System.currentTimeMillis() - auxTimer >= 1000) {
                window.getFrame().setTitle(getFrames() + " FPS | " + getUpdates() + " UPS");
                frames = 0;
                updates = 0;
                auxTimer = System.currentTimeMillis();
            }
        }
    }

    //updates managers
    private void update() {
        game.updateWithInternalDelta(this);
        input.update();
        updates++;
    }

    private void renderGame() {
        renderer.clear();
        game.render(this, renderer);
        window.render();
        frames++;
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getFrames() {
        return frames;
    }

    public int getUpdates() {
        return updates;
    }
}
