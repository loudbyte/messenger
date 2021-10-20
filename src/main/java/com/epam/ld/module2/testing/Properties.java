package com.epam.ld.module2.testing;

public class Properties {
    private static String input = ClassLoader.getSystemResource("input.txt").getFile();
    private static String output = ClassLoader.getSystemResource("output.txt").getFile();

    public static String getInput() {
        return input;
    }

    public static String getOutput() {
        return output;
    }

    public static void setProps(String[] args) {
        if (args != null && args.length == 2) {
            input = args[0];
            output = args[1];
        }
    }
}
