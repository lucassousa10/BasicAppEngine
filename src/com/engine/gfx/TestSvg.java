package com.engine.gfx;

import com.engine.AbstractApplication;
import com.engine.Engine;
import com.engine.Renderer;

import java.awt.*;
import java.util.ArrayList;

public class TestSvg extends AbstractApplication {

    ArrayList<SvgPathParsing.Operation> operations;

    public TestSvg() {
        /*
        M 10 20 C 20 40 40 60 60 80 M 90 120 C 170 210 190 240 220 260 Z
         */
        String d = "" +
                "m 10 20 " +
                "c 10 20 30 40 50 60 " +
                //"m 30 40 " +
                //"c 80 90 100 120 130 140 " +
                "z";
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
        //e.setScale(1.5f);
        e.start();
    }
}
