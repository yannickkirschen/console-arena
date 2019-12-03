package com.github.yannickkirschen.school.arena;

import com.github.yannickkirschen.school.arena.exception.NonInteractiveModeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;

/**
 * The {@link ConsoleUtil} is the central way of handling issues with the console.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public final class ConsoleUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleUtil.class);
    private static final Console CONSOLE = System.console();

    private ConsoleUtil() {}

    /**
     * Does a console input and casts it to an integer. If the input is 'q', the application quits with a status code of 0.
     * <p>
     * When the input cannot be casted to an Integer, the user is asked to try it again.
     *
     * @return The integer of the user's input.
     */
    public static Integer doConsoleInput() {
        String s = CONSOLE.readLine("> ");
        if ("q".equals(s)) {
            System.exit(0);
        }

        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            LOGGER.info("Type a number!");
            return doConsoleInput();
        }
    }

    /**
     * Checks, if the terminal runs in an interactive mode.
     *
     * @throws NonInteractiveModeException If we are in an non-interactive mode.
     */
    static void ensureInteractiveMode() throws NonInteractiveModeException {
        if (CONSOLE == null) { throw new NonInteractiveModeException(); }
    }

    /**
     * Prints an information that the user shall press any key to continue and can press 'q' at any point in the game to quit.
     */
    static void doInfo() { CONSOLE.readLine("Press any key to continue. You can press 'q' at any point in the game to quit."); }
}
