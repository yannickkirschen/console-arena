package arena;

import java.io.Console;
import java.util.List;

import arena.exception.NonInteractiveModeException;
import arena.fighter.*;
import lombok.extern.log4j.Log4j2;

/**
 * The {@link ConsoleUtil} is the central way of handling issues with the console.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
@Log4j2
public final class ConsoleUtil {
    private static final Console CONSOLE = System.console();

    private ConsoleUtil() {
    }

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
            log.info("Type a number!");
            return doConsoleInput();
        }
    }

    /**
     * Checks, if the terminal runs in an interactive mode.
     *
     * @throws NonInteractiveModeException If we are in an non-interactive mode.
     */
    static void ensureInteractiveMode() throws NonInteractiveModeException {
        if (CONSOLE == null) {
            throw new NonInteractiveModeException();
        }
    }

    /**
     * Prints an information that the user shall press any key to continue and can press 'q' at any point in the game to quit.
     */
    static void doInfo() {
        CONSOLE.readLine("Press any key to continue. You can press 'q' at any point in the game to quit.");
    }

    /**
     * Constructs all skills of a specific mode in order to be displayed as a menu for the user. Such as:
     *
     * <pre>
     *     How do you want to attack?
     *
     *     0 - Punch (Power: 1)
     *     1 - Kick (Power: 2)
     * </pre>
     * <p>
     * The user can now select which attack to use.
     *
     * @param mode The mode of the skills to display.
     * @return All skills of a specific mode as a pretty string.
     */
    public static String fighterSkillAsString(Fighter fighter, Mode mode) {
        StringBuilder sb = new StringBuilder().append("\n").append("How do you want to ").append(mode == Mode.ATTACK ? "attack" : "defend").append("?");

        List<Skill> skills = fighter.getSkills(mode);
        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            sb.append("\n").append(i).append(" - ").append(skill.getName()).append(" (Power: ").append(skill.getPower()).append(")");
        }

        return sb.append("\n").toString();
    }
}
