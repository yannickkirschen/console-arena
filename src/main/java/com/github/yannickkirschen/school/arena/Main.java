package com.github.yannickkirschen.school.arena;

import com.github.yannickkirschen.school.arena.arena.Arena;
import com.github.yannickkirschen.school.arena.fighter.Fighter;
import com.github.yannickkirschen.school.arena.fighter.FighterList;
import com.github.yannickkirschen.school.arena.fighter.FighterList2;
import com.github.yannickkirschen.school.arena.yaml.FighterReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;
import java.util.Random;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        FighterList2 fighterList2 = FighterReader.read2();

        FighterList fighters = FighterReader.read();

        Console console = System.console();
        if (console == null) {
            LOGGER.error("No console: non-interactive mode!");
            System.exit(-1);
        }

        LOGGER.info("{}", fighters);

        Fighter fighter = fighters.getAndRemove(Integer.valueOf(console.readLine("Player: ")));
        LOGGER.info("You've gone for player '{}'", fighter.getName());

        boolean playerAttacks = true;

        Random random = new Random();
        while (fighter.getHealth() > 0) {
            LOGGER.info("Health: {}\n", fighter.getHealth());
            Fighter enemy = fighters.get(random.nextInt(fighters.length()));
            LOGGER.info("\n----------");
            LOGGER.info("You are fighting against '{}' with a power of '{}'.", enemy.getName(), enemy.getPower());

            if (playerAttacks) {
                LOGGER.info("With which skill do you want to attack?");
                LOGGER.info("{}", fighter.getPrettySkills());

                String skill = fighter.getSkillName(Integer.valueOf((console.readLine("Skill: "))));

                LOGGER.info("Attacking '{}' with '{}'.", enemy.getName(), skill);
                String winner = Arena.attack(fighter, enemy, skill);

                if (winner.equals(fighter.getName())) {
                    LOGGER.info("You won the game!");
                } else {
                    LOGGER.info("You lost the game!");
                }
                playerAttacks = false;
            } else {
                LOGGER.info("With which defense do you want to defend?");
                LOGGER.info("{}", fighter.getPrettyDefenses());

                String defense = fighter.getDefenseName(Integer.valueOf((console.readLine("Defense: "))));

                LOGGER.info("Defending '{}' with '{}'.", enemy.getName(), defense);
                String winner = Arena.defense(fighter, enemy, defense);

                if (winner.equals(fighter.getName())) {
                    LOGGER.info("You won the game!");
                } else {
                    LOGGER.info("You lost the game!");
                }
                playerAttacks = true;
            }
        }
    }
}
