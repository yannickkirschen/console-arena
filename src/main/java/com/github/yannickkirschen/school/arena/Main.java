package com.github.yannickkirschen.school.arena;

import com.github.yannickkirschen.school.arena.exception.NonInteractiveModeException;
import com.github.yannickkirschen.school.arena.fighter.Fighter;
import com.github.yannickkirschen.school.arena.fighter.Fighters;
import com.github.yannickkirschen.school.arena.fighter.Mode;
import com.github.yannickkirschen.school.arena.yaml.FighterReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The entry point of ConsoleArena and the actual game logic. The game runs until one of the players (either the user or the computer) has a health of 0.
 *
 * @author Yannick Kirschen
 * @see Fighters
 * @see Fighter
 * @see Mode
 * @see Arena
 * @see com.github.yannickkirschen.school.arena.fighter.Skill
 * @since 1.0.0
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Fighters fighters;
    private Fighter one;
    private Fighter two;

    public static void main(String[] args) {
        new Main().loop(args);
    }

    /**
     * The main loop of the game.
     *
     * @param args The command line arguments.
     */
    private void loop(String[] args) {
        ensureInteractiveMode();
        readFighters(args);
        chooseFighter();
        chooseEnemy();

        while (one.getHealth() > 0 && two.getHealth() > 0) {
            healthInfo(one, two);
            if (fight(one, two, Mode.ATTACK) || fight(one, two, Mode.DEFENSE)) { break; }
        }
        LOGGER.info("{} won the game!", (one.getHealth() > 0 ? one : two).getName());
    }

    /**
     * Ensures that we are in an interactive mode. If not, the application quits with an exit code of -1.
     */
    private void ensureInteractiveMode() {
        try {
            ConsoleUtil.ensureInteractiveMode();
        } catch (NonInteractiveModeException e) {
            LOGGER.error("No console: non-interactive mode!");
            System.exit(-1);
        }
    }

    /**
     * Reads all fighters either from the provided or the default YAML file.
     *
     * @param args The command line arguments.
     */
    private void readFighters(String[] args) {
        fighters = Fighters.fromYamlFighters(FighterReader.read(args));
        if (fighters.amount() < 2) {
            LOGGER.info("There must be at least 2 fighters.");
            System.exit(-1);
        }
        LOGGER.info("{}", fighters);
    }

    /**
     * Lets the user choose a fighter to play with.
     */
    private void chooseFighter() {
        one = fighters.getAndRemove(ConsoleUtil.doConsoleInput());
        while (one == null) {
            LOGGER.info("There was no fighter for the specified ID.");
            one = fighters.getAndRemove(ConsoleUtil.doConsoleInput());
        }
    }

    /**
     * Randomly chooses an enemy.
     */
    private void chooseEnemy() {
        two = fighters.getRandom();
        LOGGER.info("\nYou've gone for player '{}'.", one.getName());
        LOGGER.info("You are fighting against '{}'.", two.getName());
        ConsoleUtil.doInfo();
    }

    /**
     * Do the actual fight of two fighters. Checks, if the loser of this fight has lost the game.
     *
     * @param one  The first player (= the user).
     * @param two  The second player (= the computer)
     * @param mode The mode of the fight (attack/defend)
     *
     * @return <code>true</code>, if the loser of the fight has no more health (and the game is over). Otherwise <code>false</code>.
     */
    private boolean fight(Fighter one, Fighter two, Mode mode) {
        Fighter winner = Arena.fight(one, two, mode);
        if (winner == null) {
            LOGGER.info("There was a tie.");
        } else {
            LOGGER.info("\n\n<<< {} won the fight! >>>\n", winner.getName());
            Fighter loser = oppositeFighter(winner, one, two);
            return loser.getHealth() <= 0;
        }
        return false;
    }

    /**
     * Gets the opposite fighter of two fighters. Given you have two fighters <code>A</code> and <code>B</code>. When the actual fighter is <code>A</code>, the
     * opposite is <code>B</code>. When the actual fighter is <code>B</code>, the opposite is <code>A</code>.
     *
     * @param actual The fighter to get the opposite from.
     * @param one    The first fighter.
     * @param two    The second fighter.
     *
     * @return The opposite fighter of <code>actual</code>.
     */
    private Fighter oppositeFighter(Fighter actual, Fighter one, Fighter two) { return actual.equals(one) ? two : one; }

    /**
     * Prints the health of two fighters.
     *
     * @param one The first fighter.
     * @param two The second fighter.
     */
    private void healthInfo(Fighter one, Fighter two) {
        LOGGER.info("{}'s health: {}", one.getName(), one.getHealth());
        LOGGER.info("{}'s health: {}", two.getName(), two.getHealth());
    }
}
