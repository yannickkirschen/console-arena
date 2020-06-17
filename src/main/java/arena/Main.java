package arena;

import java.util.*;

import arena.exception.NonInteractiveModeException;
import arena.fighter.*;
import arena.match.*;
import arena.yaml.FighterReader;
import lombok.extern.log4j.Log4j2;

/**
 * The entry point of ConsoleArena and the actual game logic. The game runs until one of the players (either the user or the computer) has a health of 0.
 *
 * @author Yannick Kirschen
 * @see Fighter
 * @see Mode
 * @see Arena
 * @see Skill
 * @since 1.0.0
 */
@Log4j2
public class Main {
    public static void main(String[] args) {
        ensureInteractiveMode();
        List<Fighter> fighters = readFighters(args);
        Fighter player = choosePlayer(fighters);
        Fighter enemy = fighters.get(new Random().nextInt(fighters.size()));

        log.info("\nYou've gone for player '{}'.", player.getName());
        log.info("You are fighting against '{}'.", enemy.getName());
        ConsoleUtil.doInfo();

        new Match(player, enemy).start();
    }

    /**
     * Ensures that we are in an interactive mode. If not, the application quits with an exit code of -1.
     */
    private static void ensureInteractiveMode() {
        try {
            ConsoleUtil.ensureInteractiveMode();
        } catch (NonInteractiveModeException e) {
            log.error("No console: non-interactive mode!");
            System.exit(-1);
        }
    }

    /**
     * Reads the fighters from the YAML file based on the command line argument. Reads the default file if no argument is provided.
     * <p>
     * If there are less than two fighters, the application quits with an exit code of -1.
     *
     * @param args The command line arguments.
     * @return All fighter from the YAML file.
     */
    private static List<Fighter> readFighters(String[] args) {
        List<Fighter> fighters = FighterReader.read(args).asFighters();
        if (fighters.size() < 2) {
            log.info("There must be at least 2 fighters.");
            System.exit(-1);
        }
        return fighters;
    }

    /**
     * Lets the user choose a player based on a list of fighters.
     *
     * @param fighters The list of fighters to present the user.
     * @return The chosen fighter.
     */
    private static Fighter choosePlayer(List<Fighter> fighters) {
        log.info("Choose a player:");

        for (int i = 0; i < fighters.size(); i++) {
            Fighter fighter = fighters.get(i);
            log.info("{} - {} (Power: {})", i, fighter.getName(), fighter.getPower());
        }
        log.info("\n");

        Fighter player = fighters.get(ConsoleUtil.doConsoleInput());
        while (player == null) {
            log.info("There was no fighter for the specified ID.");
            player = fighters.get(ConsoleUtil.doConsoleInput());
        }
        fighters.remove(player);
        return player;
    }
}
