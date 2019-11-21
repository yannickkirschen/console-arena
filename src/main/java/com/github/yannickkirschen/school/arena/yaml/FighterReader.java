package com.github.yannickkirschen.school.arena.yaml;

import com.github.yannickkirschen.school.arena.fighter.Fighter2;
import com.github.yannickkirschen.school.arena.fighter.FighterList;
import com.github.yannickkirschen.school.arena.fighter.FighterList2;
import com.github.yannickkirschen.school.arena.fighter.Skill;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public final class FighterReader {
    private FighterReader() {}

    public static FighterList read() {
        Yaml yaml = new Yaml(new Constructor(FighterList.class));
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("fighters.yml");
        return yaml.load(inputStream);
    }

    public static FighterList2 read2() {
        Yaml yaml = new Yaml(new Constructor(FighterList2.class));
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("fighters2.yml");
        FighterList2 fighters = yaml.load(inputStream);

        int idFighter = 1;
        for (Fighter2 fighter : fighters.getFighters()) {
            int idSkill = 1;
            fighter.setId(idFighter);
            for (Skill skill : fighter.getSkills()) {
                skill.setId(idSkill);
                idSkill++;
            }
            idFighter++;
        }
        return fighters;
    }
}
