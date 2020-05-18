package com.engine.gfx;

import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;

import java.awt.*;
import java.util.ArrayList;

public class TestSvg extends AbstractApplication {

    ArrayList<SvgPathParsing.Operation> operations;

    public TestSvg() {
        String d = "m 1 2 c 1 2 3 4 5 6 z";
        operations = SvgPathParsing.toAbsolutesOperations(d);
    }

    @Override
    public void update(Engine engine, float deltaTime) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {
        renderer.drawAbsolutePath(0, 0, Color.WHITE.getRGB(), operations);
    }

    public static void main(String[] args) {
        Engine e = new Engine(new TestSvg());
        e.setSize(600, 600);
        e.start();
    }
}
