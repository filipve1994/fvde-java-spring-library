package io.filipvde.utils.others;

import java.io.PrintStream;

/**
 * Class used to shorten printing functions.
 */
public final class Print {

    /**
     * The default print stream.
     */
    public Print() {
    }

    /**
     * PrintStream field to be used.
     */
    private static final PrintStream PRINT_STREAM = System.out;
    /**
     * Prints any Object and then terminate the line.
     * @param object the thing to print.
     * @param <T> the type of the {@code Object}.
     */
    public static <T> void println(T object) {
        PRINT_STREAM.println(object);
    }

    /**
     * Terminates the current line by writing the line separator string
     */
    public static void println() {
        PRINT_STREAM.println();
    }

    /**
     * Prints any Object.
     * @param object the thing to print.
     * @param <T> the type of the {@code Object}.
     */
    public static <T> void print(T object) {
        PRINT_STREAM.print(object);
    }

    /**
     * Prints a formatted String
     * @param  format
     *         A format string as described in <a
     *         href="../util/Formatter.html#syntax">Format string syntax</a>
     * @param args arguments.
     * @param <T> the type of the {@code Object}.
     */
    @SafeVarargs
    @SuppressWarnings("ConfusingArgumentToVarargsMethod")
    public static <T> void printf(String format, T...args) {
        PRINT_STREAM.printf(format, args);
    }
}