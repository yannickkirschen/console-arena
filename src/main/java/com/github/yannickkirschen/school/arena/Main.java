package com.github.yannickkirschen.school.arena;

import com.github.yannickkirschen.school.arena.exception.NonInteractiveModeException;
import com.github.yannickkirschen.school.arena.fighter.Fighter;
import com.github.yannickkirschen.school.arena.fighter.Mode;
import com.github.yannickkirschen.school.arena.match.Arena;
import com.github.yannickkirschen.school.arena.match.Match;
import com.github.yannickkirschen.school.arena.yaml.FighterReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * The entry point of ConsoleArena and the actual game logic. The game runs until one of the players (either the user or the computer) has a health of 0.
 *
 * @author Yannick Kirschen
 * @see Fighter
 * @see Mode
 * @see Arena
 * @see com.github.yannickkirschen.school.arena.fighter.Skill
 * @since 1.0.0
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ensureInteractiveMode();
        List<Fighter> fighters = readFighters(args);
        Fighter player = choosePlayer(fighters);
        Fighter enemy = fighters.get(new Random().nextInt(fighters.size()));

        LOGGER.info("\nYou've gone for player '{}'.", player.getName());
        LOGGER.info("You are fighting against '{}'.", enemy.getName());
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
            LOGGER.error("No console: non-interactive mode!");
            System.exit(-1);
        }
    }

    /**
     * Reads the fighters from the YAML file based on the command line argument. Reads the default file if no argument is provided.
     * <p>
     * If there are less than two fighters, the application quits with an exit code of -1.
     *
     * @param args The command line arguments.
     *
     * @return All fighter from the YAML file.
     */
    private static List<Fighter> readFighters(String[] args) {
        List<Fighter> fighters = FighterReader.read(args).asFighters();
        if (fighters.size() < 2) {
            LOGGER.info("There must be at least 2 fighters.");
            System.exit(-1);
        }
        return fighters;
    }

    /**
     * Lets the user choose a player based on a list of fighters.
     *
     * @param fighters The list of fighters to present the user.
     *
     * @return The chosen fighter.
     */
    private static Fighter choosePlayer(List<Fighter> fighters) {
        LOGGER.info("Choose a player:");

        for (int i = 0; i < fighters.size(); i++) {
            Fighter fighter = fighters.get(i);
            LOGGER.info("{} - {} (Power: {})", i, fighter.getName(), fighter.getPower());
        }
        LOGGER.info("\n");

        Fighter player = fighters.get(ConsoleUtil.doConsoleInput());
        while (player == null) {
            LOGGER.info("There was no fighter for the specified ID.");
            player = fighters.get(ConsoleUtil.doConsoleInput());
        }
        fighters.remove(player);
        return player;
    }
}
