package com.github.yannickkirschen.school.arena;

import com.github.yannickkirschen.school.arena.fighter.Fighter;
import com.github.yannickkirschen.school.arena.fighter.Mode;
import com.github.yannickkirschen.school.arena.fighter.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Arena} is the place where the actual fight takes place. See {@link #fight(Fighter, Fighter, Mode)} for details.
 *
 * @author Yannick Kirschen
 * @see Fighter
 * @see Mode
 * @see Skill
 * @since 1.0.0
 */
final class Arena {
    private static final Logger LOGGER = LoggerFactory.getLogger(Arena.class);

    private Arena() {}

    /**
     * Does the actual fight. The two fighters provided fight against each other by using a simple mathematical formula. The player (one) defines which action
     * to take (attack/defend). Based on this {@link Mode}, the attack/defense of the second player (= the computer) is chosen randomly. The mode of the second
     * player is shown to the actual player, so that he can choose what skill he wants to use.
     * <p>
     * The formula for the fight is the following:
     *
     * <pre>
     *     skill one is the skill that player one (= the user) chose
     *     skill two is the skill that player to (= the computer) chose
     *
     *     result = [power of skill one] + [power of player one] - [power of skill two] - [power of player two]
     * </pre>
     * <p>
     * The absolute value of this result is now subtracted from the loser's health.
     *
     * @param one               The first player (= the user).
     * @param two               The second player (= the computer).
     * @param modeOfFirstPlayer The mode with which the user wants to attack/defend.
     *
     * @return The fighter who won the match.
     */
    static Fighter fight(Fighter one, Fighter two, Mode modeOfFirstPlayer) {
        String s1 = modeOfFirstPlayer == Mode.ATTACK ? "attacks" : "defends";
        String s2 = modeOfFirstPlayer == Mode.ATTACK ? "defends" : "attacks";

        Mode oppositeSkill = modeOfFirstPlayer == Mode.ATTACK ? Mode.DEFENSE : Mode.ATTACK;

        LOGGER.info(one.skillsAsString(modeOfFirstPlayer));

        Skill skill1 = getSkillOfFighter(one, modeOfFirstPlayer);
        while (skill1 == null) {
            LOGGER.info("There was no skill for the specified ID.");
            skill1 = getSkillOfFighter(one, modeOfFirstPlayer);
        }
        Skill skill2 = two.getRandomSkill(oppositeSkill);

        LOGGER.info("{} {} with '{}'.", one.getName(), s1, skill1.getName());
        LOGGER.info("{} {} with '{}'.", two.getName(), s2, skill2.getName());

        int result = skill1.getPower() + one.getPower() - skill2.getPower() - two.getPower();

        if (result > 0) {
            two.reduceHealth(result);
            return one;
        } else if (result < 0) {
            one.reduceHealth(result);
            return two;
        }
        return null;
    }

    /**
     * Selects a skill of a fighter for a specific mode based on the choice of the user.
     *
     * @param fighter The fighter to get the skill of.
     * @param mode    The mode of the skill to get.
     *
     * @return The selected skill. <code>null</code>, if there was no skill for the user's input.
     */
    private static Skill getSkillOfFighter(Fighter fighter, Mode mode) {
        return fighter.getSkills(mode).get(ConsoleUtil.doConsoleInput());
    }
}
