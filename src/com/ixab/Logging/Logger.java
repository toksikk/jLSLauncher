package com.ixab.Logging;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Logger {
    private static String bracketOpen = "[";
    private static String bracketClosed = "]";
    private static String spacer = " ";
    public static void print(String s, Class c) {
        StringBuilder sb = new StringBuilder();
        long time = System.currentTimeMillis();
        sb.append(bracketOpen);
        sb.append(c.getCanonicalName());
        sb.append(bracketClosed);
        sb.append(spacer);
        sb.append(bracketOpen);
        sb.append(time);
        sb.append(bracketClosed);
        sb.append(spacer);
        sb.append(s);
        System.out.println(sb.toString());
    }
    public static void print(String s) {
        print(s, Logger.class);
    }
    public static void print(String s, Object o) {
        print(s, o.getClass());
    }
}
