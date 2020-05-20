package com.engine.gfx;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;

public class SvgPathParsing {

    @Deprecated
    public static ArrayList<Operation> toAbsolutesOperations(String relativeD) {
        ArrayList<Operation> absolutes = new ArrayList<>();

        float x = 0, y = 0;
        float mx = 0, my = 0;

        for (Operation o : getOperations(relativeD)) {
            switch (o.command) {
                case "m":
                    mx += o.args.get(0);
                    my += o.args.get(1);
                    absolutes.add(new Operation("M", new ArrayList<>(Arrays.asList(mx, my))));
                    break;
                case "c":
                    Operation aux = new Operation(o.command.toUpperCase(), new ArrayList<>());
                    for (int i = 0; i < o.args.size(); i += 2) {
                        x = o.args.get(i) + mx;
                        y = o.args.get(i + 1) + my;
                        aux.args.add(x);
                        aux.args.add(y);
                    }

                    mx = x;
                    my = y;
                    absolutes.add(aux);
                    break;
                case "z":
                    absolutes.add(new Operation("Z", new ArrayList<>(Arrays.asList(x, y))));
                    break;
            }
        }

        return absolutes;
    }

    public static ArrayList<Operation> getOperations(String d) {
        ArrayList<Operation> operations = new ArrayList<>();
        ArrayList<Float> aux = new ArrayList<>();

        String buffer = "", numBuffer = "", lastCommand = "";
        for (char c : d.concat(" ").toCharArray()) {
            buffer += c;

            if (buffer.equals("m") || buffer.equals("c") || buffer.equals("z")) {
                if (!lastCommand.isEmpty()) {
                    operations.add(new Operation(lastCommand, aux));
                    aux.clear();
                }

                lastCommand = buffer;
                buffer = "";
            } else if (buffer.equals(",") || buffer.equals(" ")) {
                if (!numBuffer.isEmpty()) {
                    aux.add(Float.parseFloat(numBuffer));
                }
                numBuffer = "";
                buffer = "";
            } else {
                numBuffer += buffer;
                buffer = "";
            }
        }

        if (!lastCommand.isEmpty()) {
            operations.add(new Operation(lastCommand, aux));
        }

        return operations;
    }

    public static class Operation {

        public String command;
        public ArrayList<Float> args;

        public Operation(String command, ArrayList<Float> args) {
            this.command = command;
            this.args = new ArrayList<>();
            this.args.addAll(args);
        }

        public Point[] getPoints(int offX, int offY) {
            ArrayList<Point> aux = new ArrayList<>();

            for (int i = 0; i < args.size(); i += 2) {
                aux.add(new Point(Math.round(args.get(i)) + offX, Math.round(args.get(i + 1)) + offY));
            }

            return aux.toArray(new Point[0]);
        }

        @Override
        public String toString() {
            return "Operation{" +
                    "command='" + command + '\'' +
                    ", args=" + args +
                    '}';
        }
    }

    public static void main(String[] args) {
        ArrayList<Operation> operations;
        String d = "m 10 20 c 10 20 30 40 50 60 m 30 40 c 80 90 100 120 130 140 z";
        operations = SvgPathParsing.getOperations(d);
        System.out.println(SvgPathParsing.toAbsolutesOperations(d));

        float cursorX = 0, cursorY = 0;
        float currX = 0, currY = 0;
        boolean first = true;
        ArrayList<Float> aux = new ArrayList<>();
        for (Operation o : operations) {
            switch (o.command) {
                case "m":
                    if (!first) {
                        System.out.println("drawLine2(" + currX + ", " + currY + ", " + cursorX + ", " + cursorY + ");");
                    }

                    cursorX += o.args.get(0);
                    cursorY += o.args.get(1);
                    first = false;
                    break;
                case "c":
                    aux.clear();
                    for (int i = 0; i < o.args.size(); i += 2) {
                        currX = o.args.get(i) + cursorX;
                        currY = o.args.get(i + 1) + cursorY;
                        aux.add(currX);
                        aux.add(currY);
                    }

                    System.out.println("drawCubicCurve(" + cursorX + ", " + cursorY + ", " + aux.get(0) + ", " + aux.get(1) + ", " + aux.get(2) + ", " + aux.get(3) + ", " + aux.get(4) + ", " + aux.get(5) + ");");
                    break;
                case "z":
                    System.out.println("drawLine2(" + currX + ", " + currY + ", " + cursorX + ", " + cursorY + ");");
                    break;
            }
        }
    }
}
