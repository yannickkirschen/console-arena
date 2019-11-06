package com.github.yannickkirschen.school.arena;

import com.github.yannickkirschen.school.arena.arena.Arena;
import com.github.yannickkirschen.school.arena.fighter.Fighter;
import com.github.yannickkirschen.school.arena.fighter.FighterList;
import com.github.yannickkirschen.school.arena.yaml.FighterReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        FighterList fighters = FighterReader.read();

        Fighter vader = fighters.get("Vader");
        Fighter yoda = fighters.get("Yoda");

        Arena.fight(yoda, vader, "kick", false);

        LOGGER.info("{}", vader.getHealth());
        LOGGER.info("{}", yoda.getHealth());
    }
}
