package com.engine.util;

public class ElapsedTimeChecker {

    public interface Action { void perform(); }

    public static void check(Action action, int times, boolean useNanos) {
        long begin = useNanos ? System.nanoTime() : System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            action.perform();
        }

        long end = (useNanos ? System.nanoTime() : System.currentTimeMillis()) - begin;
        System.out.println("Spent " + end + (useNanos ? "ns" : "ms") + " to perform " + times + " times.");
    }
}
