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

    public static void main(String[] args) {
        try {
            ConsoleUtil.ensureNonInteractiveMode();
        } catch (NonInteractiveModeException e) {
            LOGGER.error("No console: non-interactive mode!");
            System.exit(-1);
        }

        Fighters fighters = Fighters.fromYamlFighters(FighterReader.read(args));
        LOGGER.info("{}", fighters);

        Fighter one = fighters.getAndRemove(ConsoleUtil.doConsoleInput());

        while (one == null) {
            LOGGER.info("There was no fighter for the specified ID.");
            one = fighters.getAndRemove(ConsoleUtil.doConsoleInput());
        }

        Fighter two = fighters.getRandom();

        LOGGER.info("You've gone for player '{}'.", one.getName());
        LOGGER.info("You are fighting against '{}'.", two.getName());
        ConsoleUtil.doInfo();

        while (one.getHealth() > 0 && two.getHealth() > 0) {
            ConsoleUtil.clear();
            healthInfo(one, two);
            if (fight(one, two, Mode.ATTACK) || fight(one, two, Mode.DEFENSE)) { break; }
        }

        LOGGER.info("{} won the game!", (one.getHealth() > 0 ? one : two).getName());
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
    private static boolean fight(Fighter one, Fighter two, Mode mode) {
        Fighter winner = Arena.fight(one, two, mode);
        if (winner == null) {
            LOGGER.info("There was a tie.");
        } else {
            LOGGER.info("{} won the fight!", winner.getName());
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
    private static Fighter oppositeFighter(Fighter actual, Fighter one, Fighter two) { return actual.equals(one) ? two : one; }

    /**
     * Prints the health of two fighters.
     *
     * @param one The first fighter.
     * @param two The second fighter.
     */
    private static void healthInfo(Fighter one, Fighter two) {
        LOGGER.info("{}'s health: {}", one.getName(), one.getHealth());
        LOGGER.info("{}'s health: {}", two.getName(), two.getHealth());
    }
}
