package com.epam.ld.module2.testing;

/**
 * Properties
 */
public class Properties {
    private static String input = ClassLoader.getSystemResource("input.txt").getFile();
    private static String output = ClassLoader.getSystemResource("output.txt").getFile();

    public static String getInput() {
        return input;
    }

    public static String getOutput() {
        return output;
    }

    /**
     * Set properties such as file paths
     *
     * @param args array of file paths, should be for input.txt and output.txt files
     */
    public static void setProps(String[] args) {
        if (args != null && args.length == 2) {
            input = args[0];
            output = args[1];
        }
    }
}
