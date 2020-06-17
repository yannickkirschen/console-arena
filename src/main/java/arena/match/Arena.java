package arena.match;

import arena.ConsoleUtil;
import arena.fighter.*;
import lombok.extern.log4j.Log4j2;

/**
 * The {@link Arena} is the place where the actual fight takes place. See {@link #fight(Fighter, Fighter, Mode)} for details.
 *
 * @author Yannick Kirschen
 * @see Fighter
 * @see Mode
 * @see Skill
 * @since 1.0.0
 */
@Log4j2
public final class Arena {
    Arena() {
    }

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
     * @return The fighter who won the match.
     */
    Fighter fight(Fighter one, Fighter two, Mode modeOfFirstPlayer) {
        String s1 = modeOfFirstPlayer == Mode.ATTACK ? "attacks" : "defends";
        String s2 = modeOfFirstPlayer == Mode.ATTACK ? "defends" : "attacks";

        // 1. Get opposite skill for enemy
        Mode oppositeSkill = modeOfFirstPlayer == Mode.ATTACK ? Mode.DEFENSE : Mode.ATTACK;
        Skill skill1;
        Skill skill2;

        // 2. Let the user choose an attack/defense
        if (modeOfFirstPlayer == Mode.ATTACK) {
            skill1 = getSkillOfUser(one, modeOfFirstPlayer);
            skill2 = getSkillOfComputer(two, oppositeSkill, s2);
        } else {
            skill2 = getSkillOfComputer(two, oppositeSkill, s2);
            skill1 = getSkillOfUser(one, modeOfFirstPlayer);
        }

        log.info("{} {} with '{}'.", one.getName(), s1, skill1.getName());

        // 3. Calculate result
        int result = skill1.getPower() + one.getPower() - skill2.getPower() - two.getPower();

        if (result > 0) {
            two.reduceHealth(result);
            return one;
        } else if (result < 0) {
            one.reduceHealth(result);
            return two;
        } else {
            return null;
        }
    }

    /**
     * Lets the user select a skill to attack/defend with.
     *
     * @param fighter The user's fighter.
     * @param mode    The mode of the skill to choose from.
     * @return The skill the user chose.
     */
    private Skill getSkillOfUser(Fighter fighter, Mode mode) {
        String fighterSkill = ConsoleUtil.fighterSkillAsString(fighter, mode);
        log.info("{}", fighterSkill);
        Skill skill = fighter.getSkills(mode).get(ConsoleUtil.doConsoleInput());
        while (skill == null) {
            log.info("There was no skill for the specified ID.");
            skill = fighter.getSkills(mode).get(ConsoleUtil.doConsoleInput());
        }
        return skill;
    }

    /**
     * Randomly chooses the skill that the computer will attack/defend with.
     *
     * @param fighter The computer's fighter.
     * @param mode    The mode of the skill to choose from.
     * @param s       The string to show based on the mode (attacks/defends): "{} {} with '{}'." (format: name, s, skill name)
     * @return The skill the computer chose.
     */
    private Skill getSkillOfComputer(Fighter fighter, Mode mode, String s) {
        Skill skill = fighter.getRandomSkill(mode);
        log.info("\n\n!!! {} {} with '{}'.", fighter.getName(), s, skill.getName());
        return skill;
    }
}
