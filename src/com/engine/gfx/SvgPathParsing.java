package com.engine.gfx;

import java.util.ArrayList;
import java.util.Arrays;

public class SvgPathParsing {

    public static ArrayList<Operation> toAbsolutesOperations(String relativeD) {
        ArrayList<Operation> absolutes = new ArrayList<>();

        float x = 0, y = 0;
        float mx = 0, my = 0;

        for (Operation o : getOperations(relativeD)) {

            //todo: implement remaining commands
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

    //TODO: fix not parsing this case: "....m110 20c 40 50 60 70 80 90z..." (letters close nums)
    public static ArrayList<Operation> getOperations(String d) {
        ArrayList<Operation> operations = new ArrayList<>();
        ArrayList<Float> aux = new ArrayList<>();

        StringBuilder buffer = new StringBuilder();
        StringBuilder numBuffer = new StringBuilder();
        String lastCommand = "";
        for (char c : d.concat(" ").toCharArray()) {
            buffer.append(c);

            //TODO: add checking for other path commands
            if (buffer.toString().equals("m") || buffer.toString().equals("c") || buffer.toString().equals("z")) {
                if (!lastCommand.isEmpty()) {
                    operations.add(new Operation(lastCommand, aux));
                    aux.clear();
                }

                lastCommand = buffer.toString();
                buffer = new StringBuilder();
            } else if (buffer.toString().equals(",") || buffer.toString().equals(" ")) {
                if (numBuffer.length() > 0) {
                    aux.add(Float.parseFloat(numBuffer.toString()));
                }
                numBuffer = new StringBuilder();
                buffer = new StringBuilder();
            } else {
                numBuffer.append(buffer);
                buffer = new StringBuilder();
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
        String d = "m 64.95,1.293 c -11.24,0 -24.32,26.467 -23.86,50.357 c 0,7.26 2.35,22.03 5.7,40.56 c -21.07,21.79 -45.581,44.29 -45.581,73.69 c 0,27 16.161,61.4 55.161,61.1 c 6,0 11.39,-0.9 16.23,-2.2 c 3.62,19.6 5.99,35.1 5.99,42.3 c 0,28.2 -36.94,30.9 -38.66,14.7 c 7.8,0 13.94,-6.4 13.94,-14.1 c 0,-7.8 -6.45,-14.2 -14.49,-14.2 c -4.37,0 -8.36,2 -11.01,5 c 0,0 0,0 0,0 c -0.77,0.8 -1.09,1.4 -1.63,2.2 c -1.65,2.8 -2.74,6.9 -2.74,12.7 c 0,23.5 59.58,38.7 59.58,-7.8 c 0,-6.3 -2.5,-22.2 -6.4,-42.2 c 42.52,-15.5 31.82,-78.5 -7.39,-78.8 c -3.04,0 -5.93,0.7 -8.74,1 c -2.27,-10.6 -4.45,-21.2 -6.4,-30.7 c 14.99,-14.6 27.99,-33.54 27.61,-68.33 c 0,-25.06 -8.33,-45.12 -17.15,-45.433 z m 2.58,24.197 c 5.07,-0.77 9.17,4.22 9.17,14.6 c 0.79,17.8 -12.06,33.35 -26.96,49 c -1.41,-8.51 -2.32,-15.5 -2.4,-19.63 c 0.78,-27.8 11.76,-43.11 20.19,-43.97 z m -15.92,92.31 c 1.79,9.4 3.75,19.2 5.77,29.1 c -25.88,9.3 -38.3,45.3 -1.64,61.5 c -22.37,-19.1 -11.36,-41.6 4.76,-45.5 c 4.07,20.2 8.05,40.2 11.24,57.5 c -4.36,1.6 -9.44,2.4 -15.37,2.5 c -14.8,0 -44.39,-9.5 -44.39,-45.2 c 0,-30 20.76,-42.1 39.63,-59.9 z m 12.56,44.4 c 0.78,0 1.41,0 2.03,0 c 27.06,0 37.2,42.8 9.71,56.6 c -3.46,-17.2 -7.6,-36.9 -11.74,-56.6 z";
        operations = SvgPathParsing.getOperations(d);
        System.out.println(SvgPathParsing.toAbsolutesOperations(d));
    }
}
