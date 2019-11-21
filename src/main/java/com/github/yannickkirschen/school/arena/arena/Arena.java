package com.github.yannickkirschen.school.arena.arena;

import com.github.yannickkirschen.school.arena.fighter.Fighter;

import java.util.Random;

public final class Arena {
    private Arena() {}

    public static String attack(Fighter attacker, Fighter enemy, String skill) {
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
            return attacker.getName();
        } else {
            attacker.reduceHealth(Math.abs(result));
            return enemy.getName();
        }
    }

    public static String defense(Fighter defender, Fighter attacker, String defense) {
        Integer attackStrength;
        Integer size = attacker.getDefenses().size();
        if (size > 0) {
            String attack = attacker.getSkillKeys().get(new Random().nextInt(attacker.getSkills().size()));
            attackStrength = attacker.getSkills().get(attack);
        } else {
            attackStrength = 0;
        }

        Integer defenseStrength = defender.getDefenses().get(defense);
        Integer defenderPower = defender.getPower();
        Integer attackerPower = attacker.getPower();

        Integer result = attackStrength - defenseStrength + attackerPower - defenderPower;

        if (result > 0) {
            attacker.reduceHealth(Math.abs(result));
            return defender.getName();
        } else {
            defender.reduceHealth(Math.abs(result));
            return attacker.getName();
        }
    }
}
