package com.github.yannickkirschen.school.arena.yaml;

import com.github.yannickkirschen.school.arena.fighter.FighterList;
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
}
