package com.github.yannickkirschen.school.arena.arena;

import com.github.yannickkirschen.school.arena.fighter.Fighter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public final class Arena {
    private static final Logger LOGGER = LoggerFactory.getLogger(Arena.class);

    private Arena() {}

    public static void fight(Fighter attacker, Fighter enemy, String skill, boolean chooseDefense) {
        if (chooseDefense) {
            LOGGER.info("The defense is currently generated automatically");
        }

        Integer defenseStrength;
        Integer size = enemy.getDefenses().size();
        if (size > 0) {
            String defense = enemy.getDefenseKeys().get(new Random().nextInt(enemy.getDefenses().size()));
            defenseStrength = enemy.getDefenses().get(defense);
        } else {
            defenseStrength = 0;
        }

        Integer skillStrength = attacker.getSkills().get(skill);
        Integer attackerPower = attacker.getPower();
        Integer enemyPower = enemy.getPower();

        Integer result = skillStrength - defenseStrength + attackerPower - enemyPower;

        if (result > 0) {
            enemy.reduceHealth(Math.abs(result));
        } else {
            attacker.reduceHealth(Math.abs(result));
        }
    }
}
